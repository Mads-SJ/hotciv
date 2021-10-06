package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.Player;

import static hotciv.framework.GameConstants.*;

public class CityImpl implements City {
    private Player owner;
    private int treasury;
    private String production;
    private int costOfNewUnit;
    private String workforceFocus;

    public CityImpl(Player owner) {
        this.owner = owner;
        treasury = 0; // Treasury is empty when a new city is created.
        setProduction(ARCHER);
        costOfNewUnit = ARCHER_COST;
        workforceFocus = foodFocus;
    }

    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public int getSize() {
        return POPULATION_SIZE;
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
        return workforceFocus;
    }

    public void addTreasury(int amount) {
        treasury += amount;
    }

    public void subtractTreasury(int amount) {
        treasury -= amount;
    }

    public void setProduction(String unitType) {
        production = unitType;

        switch (unitType) {
            case ARCHER:
                costOfNewUnit = ARCHER_COST;
                break;
            case LEGION:
                costOfNewUnit = LEGION_COST;
                break;
            case SETTLER:
                costOfNewUnit = SETTLER_COST;
                break;
            case SANDWORM:
                costOfNewUnit = SANDWORM_COST;
                break;
        }
    }

    public void setWorkforceFocus(String workforceType) {
        workforceFocus = workforceType;
    }

    public int getCostOfNewUnit() {
        return costOfNewUnit;
    }

    public void setOwner(Player p) {
        owner = p;
    }
}
