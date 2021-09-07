package hotciv.standard;

import hotciv.framework.*;

import org.junit.jupiter.api.*;

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
  public void shouldBePositionOnePointOneForRedCity() {
    assertThat(game.getCityAt(new Position(1,1)).getOwner(), is(Player.RED));
  }

  @Test
  public void shouldNotBeCityAtPositionTwoPointTwo() {
    assertThat(game.getCityAt(new Position(2,2)), is(nullValue()));
  }

  @Test
  public void shouldBePositionThreePointThreeForBlueCity() {
    assertThat(game.getCityAt(new Position(3,3)).getOwner(), is(Player.BLUE));
  }

  @Test
  public void shouldProduce6ProductionForCityAtOnePointOneAtEndOfRound() {
    City c = game.getCityAt(new Position(1,1)); //TODO: Kodeduplikering?? Skal det indsættes i beforeEach
    assertThat(c.getTreasury(), is(0));
    game.endOfTurn();
    assertThat(c.getTreasury(), is(6));
  }

  @Test
  public void shouldProduce6ProductionForEachCityAtEndOfRound() {
    Map<Position, City> cities = game.getCities();
    for (Position p : cities.keySet()) { //TODO: refactor løkken til en metode
      City c = cities.get(p);
      assertThat(c.getTreasury(), is(0));
    }
    game.endOfTurn();
    for (Position p : cities.keySet()) {
      City c = cities.get(p);
      assertThat(c.getTreasury(), is(6));
    }
  }

  /** REMOVE ME. Not a test of HotCiv, just an example of the
      matchers that the hamcrest library has... */
  @Test
  public void shouldDefinetelyBeRemoved() {
    // Matching null and not null values
    // 'is' require an exact match
    String s = null;
    assertThat(s, is(nullValue()));
    s = "Ok";
    assertThat(s, is(notNullValue()));
    assertThat(s, is("Ok"));

    // If you only validate substrings, use containsString
    assertThat("This is a dummy test", containsString("dummy"));

    // Match contents of Lists
    List<String> l = new ArrayList<String>();
    l.add("Bimse");
    l.add("Bumse");
    // Note - ordering is ignored when matching using hasItems
    assertThat(l, hasItems(new String[] {"Bumse","Bimse"}));

    // Matchers may be combined, like is-not
    assertThat(l.get(0), is(not("Bumse")));
  }
}
