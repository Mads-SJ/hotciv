package hotciv.stub.broker;

import frds.broker.Servant;
import hotciv.framework.*;

public class StubBrokerGame implements Game, Servant {
    private Position position_of_green_city = new Position(1,1);

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
        System.out.println("TURN ENDS"); // TODO virker ikke
    }

    @Override
    public void changeWorkForceFocusInCityAt(Position p, String balance) {
        System.out.println("WORKFORCE CHANGED TO " + balance + " IN CITY AT: " + p);
    }

    @Override
    public void changeProductionInCityAt(Position p, String unitType) {
        System.out.println("PRODUCTION CHANGED TO " + unitType + " IN CITY AT: " + p);
    }

    @Override
    public void performUnitActionAt(Position p) {
        System.out.println("PERFORMED UNIT ACTION AT " + p);
    }

    @Override
    public void addObserver(GameObserver observer) {

    }

    @Override
    public void setTileFocus(Position position) {

    }
}
