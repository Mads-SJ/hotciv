package hotciv.stub.broker;

import hotciv.framework.Player;
import hotciv.framework.Unit;

public class StubBrokerUnit implements Unit {
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
        return 0;
    }

    @Override
    public int getAttackingStrength() {
        return 0;
    }
}
