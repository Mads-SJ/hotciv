package hotciv.standard;

import hotciv.framework.*;
import hotciv.utility.Utility;


import java.util.HashMap;
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
    public static final Position RED_CITY_POSITION = new Position(1, 1);
    public static final Position BLUE_CITY_POSITION = new Position(4, 1);
    private int age;
    private Player winner;

    //TODO: refaktorer med metoder i konstruktør????
    public GameImpl() {
        // Sets starting player
        playerInTurn = Player.RED;
        age = START_AGE;

        // Initialize City Map and insert position for red and blue city.
        cityMap = new HashMap<>();
        cityMap.put(RED_CITY_POSITION, new CityImpl(Player.RED));
        cityMap.put(BLUE_CITY_POSITION, new CityImpl(Player.BLUE));

        // Initialize World Grid with Plains all over the map.
        worldGrid = new TileImpl[WORLDSIZE][WORLDSIZE];
        for(int i = 0; i < WORLDSIZE; i++) {
            for (int j = 0; j < WORLDSIZE; j++) {
                worldGrid[i][j] = new TileImpl(PLAINS);
            }
        }

        // Update world grid with correct tiles on their position.
        worldGrid[1][0] = new TileImpl(OCEANS);
        worldGrid[0][1] = new TileImpl(HILLS);
        worldGrid[2][2] = new TileImpl(MOUNTAINS);

        // Initialize unit positions
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

    public City getCityAt(Position p) {
        return cityMap.get(p);
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

        // Cannot move over mountains and oceans
        if (worldGrid[toRow][toColumn].getTypeString().equals(MOUNTAINS)) return false;
        if (worldGrid[toRow][toColumn].getTypeString().equals(OCEANS)) return false;

        // to-position should be empty
        if (unitPositions[toRow][toColumn] == null) {
            UnitImpl unit = (UnitImpl) getUnitAt(from);
            if (unit.getOwner() != playerInTurn) return false;
            int moveCount = unit.getMoveCount();

            // Calculating the distance moved horizontally and vertically (these numbers should not exceed 1)
            int rowDist = Math.abs(fromRow - toRow);
            int columnDist = Math.abs(fromColumn - toColumn);

            // The move should be legal (meaning that the unit only moves 1 tile in either direction)
            if (rowDist <= moveCount && columnDist <= moveCount) {
                unitPositions[toRow][toColumn] = unitPositions[fromRow][fromColumn];
                unitPositions[fromRow][fromColumn] = null;

                unit.decrementMoveCount(); //TODO: decrement or set movecount?

                return true;
            }
        }
        return false;
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
    }

    public void changeWorkForceFocusInCityAt(Position p, String balance) {
    }

    public void changeProductionInCityAt(Position p, String unitType) {
    }

    public void performUnitActionAt(Position p) {
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

    private void updateCities() {
        for (Position p : cityMap.keySet()) {
            CityImpl c = (CityImpl) cityMap.get(p);
            c.addTreasury(PRODUCTION_AMOUNT);

            if (c.getTreasury() >= c.getCostOfNewUnit()) {
                buyUnitIfPositionAvailable(c, p);
            }
        }
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
}