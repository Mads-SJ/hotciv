package hotciv.stub.broker;

import hotciv.framework.Player;
import hotciv.framework.Unit;

import java.util.UUID;

public class StubBrokerUnit implements Unit {
    final String id;

    public StubBrokerUnit() {
        id = UUID.randomUUID().toString();
    }

    @Override
    public String getTypeString() {
        return "F16";
    }

    @Override
    public Player getOwner() {
        return Player.YELLOW;
    }

    @Override
    public int getMoveCount() {
        return 420;
    }

    @Override
    public int getDefensiveStrength() {
        return 50;
    }

    @Override
    public int getAttackingStrength() {
        return 100;
    }

    @Override
    public String getId() {
        return id;
    }
}
