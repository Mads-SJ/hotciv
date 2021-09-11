package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.Player;

import static hotciv.framework.GameConstants.*;

public class CityImpl implements City {
    private final Player owner;
    private int treasury;
    private String production;
    private int costOfNewUnit;

    public CityImpl(Player owner) {
        this.owner = owner;
        treasury = 0;
        production = ARCHER;
        costOfNewUnit = 10;
    }

    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public int getTreasury() {
        return treasury;
    }


    @Override
    public String getProduction() {
        return production;
    }

    @Override
    public String getWorkforceFocus() {
        return null;
    }

    //TODO: magic constant
    public void addTreasury() {
        treasury += 6;
    }

    public void setProduction(String unitType) {
        production = unitType;

        switch (unitType) {
            case ARCHER:
                costOfNewUnit = 10;
                break;
            case LEGION:
                costOfNewUnit = 15;
                break;
            case SETTLER:
                costOfNewUnit = 30;
                break;
        }
    }

    public int getCostOfNewUnit() {
        return costOfNewUnit;
    }
}
