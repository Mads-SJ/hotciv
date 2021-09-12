package hotciv.standard;

import hotciv.framework.*;

import org.junit.jupiter.api.*;

import static hotciv.framework.GameConstants.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.*;

/** Skeleton class for AlphaCiv test cases

   Updated Aug 2020 for JUnit 5 includes
   Updated Oct 2015 for using Hamcrest matchers

   This source code is from the book 
     "Flexible, Reliable Software:
       Using Patterns and Agile Development"
     published 2010 by CRC Press.
   Author: 
     Henrik B Christensen 
     Department of Computer Science
     Aarhus University
   
   Please visit http://www.baerbak.com/ for further information.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at
 
       http://www.apache.org/licenses/LICENSE-2.0
 
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

*/
public class TestAlphaCiv {
  private Game game;

  /** Fixture for alphaciv testing. */
  @BeforeEach
  public void setUp() {
    game = new GameImpl();
  }
  //TODO: refaktorering

  // FRS p. 455 states that 'Red is the first player to take a turn'.
  @Test
  public void shouldBeRedAsStartingPlayer() {
    assertThat(game.getPlayerInTurn(), is(Player.RED));
  }

  @Test
  public void shouldBeBlueAfterRed() {
    assertThat(game.getPlayerInTurn(), is(Player.RED));
    game.endOfTurn();
    assertThat(game.getPlayerInTurn(), is(Player.BLUE));
  }

  @Test
  public void shouldBeRedAfterBlue() {
    assertThat(game.getPlayerInTurn(), is(Player.RED));
    game.endOfTurn();
    assertThat(game.getPlayerInTurn(), is(Player.BLUE));
    game.endOfTurn();
    assertThat(game.getPlayerInTurn(), is(Player.RED));
  }

  @Test
  public void shouldBePosition1_1ForRedCity() {
    assertThat(game.getCityAt(new Position(1,1)).getOwner(), is(Player.RED));
  }

  @Test
  public void shouldNotBeCityAtPosition2_2() {
    assertThat(game.getCityAt(new Position(2,2)), is(nullValue()));
  }

  @Test
  public void shouldBePosition4_1ForBlueCity() {
    assertThat(game.getCityAt(new Position(4,1)).getOwner(), is(Player.BLUE));
  }

  @Test
  public void shouldProduce6ProductionForRedCityAtEndOfRound() {
    City redCity = game.getCityAt(GameImpl.RED_CITY_POSITION);
    assertThat(redCity.getTreasury(), is(0));

    game.endOfTurn(); // Turn goes to Blue
    game.endOfTurn(); // Turn goes to Red, round has ended

    assertThat(redCity.getTreasury(), is(6));
  }

  @Test
  public void shouldProduce6ProductionForBlueCityAtEndOfRound() {
    City blueCity = game.getCityAt(GameImpl.BLUE_CITY_POSITION);
    assertThat(blueCity.getTreasury(), is(0));

    game.endOfTurn(); // Turn goes to Blue
    game.endOfTurn(); // Turn goes to Red, round has ended

    assertThat(blueCity.getTreasury(), is(6));
  }

  @Test
  public void shouldBePlainsForTileAt0_2(){
    assertThat(game.getTileAt(new Position(0,2)).getTypeString(), is(PLAINS));
  }

  @Test
  public void shouldBeOceanForTileAt1_0(){
    assertThat(game.getTileAt(new Position(1,0)).getTypeString(), is(OCEANS));
  }

  @Test
  public void shouldBeHillForTileAt0_1(){
    assertThat(game.getTileAt(new Position(0,1)).getTypeString(), is(HILLS));
  }

  @Test
  public void shouldBeMountainsForTileAt2_2(){
    assertThat(game.getTileAt(new Position(2,2)).getTypeString(), is(MOUNTAINS));
  }

  @Test
  public void shouldBe16x16SizeForWorldMap(){
    // Checks position (0,0)
    assertThat(game.getTileAt(new Position(0,0)), instanceOf(TileImpl.class));

    // Checks position (7,7)
    assertThat(game.getTileAt(new Position(7,7)), instanceOf(TileImpl.class));

    // Checks position (15,15)
    assertThat(game.getTileAt(new Position(15,15)), instanceOf(TileImpl.class));
  }

  @Test
  public void shouldProduceResourceTypeFoodForPlains(){
    assertThat(new TileImpl(PLAINS).getResourceType(), is(FOOD));
  }

  @Test
  public void shouldProduceResourceTypeFoodForOceans(){
    assertThat(new TileImpl(OCEANS).getResourceType(), is(FOOD));
  }

  @Test
  public void shouldProduceResourceTypeProductionForMountains(){
    assertThat(new TileImpl(MOUNTAINS).getResourceType(), is(PRODUCTION));
  }

  @Test
  public void shouldProduceResourceTypeProductionForHills(){
    assertThat(new TileImpl(HILLS).getResourceType(), is(PRODUCTION));
  }

  @Test
  public void shouldProduceResourceTypeProductionForForest(){
    assertThat(new TileImpl(FOREST).getResourceType(), is(PRODUCTION));
  }

  @Test
  public void shouldProduce3ResourceForPlains(){
    assertThat(new TileImpl(PLAINS).getResources(), is(3));
  }

  @Test
  public void shouldProduce1ResourceForOceans(){
    assertThat(new TileImpl(OCEANS).getResources(), is(1));
  }

  @Test
  public void shouldProduce1ResourceForMountains(){
    assertThat(new TileImpl(MOUNTAINS).getResources(), is(1));
  }

  @Test
  public void shouldProduce2ResourceForHills(){
    assertThat(new TileImpl(HILLS).getResources(), is(2));
  }

  @Test
  public void shouldProduce3ResourceForForest(){
    assertThat(new TileImpl(FOREST).getResources(), is(3));
  }

  @Test
  public void shouldBeArcher(){
    UnitImpl archer = new UnitImpl(Player.RED, ARCHER);
    assertThat(archer.getTypeString(), is(ARCHER));
  }

  @Test
  public void shouldBeLegion(){
    UnitImpl legion = new UnitImpl(Player.BLUE, LEGION);
    assertThat(legion.getTypeString(), is(LEGION));
  }

  @Test
  public void shouldBeRedOwnerForArcher(){
    UnitImpl redArcher = new UnitImpl(Player.RED, ARCHER);
    assertThat(redArcher.getOwner(), is(Player.RED));
  }

  @Test
  public void shouldBeBlueOwnerForLegion(){
    UnitImpl blueLegion = new UnitImpl(Player.BLUE, LEGION);
    assertThat(blueLegion.getOwner(), is(Player.BLUE));
  }

  @Test
  public void shouldBeRedArcherAt2_0(){
    Unit archer = game.getUnitAt(new Position(2,0));
    assertThat(archer.getOwner(), is(Player.RED));
    assertThat(archer.getTypeString(), is(ARCHER));
  }

  @Test
  public void shouldBeBlueLegionAt3_2(){
    Unit legion = game.getUnitAt(new Position(3,2));
    assertThat(legion.getOwner(), is(Player.BLUE));
    assertThat(legion.getTypeString(), is(LEGION));
  }

  @Test
  public void shouldBeRedSettlerAt4_3(){
    Unit settler = game.getUnitAt(new Position(4,3));
    assertThat(settler.getOwner(), is(Player.RED));
    assertThat(settler.getTypeString(), is(SETTLER));
  }

  @Test
  public void shouldBeArcherForRedCityProduction(){
    City redCity = game.getCityAt(GameImpl.RED_CITY_POSITION);
    assertThat(redCity.getProduction(), is(ARCHER));
  }

  @Test
  public void shouldChangeProductionOfRedCityToLegion(){
    CityImpl redCity = (CityImpl) game.getCityAt(GameImpl.RED_CITY_POSITION);
    assertThat(redCity.getProduction(), is(ARCHER));

    redCity.setProduction(LEGION);
    assertThat(redCity.getProduction(), is(LEGION));
  }

  @Test
  public void shouldCost10ForArcher(){
    CityImpl redCity = (CityImpl) game.getCityAt(GameImpl.RED_CITY_POSITION);
    assertThat(redCity.getProduction(), is(ARCHER));
    assertThat(redCity.getCostOfNewUnit(), is(10));
  }

  @Test
  public void shouldCost15ForLegion(){
    CityImpl redCity = (CityImpl) game.getCityAt(GameImpl.RED_CITY_POSITION);
    redCity.setProduction(LEGION);

    assertThat(redCity.getProduction(), is(LEGION));
    assertThat(redCity.getCostOfNewUnit(), is(15));
  }

  @Test
  public void shouldCost30ForSettler(){
    CityImpl redCity = (CityImpl) game.getCityAt(GameImpl.RED_CITY_POSITION);
    redCity.setProduction(SETTLER);

    assertThat(redCity.getProduction(), is(SETTLER));
    assertThat(redCity.getCostOfNewUnit(), is(30));
  }

  @Test
  public void shouldProduceArcherWhenAffordableForRedCity(){
    CityImpl redCity = (CityImpl) game.getCityAt(GameImpl.RED_CITY_POSITION);
    assertThat(game.getUnitAt(GameImpl.RED_CITY_POSITION), is(nullValue()));

    // First round, 0 treasury
    game.endOfTurn();
    game.endOfTurn();

    // Second round, 6 treasury
    game.endOfTurn();
    game.endOfTurn();

    // After second round, 12 treasury.

    Unit redArcher = game.getUnitAt(GameImpl.RED_CITY_POSITION); // Unit is at city's position.
    assertThat(redArcher.getTypeString(), is(ARCHER));
    assertThat(redArcher.getOwner(), is(Player.RED));

    // After unit has been made, 12 - 10 = 2 treasury should remain
    assertThat(redCity.getTreasury(), is(2));
  }

  @Test
  public void shouldProduceLegionWhenAffordableForRedCity(){
    CityImpl redCity = (CityImpl) game.getCityAt(GameImpl.RED_CITY_POSITION);
    assertThat(game.getUnitAt(GameImpl.RED_CITY_POSITION), is(nullValue()));

    // Set production goal to Legion
    redCity.setProduction(LEGION);

    // First round, 0 treasury
    game.endOfTurn();
    game.endOfTurn();

    // Second round, 6 treasury
    game.endOfTurn();
    game.endOfTurn();

    // Third round, 12 treasury.
    game.endOfTurn();
    game.endOfTurn();

    // After third round, 18 treasury.

    Unit redLegion = game.getUnitAt(GameImpl.RED_CITY_POSITION); // Unit is at city's position.
    assertThat(redLegion.getTypeString(), is(LEGION));
    assertThat(redLegion.getOwner(), is(Player.RED));

    // After unit has been made, 18 - 15 = 3 treasury should remain
    assertThat(redCity.getTreasury(), is(3));
  }

  @Test
  public void shouldPlaceUnitClockwiseOfOtherUnitIfTileIsOccupied(){
    // initialize position of city by row and column
    int cityRow = GameImpl.RED_CITY_POSITION.getRow();
    int cityColumn = GameImpl.RED_CITY_POSITION.getColumn();

    // 5 rounds passes
    for(int i = 0; i < 5; i++) {
      game.endOfTurn();
      game.endOfTurn();
    }

    // A unit should be on the cityTile, north of the city and north-east of the city, but not east
    assertThat(game.getUnitAt(new Position(cityRow, cityColumn)), instanceOf(UnitImpl.class));
    assertThat(game.getUnitAt(new Position(cityRow - 1, cityColumn)), instanceOf(UnitImpl.class));
    assertThat(game.getUnitAt(new Position(cityRow - 1, cityColumn + 1)), instanceOf(UnitImpl.class));
    assertThat(game.getUnitAt(new Position(cityRow, cityColumn + 1)), is(nullValue()));

    // 11 rounds passes
    for(int i = 0; i < 11; i++) {
      game.endOfTurn();
      game.endOfTurn();
    }

    // All tiles around the city should be occupied by units
    assertThat(game.getUnitAt(new Position(cityRow - 1, cityColumn - 1)), instanceOf(UnitImpl.class));
  }

  @Test
  public void shouldBe3DefenceForArcher(){
    Unit archer = new UnitImpl(Player.RED, ARCHER);
    assertThat(archer.getDefensiveStrength(), is(3));
  }

  @Test
  public void shouldBe2DefenceForLegion(){
    Unit legion = new UnitImpl(Player.RED, LEGION);
    assertThat(legion.getDefensiveStrength(), is(2));
  }

  @Test
  public void shouldBe3DefenceForSettler(){
    Unit settler = new UnitImpl(Player.RED, SETTLER);
    assertThat(settler.getDefensiveStrength(), is(3));
  }

  @Test
  public void shouldBe2AttackForArcher(){
    Unit archer = new UnitImpl(Player.RED, ARCHER);
    assertThat(archer.getAttackingStrength(), is(2));
  }

  @Test
  public void shouldBe4AttackForLegion(){
    Unit legion = new UnitImpl(Player.RED, LEGION);
    assertThat(legion.getAttackingStrength(), is(4));
  }

  @Test
  public void shouldBe0AttackForSettler(){
    Unit settler = new UnitImpl(Player.RED, SETTLER);
    assertThat(settler.getAttackingStrength(), is(0));
  }

  @Test
  public void shouldStartInYear4000BC(){
    // -4000 = 4000BC
    assertThat(game.getAge(), is(-4000));
  }

  @Test
  public void shouldBeYear3900BCAfterOneRound(){
    game.endOfTurn();
    game.endOfTurn();
    // -3900 = 3900BC
    assertThat(game.getAge(), is(-3900));
  }

  @Test
  public void shouldBeRedWinnerInYear3000BC(){
    for (int i = 0; i < 10; i++){
      game.endOfTurn();
      game.endOfTurn();
    }
    assertThat(game.getWinner(), is(Player.RED));
  }

  @Test
  public void shouldBeNoWinnerBeforeYear3000(){
    assertThat(game.getWinner(), is(nullValue()));
    for (int i = 0; i < 9; i++){
      game.endOfTurn();
      game.endOfTurn();
    }
    assertThat(game.getWinner(), is(nullValue()));
  }

  @Test
  public void shouldMove1DistanceHorizontallyForArcher() {
    // The archer is initially placed on position (2,0)
    Position initialArcherPos = new Position(2,0);
    Position finalArcherPos = new Position(2, 1);
    assertThat(game.getUnitAt(initialArcherPos).getTypeString(), is(ARCHER));

    // There should not be an archer at position (2,1)
    assertThat(game.getUnitAt(finalArcherPos), is(nullValue()));

    // The archer moves one tile east
    game.moveUnit(initialArcherPos, finalArcherPos);
    assertThat(game.getUnitAt(finalArcherPos).getTypeString(), is(ARCHER));

    // The archer is no longer on the archers initial position
    assertThat(game.getUnitAt(initialArcherPos), is(nullValue()));
  }

  @Test
  public void shouldMove1DistanceVerticallyForArcher() {
    // The archer is initially placed on position (2,0)
    Position initialArcherPos = new Position(2,0);
    Position finalArcherPos = new Position(3, 0);
    assertThat(game.getUnitAt(initialArcherPos).getTypeString(), is(ARCHER));

    // There should not be an archer at position (3,0)
    assertThat(game.getUnitAt(finalArcherPos), is(nullValue()));

    // The archer moves one tile south
    game.moveUnit(initialArcherPos, finalArcherPos);
    assertThat(game.getUnitAt(finalArcherPos).getTypeString(), is(ARCHER));

    // The archer is no longer on the archers initial position
    assertThat(game.getUnitAt(initialArcherPos), is(nullValue()));
  }

  @Test
  public void shouldMove1DistanceDiagonallyForArcher() {
    // The archer is initially placed on position (2,0)
    Position initialArcherPos = new Position(2,0);
    Position finalArcherPos = new Position(3, 1);
    assertThat(game.getUnitAt(initialArcherPos).getTypeString(), is(ARCHER));

    // There should not be an archer at position (3,1)
    assertThat(game.getUnitAt(finalArcherPos), is(nullValue()));

    // The archer moves one tile to the south-east
    game.moveUnit(initialArcherPos, finalArcherPos);
    assertThat(game.getUnitAt(finalArcherPos).getTypeString(), is(ARCHER));

    // The archer is no longer on the archers initial position
    assertThat(game.getUnitAt(initialArcherPos), is(nullValue()));
  }

  @Test
  public void shouldMove1DistanceDiagonallyForLegion() {
    // The legion is initially placed on position (3,2)
    Position initialLegionPos = new Position(3,2);
    Position finalLegionPos = new Position(2, 3);
    assertThat(game.getUnitAt(initialLegionPos).getTypeString(), is(LEGION));

    // There should not be a legion at position (2,3)
    assertThat(game.getUnitAt(finalLegionPos), is(nullValue()));

    // Blue should be in turn to be able to move the blue legion unit
    game.endOfTurn();

    // The legion moves one tile to the north-east
    game.moveUnit(initialLegionPos, finalLegionPos);
    assertThat(game.getUnitAt(finalLegionPos).getTypeString(), is(LEGION));

    // The legion is no longer on the legion initial position
    assertThat(game.getUnitAt(initialLegionPos), is(nullValue()));
  }

  @Test
  public void shouldMove1DistanceDiagonallyForSettler() {
    // The settler is initially placed on position (4,3)
    Position initialSettlerPos = new Position(4,3);
    Position finalSettlerPos = new Position(5, 4);
    assertThat(game.getUnitAt(initialSettlerPos).getTypeString(), is(SETTLER));

    // There should not be a settler at position (5,4)
    assertThat(game.getUnitAt(finalSettlerPos), is(nullValue()));

    // The settler moves one tile to the south-east
    game.moveUnit(initialSettlerPos, finalSettlerPos);
    assertThat(game.getUnitAt(finalSettlerPos).getTypeString(), is(SETTLER));

    // The settler is no longer on the settler initial position
    assertThat(game.getUnitAt(initialSettlerPos), is(nullValue()));
  }

  @Test
  public void shouldNotBeAbleToMove2DistanceForArcher() {
    // The archer is initially placed on position (2,0)
    Position initialArcherPos = new Position(2,0);
    Position finalArcherPos = new Position(4, 0);
    assertThat(game.getUnitAt(initialArcherPos).getTypeString(), is(ARCHER));

    // There should not be an archer at position (4,0)
    assertThat(game.getUnitAt(finalArcherPos), is(nullValue()));

    // The archer tries to move two tiles south
    Boolean hasMoved = game.moveUnit(initialArcherPos, finalArcherPos);
    assertThat(hasMoved, is(false));
    assertThat(game.getUnitAt(initialArcherPos).getTypeString(), is(ARCHER));

    // The archer has never moved, and the final position is still available
    assertThat(game.getUnitAt(finalArcherPos), is(nullValue()));
  }

  @Test
  public void shouldHaveMoveCountOf1ForArcher() {
    Unit archer = game.getUnitAt(new Position(2,0));
    assertThat(archer.getMoveCount(), is(1));
  }

  @Test
  public void shouldHaveMoveCountOf0ForArcherAfterMoving() {
    Unit archer = game.getUnitAt(new Position(2,0));
    assertThat(archer.getMoveCount(), is(1));

    // Move unit. Move count should be updated to 0.
    game.moveUnit(new Position(2,0), new Position(2,1));
    assertThat(archer.getMoveCount(), is(0));
  }

  @Test
  public void shouldNotBeAbleToMoveUnitOnMountain() {
    // The legion is initially placed on position (3,2)
    Position initialLegionPos = new Position(3,2);
    Position mountainPos = new Position(2, 2);
    assertThat(game.getUnitAt(initialLegionPos).getTypeString(), is(LEGION));

    // There should not be a legion at position (2,2)
    assertThat(game.getUnitAt(mountainPos), is(nullValue()));

    // The legion tries to move one tile to the north
    Boolean hasMoved = game.moveUnit(initialLegionPos, mountainPos);

    assertThat(hasMoved, is(false));
    assertThat(game.getUnitAt(initialLegionPos).getTypeString(), is(LEGION));

    // The legion has not moved onto the mountain pos
    assertThat(game.getUnitAt(mountainPos), is(nullValue()));
  }

  @Test
  public void shouldNotBeAbleToMoveUnitOnOcean() {
    // The archer is initially placed on position (2,0)
    Position initialArcherPos = new Position(2,0);
    Position oceanPos = new Position(1, 0);
    assertThat(game.getUnitAt(initialArcherPos).getTypeString(), is(ARCHER));

    // There should not be an archer at position (1,0)
    assertThat(game.getUnitAt(oceanPos), is(nullValue()));

    // The archer tries to move one tile north
    Boolean hasMoved = game.moveUnit(initialArcherPos, oceanPos);

    assertThat(hasMoved, is(false));
    assertThat(game.getUnitAt(initialArcherPos).getTypeString(), is(ARCHER));

    // The archer has not moved onto the ocean
    assertThat(game.getUnitAt(oceanPos), is(nullValue()));
  }

  @Test
  public void shouldNotBeAbleToMoveRedUnitInBlueTurn() {
    // The archer is initially placed on position (2,0)
    Position initialArcherPos = new Position(2,0);
    Position candidateMovePos = new Position(3, 0);

    // Blue should be in turn
    game.endOfTurn();
    assertThat(game.getUnitAt(initialArcherPos).getTypeString(), is(ARCHER));

    // There should not be an archer at position (3,0)
    assertThat(game.getUnitAt(candidateMovePos), is(nullValue()));

    // Blue tries to move the red archer one tile south
    Boolean hasMoved = game.moveUnit(initialArcherPos, candidateMovePos);

    assertThat(hasMoved, is(false));
    assertThat(game.getUnitAt(initialArcherPos).getTypeString(), is(ARCHER));

    // The red archer has not moved since it is blue's turn
    assertThat(game.getUnitAt(candidateMovePos), is(nullValue()));
  }

  @Test
  public void shouldBeMoveCount1ForArcherAfterMovementAndEndOfRound() {
    // The archer is initially placed on position (2,0)
    Position initialArcherPos = new Position(2,0);
    Position candidateMovePos = new Position(3, 0);

    Unit archer = game.getUnitAt(initialArcherPos);
    assertThat(archer.getMoveCount(), is(1));

    Boolean hasMoved = game.moveUnit(initialArcherPos, candidateMovePos);
    assertThat(hasMoved, is(true));
    assertThat(archer.getMoveCount(), is(0));

    game.endOfTurn();
    game.endOfTurn();

    assertThat(archer.getMoveCount(), is(1));
  }

  @Test
  public void shouldBeRedUnitThatWinsBattleWhenMovingOntoBlueUnit() {
    Position blueLegionPos = new Position(3,2);
    Position redSettlerPos = new Position(4,3);

    assertThat(game.getUnitAt(blueLegionPos).getTypeString(), is(LEGION));
    assertThat(game.getUnitAt(redSettlerPos).getTypeString(), is(SETTLER));

    game.moveUnit(redSettlerPos, blueLegionPos);

    assertThat(game.getUnitAt(blueLegionPos).getTypeString(), is(SETTLER));
  }

  @Test
  public void shouldChangeOwnershipFromRedToBlueForCity() {
    CityImpl redCity = (CityImpl) game.getCityAt(GameImpl.RED_CITY_POSITION);
    assertThat(redCity.getOwner(), is(Player.RED));
    redCity.setOwner(Player.BLUE);
    assertThat(redCity.getOwner(), is(Player.BLUE));
  }

  @Test
  public void shouldChangeOwnershipFromBlueToRedWhenRedSettlerWinsBattle() {
    Position blueLegionPos = new Position(3,2);
    Position redSettlerPos = new Position(4,3);

    // Asserts that the units is at the correct positions, and that blue owns the city at blue city position.
    assertThat(game.getUnitAt(blueLegionPos).getTypeString(), is(LEGION));
    assertThat(game.getUnitAt(redSettlerPos).getTypeString(), is(SETTLER));
    assertThat(game.getCityAt(GameImpl.BLUE_CITY_POSITION).getOwner(), is(Player.BLUE));

    // Moves red settler one tile west.
    game.moveUnit(redSettlerPos, new Position(4, 2));

    // Changes turn to blue.
    game.endOfTurn();

    // Moves blue legion to blue's city.
    game.moveUnit(blueLegionPos, GameImpl.BLUE_CITY_POSITION);

    // Changes turn to red.
    game.endOfTurn();

    // Moves red settler to blue's city. Wins battle over blue legion, and ownership of the city changes to Red.
    game.moveUnit(new Position(4,2), GameImpl.BLUE_CITY_POSITION);
    assertThat(game.getCityAt(GameImpl.BLUE_CITY_POSITION).getOwner(), is(Player.RED));
  }

  @Test
  public void shouldBePopulationOfOneForRedCity() {
    City redCity = game.getCityAt(GameImpl.RED_CITY_POSITION);
    assertThat(redCity.getSize(), is(1));
  }
}
