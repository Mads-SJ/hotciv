package hotciv.standard;

import hotciv.framework.Player;
import hotciv.framework.Unit;

import static hotciv.framework.GameConstants.*;

public class UnitImpl implements Unit {

    private final Player owner;
    private final String typeString;
    private int moveCount;


    public UnitImpl(Player owner, String typeString) {
        this.owner = owner;
        this.typeString = typeString;
        moveCount = 1;
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
        switch (typeString){
            case LEGION:
                return 2;
            case ARCHER:
            case SETTLER:
                return 3;
        }
        return -1;
    }

    @Override
    public int getAttackingStrength() {
        switch (typeString) {
            case SETTLER:
                return 0;
            case ARCHER:
                return 2;
            case LEGION:
                return 4;
        }
        return -1;
    }

    public void decrementMoveCount() {
        moveCount--;
    }
    public void resetMoveCount() {
        moveCount = 1;
    }

}
