package hotciv.variants;

import hotciv.common.WorldLayoutStrategy;
import hotciv.framework.City;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.CityImpl;
import hotciv.standard.TileImpl;

import java.util.HashMap;
import java.util.Map;

import static hotciv.framework.GameConstants.*;
import static hotciv.standard.GameImpl.BLUE_CITY_POSITION;
import static hotciv.standard.GameImpl.RED_CITY_POSITION;

public class AlphaWorldLayoutStrategy implements WorldLayoutStrategy {
    @Override
    public String[] getWorldLayout() {
        return new String[] {
                "ohoooooooooooooo",
                ".ooooooooooooooo",
                "ooMooooooooooooo",
                "oooooooooooooooo",
                "oooooooooooooooo",
                "oooooooooooooooo",
                "oooooooooooooooo",
                "oooooooooooooooo",
                "oooooooooooooooo",
                "oooooooooooooooo",
                "oooooooooooooooo",
                "oooooooooooooooo",
                "oooooooooooooooo",
                "oooooooooooooooo",
                "oooooooooooooooo",
                "oooooooooooooooo"
        };
    }

    @Override
    public Map<Position, City> getCityMap() {
        Map<Position, City> cityMap = new HashMap<>();

        cityMap.put(ALPHA_RED_CITY_POS, new CityImpl(Player.RED));
        cityMap.put(ALPHA_BLUE_CITY_POS, new CityImpl(Player.BLUE));

        return cityMap;
    }
}
