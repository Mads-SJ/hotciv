package hotciv.standard;

import hotciv.framework.Player;
import hotciv.framework.Unit;

import static hotciv.framework.GameConstants.*;

public class UnitImpl implements Unit {

    private final Player owner;
    private final String typeString;
    private int moveCount;
    private int defensiveStrength;
    private int attackingStrength;
    private Boolean movable;

    public UnitImpl(Player owner, String typeString) {
        this.owner = owner;
        this.typeString = typeString;
        movable = true;
        initializeStrengths(typeString);
        initializeMoveCount(typeString);
    }

    private void initializeMoveCount(String typeString) {
        if (typeString.equals(SANDWORM)) moveCount = SANDWORM_MOVE_COUNT;
        else moveCount = STANDARD_MOVE_COUNT;
    }

    private void initializeStrengths(String typeString) {
        // Attacking- and defensive constants according to specifications in table 36.2
        switch (typeString) {
            case SETTLER:
                attackingStrength = 0;
                defensiveStrength = 3;
                break;
            case ARCHER:
                attackingStrength = 2;
                defensiveStrength = 3;
                break;
            case LEGION:
                attackingStrength = 4;
                defensiveStrength = 2;
                break;
        }
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
        moveCount = STANDARD_MOVE_COUNT;
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
