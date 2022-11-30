package hotciv.stub.broker;
import frds.broker.Servant;
import hotciv.framework.City;
import hotciv.framework.Player;

import java.util.UUID;

public class StubBrokerCity implements City, Servant {
    final String id;

    public StubBrokerCity() {
        id = UUID.randomUUID().toString();
    }

    @Override
    public Player getOwner() {
        return Player.GREEN;
    }

    @Override
    public int getSize() {
        return 4;
    }

    @Override
    public int getTreasury() {
        return 69;
    }

    @Override
    public String getProduction() {
        return "F16";
    }

    @Override
    public String getWorkforceFocus() {
        return "gold";
    }

    @Override
    public String getId() {
        return id;
    }
}
