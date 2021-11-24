package hotciv.standard.broker;

import frds.broker.ClientProxy;
import frds.broker.Requestor;
import hotciv.framework.Player;
import hotciv.framework.Unit;

import static hotciv.framework.OperationNames.*;

public class UnitProxy implements Unit, ClientProxy {
    private static final String UNIT_OBJECT_ID = "singleton";
    private final Requestor requestor;

    public UnitProxy(Requestor requestor) {
        this.requestor = requestor;
    }

    @Override
    public String getTypeString() {
        String typeString = requestor.sendRequestAndAwaitReply(UNIT_OBJECT_ID, UNIT_GET_TYPE_STRING_OPERATION, String.class);
        return typeString;
    }

    @Override
    public Player getOwner() {
        Player owner = requestor.sendRequestAndAwaitReply(UNIT_OBJECT_ID, UNIT_GET_OWNER_OPERATION, Player.class);
        return owner;
    }

    @Override
    public int getMoveCount() {
        int moveCount = requestor.sendRequestAndAwaitReply(UNIT_OBJECT_ID, UNIT_GET_MOVE_COUNT_OPERATION, int.class);
        return moveCount;
    }

    @Override
    public int getDefensiveStrength() {
        int defensiveStrength = requestor.sendRequestAndAwaitReply(UNIT_OBJECT_ID, UNIT_GET_DEFENSIVE_STRENGTH_OPERATION, int.class);
        return defensiveStrength;
    }

    @Override
    public int getAttackingStrength() {
        int attackingStrength = requestor.sendRequestAndAwaitReply(UNIT_OBJECT_ID, UNIT_GET_ATTACKING_STRENGTH_OPERATION, int.class);
        return attackingStrength;
    }

    @Override
    public int getId() {
        return 0;
    }
}
