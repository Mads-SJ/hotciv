package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.Player;

import static hotciv.framework.GameConstants.*;

public class CityImpl implements City {
    private Player owner;
    private int treasury;
    private int food;
    private String production;
    private int costOfNewUnit;
    private String workforceFocus;
    private int population;

    public CityImpl(Player owner) {
        this.owner = owner;
        treasury = 0; // Treasury is empty when a new city is created.
        food = 0;
        setProduction(ARCHER);
        costOfNewUnit = ARCHER_COST;
        workforceFocus = foodFocus;
        population = 1;
    }

    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public int getSize() {
        return population;
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
        // TODO overvej at brug unitStrategy.isUnitValid for at sætte production.
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

    public void increasePopulation() {
        population++;
    }

    public int getPopulation() {
        return population;
    }

    public int getFood() {
        return food;
    }

    public void addFood(int amount) {
        food += amount;
    }

    public void resetFood() {
        food = 0;
    }
}
