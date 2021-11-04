package hotciv.variants.strategy;

import hotciv.common.strategy.WorldLayoutStrategy;
import hotciv.framework.City;
import hotciv.framework.Position;
import hotciv.standard.UnitImpl;
import thirdparty.ThirdPartyFractalGenerator;

import java.util.HashMap;
import java.util.Map;

import static hotciv.framework.GameConstants.WORLDSIZE;

public class FractalWorldLayoutStrategy implements WorldLayoutStrategy {
    @Override
    public String[] getWorldLayout() {
        String[] worldLayout = new String[WORLDSIZE];
        ThirdPartyFractalGenerator generator = new ThirdPartyFractalGenerator();

        for (int i = 0; i < WORLDSIZE; i++) {
            String line = "";
            for (int j = 0; j < WORLDSIZE; j++) {
                line += generator.getLandscapeAt(i, j);
            }
            worldLayout[i] = line;
        }

        return worldLayout;
    }

    @Override
    public Map<Position, City> getCityMap() {
        return new HashMap<>();
    }

    @Override
    public UnitImpl[][] getUnitPositions() {
        return new UnitImpl[WORLDSIZE][WORLDSIZE];
    }
}