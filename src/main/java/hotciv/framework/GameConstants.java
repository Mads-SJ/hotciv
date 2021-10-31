package hotciv.framework;

/**
 * Collection of constants used in HotCiv Game. Note that strings are
 * used instead of enumeration types to keep the set of valid
 * constants open to extensions by future HotCiv variants.  Enums can
 * only be changed by compile time modification.
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
public class GameConstants {
    // The size of the world is set permanently to a 16x16 grid
    public static final int WORLDSIZE = 16;
    // Fixed production and population amount
    public static final int PRODUCTION_AMOUNT = 6;
    public static final int POPULATION_SIZE = 1;
    // Valid unit types
    public static final String ARCHER = "archer";
    public static final String LEGION = "legion";
    public static final String SETTLER = "settler";
    public static final String SANDWORM = "sandworm";
    // Cost of unit types
    public static final int ARCHER_COST = 10;
    public static final int LEGION_COST = 15;
    public static final int SETTLER_COST = 30;
    public static final int SANDWORM_COST = 30;
    // Valid terrain types
    public static final String PLAINS = "plains";
    public static final String OCEANS = "ocean";
    public static final String FOREST = "forest";
    public static final String HILLS = "hills";
    public static final String MOUNTAINS = "mountain";
    public static final String DESERT = "desert";
    // Valid production balance types
    public static final String productionFocus = "hammer";
    public static final String foodFocus = "apple";
    // Valid resources
    public static final String FOOD = "food";
    public static final String PRODUCTION = "production";
    // Age related constants
    public static final int AGING_PER_ROUND = 100;
    public static final int START_AGE = -4000; // 4000BC
    public static final int END_AGE = -3000; // 3000BC
    // Movement related constants
    public static final int STANDARD_MOVE_COUNT = 1;
    public static final int SANDWORM_MOVE_COUNT = 2;
    public static final int MOVE_DISTANCE = 1;
    // Standard city positions
    public static final Position ALPHA_RED_CITY_POS = new Position(1,1);
    public static final Position ALPHA_BLUE_CITY_POS = new Position(4,1);
    public static final Position DELTA_RED_CITY_POS = new Position(8,12);
    public static final Position DELTA_BLUE_CITY_POS = new Position(4,5);
}
