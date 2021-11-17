package hotciv.stub;
import frds.broker.Servant;
import hotciv.framework.City;
import hotciv.framework.Player;

public class StubBrokerCity implements City, Servant {
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
}
