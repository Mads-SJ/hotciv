package hotciv.standard;

import hotciv.common.ActionStrategy;
import hotciv.common.AgingStrategy;
import hotciv.common.WinningStrategy;
import hotciv.common.WorldLayoutStrategy;
import hotciv.framework.*;
import hotciv.utility.Utility;
import hotciv.variants.*;


import java.util.HashMap;
import java.util.Map;

import static hotciv.framework.GameConstants.*;
import static hotciv.framework.WorldLayoutConstants.*;

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
    public static final Position RED_CITY_POSITION = new Position(1, 1);
    public static final Position BLUE_CITY_POSITION = new Position(4, 1);
    private int age;
    private Player winner;
    private WinningStrategy winningStrategy;
    private AgingStrategy agingStrategy;
    private ActionStrategy actionStrategy;
    private WorldLayoutStrategy worldLayoutStrategy;

    public GameImpl(String gameVariant) {
        playerInTurn = Player.RED; // Red always starts
        age = START_AGE;

        initializeGameVariant(gameVariant);
        initializeCityMap();
        initializeWorldGrid();
        initializeUnitPositions(gameVariant);
    }

    private void initializeGameVariant(String gameVariant) {
        // Set default (Alpha Civ) game strategy variants
        winningStrategy = new AlphaWinningStrategy();
        agingStrategy = new AlphaAgingStrategy();
        actionStrategy = new AlphaActionStrategy();
        worldLayoutStrategy = new AlphaWorldLayoutStrategy();

        switch(gameVariant) {
            case ALPHA_CIV:
                // do nothing, game already set as default
                break;
            case BETA_CIV:
                winningStrategy = new BetaWinningStrategy();
                agingStrategy = new BetaAgingStrategy();
                break;
            case GAMMA_CIV:
                actionStrategy = new GammaActionStrategy();
            case DELTA_CIV:
                worldLayoutStrategy = new DeltaWorldLayoutStrategy(DELTA_CIV_WORLD_LAYOUT);
        }
    }

    private void initializeCityMap() { // TODO: switch med constants eller metode i strategy??
        cityMap = new HashMap<>();

        // Standard positions for some cities
        cityMap.put(RED_CITY_POSITION, new CityImpl(Player.RED));
        cityMap.put(BLUE_CITY_POSITION, new CityImpl(Player.BLUE));
    }

    private void initializeWorldGrid() {
        worldGrid = worldLayoutStrategy.createWorldLayout();
    }

    private void initializeUnitPositions(String gameVariant) {
        unitPositions = new UnitImpl[WORLDSIZE][WORLDSIZE];

        // Some units are placed before game starts for certain game variants.
        // TODO: Parametiseret løsning... metode i strategy i stedet?
        switch (gameVariant) {
            case ALPHA_CIV:
            case BETA_CIV:
            case GAMMA_CIV:
                unitPositions[2][0] = new UnitImpl(Player.RED, ARCHER);
                unitPositions[3][2] = new UnitImpl(Player.BLUE, LEGION);
                unitPositions[4][3] = new UnitImpl(Player.RED, SETTLER);
                break;
            case DELTA_CIV:
                // do nothing
                break;
        }
    }

    public Tile getTileAt(Position p) {
        return worldGrid[p.getRow()][p.getColumn()];
    }

    public Unit getUnitAt(Position p) {
        return unitPositions[p.getRow()][p.getColumn()];
    }

    public void removeUnitAt(Position p) {
        unitPositions[p.getRow()][p.getColumn()] = null;
    }

    public City getCityAt(Position p) {
        return cityMap.get(p);
    }

    public void createCityAt(Position p) {
        cityMap.put(p, new CityImpl(getPlayerInTurn())); //TODO: refaktorer med denne metode
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

    public int getAge() {
        return age;
    }

    public boolean moveUnit(Position from, Position to) {
        int fromRow = from.getRow();
        int fromColumn = from.getColumn();
        int toRow = to.getRow();
        int toColumn = to.getColumn();

        // Gets the unit at the two positions, if there is a unit. If not, the unit will be null.
        UnitImpl fromUnit = (UnitImpl) getUnitAt(from);
        UnitImpl toUnit = (UnitImpl) getUnitAt(to);

        // If archer is fortified, it cannot move.
        if (!fromUnit.isMovable()) return false;
        // Units can only be moved, if their owner is the player in turn.
        if (fromUnit.getOwner() != playerInTurn) return false;
        // Unit cannot move over mountains and oceans
        if (worldGrid[toRow][toColumn].getTypeString().equals(MOUNTAINS)) return false;
        if (worldGrid[toRow][toColumn].getTypeString().equals(OCEANS)) return false;

        // to-position should be empty or the unit should not be owned by the same owner as from unit
        if (toUnit == null || fromUnit.getOwner() != toUnit.getOwner()) {
            int moveCount = fromUnit.getMoveCount();

            // Calculating the distance moved horizontally and vertically (these numbers should not exceed 1)
            int rowDist = Math.abs(fromRow - toRow);
            int columnDist = Math.abs(fromColumn - toColumn);

            // The move should be legal (meaning that the unit only moves 1 tile in either direction)
            if (rowDist <= moveCount && columnDist <= moveCount) {

                // Change position of the unit.
                unitPositions[toRow][toColumn] = unitPositions[fromRow][fromColumn];
                // The old position is now free.
                removeUnitAt(from);

                fromUnit.decrementMoveCount();

                // If there's a city on the 'to' position, transfer it to the player arriving at the tile.
                transferCityOwner(to);

                return true;

            }
        }
        return false;
    }

    private void transferCityOwner(Position to) {
        CityImpl candidateCity = (CityImpl) getCityAt(to);
        if (candidateCity != null) {
            candidateCity.setOwner(playerInTurn);
        }
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
        age = agingStrategy.ageWorld(age);
        winner = winningStrategy.checkIfGameOver(this);
        resetMoveCount();
    }

    private void ageWorld() {
        age += AGING_PER_ROUND;
    }

    private void checkIfGameOver() {
        if (age == END_AGE) {
            winner = Player.RED;
        }
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
            CityImpl c = (CityImpl) cityMap.get(p);
            c.addTreasury(PRODUCTION_AMOUNT);

            if (c.getTreasury() >= c.getCostOfNewUnit()) {
                buyUnitIfPositionAvailable(c, p);
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

    private void placeUnit(CityImpl c, Position p) {
        unitPositions[p.getRow()][p.getColumn()] = new UnitImpl(c.getOwner(), c.getProduction());
        c.subtractTreasury(c.getCostOfNewUnit());
    }

    private void buyUnitIfPositionAvailable(CityImpl c, Position cityPosition) {
        // A unit is placed on the city position if no other unit is present
        if (unitPositions[cityPosition.getRow()][cityPosition.getColumn()] == null) {
            placeUnit(c, cityPosition);
        }
        // A unit is placed on the first non-occupied adjacent tile,
        // starting from the tile just north of the city and moving clockwise
        else {
            for (Position candidatePosition : Utility.get8neighborhoodOf(cityPosition)) {
                if (unitPositions[candidatePosition.getRow()][candidatePosition.getColumn()] == null) {
                    placeUnit(c, candidatePosition);
                    break;
                }
            }
        }
    }
}