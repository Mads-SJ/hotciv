package hotciv.variants.strategy;

import hotciv.common.strategy.UnitStrategy;
import hotciv.common.strategy.WorldLayoutStrategy;
import static hotciv.framework.GameConstants.*;

import hotciv.framework.City;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.CityImpl;
import hotciv.standard.UnitImpl;

import java.util.HashMap;
import java.util.Map;

public class ThetaWorldLayoutStrategy implements WorldLayoutStrategy {
    @Override
    public String[] getWorldLayout() {
        return new String[]{
                "...oododdoo.....",
                "..ohhdddddddddd.",
                ".oddddMddd...dd.",
                ".odMMddododdddoo",
                "...ofodddddddo..",
                ".ofddddoddddddo.",
                "...odd..dddddd..",
                ".oddddddddddoM..",
                ".oddddddddddddf.",
                "offddddoddddddoo",
                "oodddodo...oddoo",
                ".ooMMdddd...ddd.",
                "..oodddddfoddd..",
                "....oddddMMdo...",
                "..ooddddddMd....",
                ".....oddddddoo..",
        };
    }

    @Override
    public Map<Position, City> getCityMap() {
        Map<Position, City> cityMap = new HashMap<>();

        cityMap.put(DELTA_RED_CITY_POS, new CityImpl(Player.RED));
        cityMap.put(DELTA_BLUE_CITY_POS, new CityImpl(Player.BLUE));

        return cityMap;
    }

    @Override
    public UnitImpl[][] getUnitPositions() {
        UnitImpl[][] unitPositions = new UnitImpl[WORLDSIZE][WORLDSIZE];

        UnitStrategy unitStrategy = new AlphaUnitStrategy(); // TODO højere kobling ligesom ved alpha
        unitPositions[3][8] = new UnitImpl(Player.RED, ARCHER, unitStrategy);
        unitPositions[5][5] = new UnitImpl(Player.RED, SETTLER, unitStrategy);
        unitPositions[4][4] = new UnitImpl(Player.BLUE, LEGION, unitStrategy);
        unitPositions[8][6] = new UnitImpl(Player.BLUE, SANDWORM, unitStrategy);

        return unitPositions;
    }
}
