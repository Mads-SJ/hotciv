package hotciv.standard;

import hotciv.common.strategy.UnitStrategy;
import hotciv.framework.City;
import hotciv.framework.Player;

import java.util.UUID;

import static hotciv.framework.GameConstants.*;

public class CityImpl implements City {
    private Player owner;
    private int treasury;
    private int food;
    private String production;
    private int costOfNewUnit;
    private String workforceFocus;
    private int population;
    private UnitStrategy unitStrategy;
    private final String id;

    public CityImpl(Player owner, UnitStrategy unitStrategy) {
        this.owner = owner;
        this.unitStrategy = unitStrategy;
        treasury = 0; // Treasury is empty when a new city is created.
        food = 0;
        setProduction(ARCHER);
        costOfNewUnit = ARCHER_COST;
        workforceFocus = foodFocus;
        population = 1;

        id = UUID.randomUUID().toString();
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

    @Override
    public String getId() {
        return id;
    }

    public void addTreasury(int amount) {
        treasury += amount;
    }

    public void subtractTreasury(int amount) {
        treasury -= amount;
    }

    public void setProduction(String unitType) {
        int cost = unitStrategy.getCostOfUnit(unitType);
        if (cost > 0) {
            production = unitType;
            costOfNewUnit = cost;
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
