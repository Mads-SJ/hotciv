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
    // assertThat(game.getPlayerInTurn(), is(Player.RED));
    // TODO: Beholdes eller ej? Kodeduplikering...
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
}
