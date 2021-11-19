package hotciv.stub.broker;

import frds.broker.Servant;
import hotciv.framework.*;

public class StubBrokerGame implements Game, Servant {
    private Position position_of_green_city = new Position(1,1);
    private String lastVoidMethodCalled = "none";

    @Override
    public Tile getTileAt(Position p) {
        return null;
    }

    @Override
    public Unit getUnitAt(Position p) {
        return null;
    }

    @Override
    public City getCityAt(Position p) {
        if (p.equals(position_of_green_city)) {
            return new StubBrokerCity();
        }
        return null;
    }

    @Override
    public Player getPlayerInTurn() {
        return Player.GREEN;
    }

    @Override
    public Player getWinner() {
        return Player.YELLOW;
    }

    @Override
    public int getAge() {
        return 42;
    }

    @Override
    public boolean moveUnit(Position from, Position to) {
        return true;
    }

    @Override
    public void endOfTurn() {
        lastVoidMethodCalled = "endOfTurn";
    }

    @Override
    public void changeWorkForceFocusInCityAt(Position p, String balance) {
        lastVoidMethodCalled = "changeWorkForceFocusInCityAt";
    }

    @Override
    public void changeProductionInCityAt(Position p, String unitType) {
        lastVoidMethodCalled = "changeProductionInCityAt";
    }

    @Override
    public void performUnitActionAt(Position p) {
        lastVoidMethodCalled = "performUnitActionAt";
    }

    @Override
    public void addObserver(GameObserver observer) {

    }

    @Override
    public void setTileFocus(Position position) {

    }

    public String getLastVoidMethodCalled() {
        return lastVoidMethodCalled;
    }
}
