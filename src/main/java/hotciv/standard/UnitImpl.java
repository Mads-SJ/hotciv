package hotciv.standard;

import hotciv.common.strategy.UnitStrategy;
import hotciv.framework.Player;
import hotciv.framework.Unit;

import static hotciv.framework.GameConstants.*;

public class UnitImpl implements Unit {

    private final Player owner;
    private final String typeString;
    private UnitStrategy unitStrategy;
    private final int initialMoveCount;
    private int moveCount;
    private int defensiveStrength;
    private int attackingStrength;
    private Boolean movable;

    public UnitImpl(Player owner, String typeString, UnitStrategy unitStrategy) {
        this.unitStrategy = unitStrategy;
        if (unitStrategy.isUnitValid(typeString)) this.typeString = typeString;
        else this.typeString = ARCHER; // if unit is not valid the unit type is set to ARCHER

        this.owner = owner;
        movable = true;

        attackingStrength = unitStrategy.initializeAttackingStrength(typeString);
        defensiveStrength = unitStrategy.initializeDefensiveStrength(typeString);

        moveCount = unitStrategy.initializeMoveCount(typeString);
        initialMoveCount = moveCount;
    }

    @Override
    public String getTypeString() {
        return typeString;
    }

    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public int getMoveCount() {
        return moveCount;
    }

    @Override
    public int getDefensiveStrength() {
        return defensiveStrength;
    }

    @Override
    public int getAttackingStrength() {
       return attackingStrength;
    }

    public void changeDefensiveStrength() {
        if (typeString.equals(ARCHER)) {
            if (defensiveStrength == 3) defensiveStrength *=2;
            else if (defensiveStrength == 6) defensiveStrength /= 2;
        }
    }

    public void decrementMoveCount() {
        moveCount--;
    }
    public void resetMoveCount() {
        moveCount = initialMoveCount;
    }
    public Boolean isMovable() {
        return movable;
    }

    public void changeMovable() {
        if (typeString.equals(ARCHER)) {
            movable = !movable;
        }
    }

}
