package hotciv.standard;

import hotciv.common.factory.GameFactory;
import hotciv.common.strategy.*;
import hotciv.framework.*;
import hotciv.utility.Utility;


import java.util.Map;

import static hotciv.framework.GameConstants.*;

/**
 * Skeleton implementation of HotCiv.
 * <p>
 * This source code is from the book
 * "Flexible, Reliable Software:
 * Using Patterns and Agile Development"
 * published 2010 by CRC Press.
 * Author:
 * Henrik B Christensen
 * Department of Computer Science
 * Aarhus University
 * <p>
 * Please visit http://www.baerbak.com/ for further information.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class GameImpl implements Game {
    private Player playerInTurn;
    private Map<Position, City> cityMap;
    private Tile[][] worldGrid;
    private Unit[][] unitPositions;
    private int currentRound;
    private int age;
    private Player winner;
    private WinningStrategy winningStrategy;
    private AgingStrategy agingStrategy;
    private ActionStrategy actionStrategy;
    private WorldLayoutStrategy worldLayoutStrategy;
    private AttackingStrategy attackingStrategy;


    public GameImpl(GameFactory gameFactory) {
        playerInTurn = Player.RED; // Red always starts
        currentRound = 1;
        age = START_AGE; // TODO: refaktorer age med roundsPassed?

        this.winningStrategy = gameFactory.createWinningStrategy();
        this.agingStrategy = gameFactory.createAgingStrategy();
        this.actionStrategy = gameFactory.createActionStrategy();
        this.worldLayoutStrategy = gameFactory.createWorldLayoutStrategy();
        this.attackingStrategy = gameFactory.createAttackingStrategy();

        initializeCityMap();
        initializeWorldGrid();
        initializeUnitPositions();
    }

    private void initializeCityMap() {
        cityMap = worldLayoutStrategy.getCityMap();
    }

    private void initializeWorldGrid() {
        worldGrid = new TileImpl[WORLDSIZE][WORLDSIZE];
        String[] layout = worldLayoutStrategy.getWorldLayout();

        String line;
        for (int r = 0; r < WORLDSIZE; r++ ) {
            line = layout[r];
            for ( int c = 0; c < WORLDSIZE; c++ ) {
                char tileChar = line.charAt(c);
                String type = "error";
                if ( tileChar == '.' ) { type = OCEANS; }
                if ( tileChar == 'o' ) { type = PLAINS; }
                if ( tileChar == 'M' ) { type = MOUNTAINS; }
                if ( tileChar == 'f' ) { type = FOREST; }
                if ( tileChar == 'h' ) { type = HILLS; }

                worldGrid[r][c] = new TileImpl(type);
            }
        }
    }

    private void initializeUnitPositions() {
        unitPositions = new UnitImpl[WORLDSIZE][WORLDSIZE];

        unitPositions[2][0] = new UnitImpl(Player.RED, ARCHER);
        unitPositions[3][2] = new UnitImpl(Player.BLUE, LEGION);
        unitPositions[4][3] = new UnitImpl(Player.RED, SETTLER);
    }

    public Tile getTileAt(Position p) {
        return worldGrid[p.getRow()][p.getColumn()];
    }

    public Unit getUnitAt(Position p) {
        return unitPositions[p.getRow()][p.getColumn()];
    }

    public void setUnitAt(Position p, Unit u) {
        unitPositions[p.getRow()][p.getColumn()] = u;
    }

    public void removeUnitAt(Position p) {
        unitPositions[p.getRow()][p.getColumn()] = null;
    }

    public City getCityAt(Position p) {
        return cityMap.get(p);
    }

    public void createCityAt(Position p) {
        cityMap.put(p, new CityImpl(getPlayerInTurn()));
    }

    public Map<Position, City> getCities() {
        return cityMap;
    }

    public Player getPlayerInTurn() {
        return playerInTurn;
    }

    public Player getWinner() {
        return winner;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public int getAge() {
        return age;
    }

    public boolean moveUnit(Position from, Position to) {
        if (! isMoveValid(from, to)) return false;

        boolean isAttackSuccessful = true;
        if (isEnemyUnitAt(to)) isAttackSuccessful = resolveAttack(from, to);

        if (! isAttackSuccessful) return false;

        makeActualMove(from, to);

        if (isCityAt(to)) transferCityOwnerAt(to);

        return true;
    }

    private boolean resolveAttack(Position from, Position to) {
        boolean isAttackSuccessful = attackingStrategy.resolveAttack(this, from, to);

        if(! isAttackSuccessful) {
            removeUnitAt(from);
            return false;
        }

        winningStrategy.incrementBattlesWonBy(this, getPlayerInTurn());

        return true;
    }

    private boolean isEnemyUnitAt(Position to) {
        Unit potentialUnit = getUnitAt(to);

        boolean isUnitAtPos = potentialUnit != null;
        if (! isUnitAtPos) return false;

        boolean isEnemyUnit = potentialUnit.getOwner() != getPlayerInTurn();

        return isEnemyUnit;
    }

    private boolean isCityAt(Position p) {
        return getCityAt(p) != null;
    }

    private boolean isMoveValid(Position from, Position to) {
        UnitImpl unitToMove = (UnitImpl) getUnitAt(from);
        UnitImpl potentialUnitAtToPosition = (UnitImpl) getUnitAt(to);

        if (! unitToMove.isMovable()) return false;

        boolean isUnitOwnerThePlayerInTurn = unitToMove.getOwner() == playerInTurn;
        if (! isUnitOwnerThePlayerInTurn) return false;

        if (! isPassableTerrain(to)) return false;

        boolean isStackingUnits = potentialUnitAtToPosition != null &&
                unitToMove.getOwner() == potentialUnitAtToPosition.getOwner();
        if (isStackingUnits) return false;

        if (! isWithinMoveRange(to, from)) return false;

        return true;
    }

    private boolean isWithinMoveRange(Position to, Position from) {
        int moveCount = getUnitAt(from).getMoveCount();

        // Calculating the distance moved horizontally and vertically (these numbers should not exceed 1)
        int rowDist = Math.abs(from.getRow() - to.getRow());
        int columnDist = Math.abs(from.getColumn() - to.getColumn());

        // The move should be within move range (meaning that the unit only moves 1 tile in either direction)
        return rowDist <= moveCount && columnDist <= moveCount;
    }

    private boolean isPassableTerrain(Position p) {
        boolean isMountains = getTileAt(p).getTypeString().equals(MOUNTAINS);
        boolean isOceans = getTileAt(p).getTypeString().equals(OCEANS);
        return ! isMountains && ! isOceans;
    }

    private void makeActualMove(Position from, Position to) {
        UnitImpl unitToMove = (UnitImpl) getUnitAt(from);
        setUnitAt(to, unitToMove);
        removeUnitAt(from);
        unitToMove.decrementMoveCount();
    }

    private void transferCityOwnerAt(Position to) {
        CityImpl c = (CityImpl) getCityAt(to);
        c.setOwner(playerInTurn);
    }

    public void endOfTurn() {
        switch (playerInTurn) {
            case RED:
                playerInTurn = Player.BLUE;
                break;
            case BLUE:
                playerInTurn = Player.RED;
                endOfRound();
                break;
        }
    }

    private void endOfRound() {
        updateCities();
        ageWorld();
        checkIfGameOver();
        resetMoveCount();
        currentRound++;
    }

    private void ageWorld() {
        age = agingStrategy.ageWorld(age);
    }

    private void checkIfGameOver() {
        winner = winningStrategy.checkIfGameOver(this);
    }

    private void resetMoveCount() {
        for(int i = 0; i < WORLDSIZE; i++) {
            for (int j = 0; j < WORLDSIZE; j++) {
                UnitImpl u = (UnitImpl) getUnitAt(new Position(i, j));
                if (u != null) u.resetMoveCount();
            }
        }
    }

    private void updateCities() {
        for (Position p : cityMap.keySet()) {
            CityImpl c = (CityImpl) getCityAt(p);
            c.addTreasury(PRODUCTION_AMOUNT);

            if (c.getTreasury() >= c.getCostOfNewUnit()) {
                buyUnit(p);
            }
        }
    }

    public void changeWorkForceFocusInCityAt(Position p, String balance) {
        CityImpl c = (CityImpl) getCityAt(p);
        if (c.getOwner() == playerInTurn) {
            c.setWorkforceFocus(balance);
        }
    }

    public void changeProductionInCityAt(Position p, String unitType) {
        CityImpl c = (CityImpl) getCityAt(p);
        if (c.getOwner() == playerInTurn) {
            c.setProduction(unitType);
        }
    }

    public void performUnitActionAt(Position p) {
        actionStrategy.performUnitActionAt(this, p);
    }

    private void placeNewUnitAt(CityImpl c, Position p) {
        Unit u = new UnitImpl(c.getOwner(), c.getProduction());
        setUnitAt(p, u);
    }

    private void buyUnit(Position cityPosition) {
        CityImpl c = (CityImpl) getCityAt(cityPosition);
        Position availablePosition = getAvailablePosition(cityPosition);

        if (availablePosition != null) {
            makeActualBuy(c, availablePosition);
        }
    }

    private void makeActualBuy(CityImpl c, Position p) {
        placeNewUnitAt(c, p);
        c.subtractTreasury(c.getCostOfNewUnit());
    }

    private Position getAvailablePosition(Position cityPosition) {
        boolean isCityPositionAvailable = getUnitAt(cityPosition) == null;
        if (isCityPositionAvailable) return cityPosition;

        for (Position candidatePosition : Utility.get8neighborhoodOf(cityPosition)) {
            if (! isPassableTerrain(candidatePosition)) continue;

            boolean isAvailablePosition = getUnitAt(candidatePosition) == null;
            if (isAvailablePosition) return candidatePosition;
        }

        return null;
    }
}