package hotciv.variants;

import hotciv.common.WorldLayoutStrategy;
import static hotciv.framework.GameConstants.*;
import static hotciv.standard.GameImpl.BLUE_CITY_POSITION;
import static hotciv.standard.GameImpl.RED_CITY_POSITION;

import hotciv.framework.City;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.CityImpl;
import hotciv.standard.TileImpl;

import java.util.HashMap;
import java.util.Map;

public class DeltaWorldLayoutStrategy implements WorldLayoutStrategy {
    @Override
    public String[] getWorldLayout() {
        return new String[] {
                "...ooMooooo.....",
                "..ohhoooofffoo..",
                ".oooooMooo...oo.",
                ".ooMMMoooo..oooo",
                "...ofooohhoooo..",
                ".ofoofooooohhoo.",
                "...ooo..........",
                ".ooooo.ooohooM..",
                ".ooooo.oohooof..",
                "offfoooo.offoooo",
                "oooooooo...ooooo",
                ".ooMMMoooo......",
                "..ooooooffoooo..",
                "....ooooooooo...",
                "..ooohhoo.......",
                ".....ooooooooo..",
        };
    }

    @Override
    public Map<Position, City> getCityMap() {
        Map<Position, City> cityMap = new HashMap<>();

        cityMap.put(DELTA_RED_CITY_POS, new CityImpl(Player.RED));
        cityMap.put(DELTA_BLUE_CITY_POS, new CityImpl(Player.BLUE));

        return cityMap;
    }
}
