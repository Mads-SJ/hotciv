package hotciv.standard;

import hotciv.framework.*;

import java.util.HashMap;
import java.util.Map;

/** Skeleton implementation of HotCiv.
 
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

public class GameImpl implements Game {
  private Player playerInTurn;
  private Map<Position, City> cityMap;

  public GameImpl() {
    playerInTurn = Player.RED;
    cityMap = new HashMap<>();
    cityMap.put(new Position(1,1), new CityImpl(Player.RED));
    cityMap.put(new Position(3,3), new CityImpl(Player.BLUE));
  }

  public Tile getTileAt( Position p ) { return null; }
  public Unit getUnitAt( Position p ) { return null; }
  public City getCityAt( Position p ) { return cityMap.get(p);}
  public Player getPlayerInTurn() { return playerInTurn; }
  public Player getWinner() { return null; }
  public int getAge() { return 0; }
  public boolean moveUnit( Position from, Position to ) {
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

  public void endOfRound() {
    CityImpl redCity = (CityImpl) cityMap.get(new Position(1,1));
    redCity.addTreasury();
  }

  public void changeWorkForceFocusInCityAt( Position p, String balance ) {}
  public void changeProductionInCityAt( Position p, String unitType ) {}
  public void performUnitActionAt( Position p ) {}

  public Map<Position, City> getCities() {
    return cityMap;
  }
}
