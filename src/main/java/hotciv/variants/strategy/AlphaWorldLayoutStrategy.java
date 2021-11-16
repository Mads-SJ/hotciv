package hotciv.variants.strategy;

import hotciv.common.strategy.UnitStrategy;
import hotciv.common.strategy.WorldLayoutStrategy;
import hotciv.framework.City;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.CityImpl;
import hotciv.standard.UnitImpl;

import java.util.HashMap;
import java.util.Map;

import static hotciv.framework.GameConstants.*;

public class AlphaWorldLayoutStrategy implements WorldLayoutStrategy {
    private UnitStrategy unitStrategy;

    public AlphaWorldLayoutStrategy() {
        this.unitStrategy = new AlphaUnitStrategy();
    }
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

        cityMap.put(ALPHA_RED_CITY_POS, new CityImpl(Player.RED, unitStrategy));
        cityMap.put(ALPHA_BLUE_CITY_POS, new CityImpl(Player.BLUE, unitStrategy));

        return cityMap;
    }

    @Override
    public UnitImpl[][] getUnitPositions() {
        UnitImpl[][] unitPositions = new UnitImpl[WORLDSIZE][WORLDSIZE];

        unitPositions[2][0] = new UnitImpl(Player.RED, ARCHER, unitStrategy);
        unitPositions[3][2] = new UnitImpl(Player.BLUE, LEGION, unitStrategy);
        unitPositions[4][3] = new UnitImpl(Player.RED, SETTLER, unitStrategy);

        return unitPositions;
    }
}
