package hotciv.standard;

import hotciv.framework.*;
import frds.broker.*;
import static hotciv.framework.OperationNames.*;

public class GameProxy implements Game, ClientProxy {
    private static final String GAME_OBJECTID = "singleton";
    private final Requestor requestor;

    public GameProxy(Requestor requestor) {
        this.requestor = requestor;
    }

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
        City city = requestor.sendRequestAndAwaitReply(GAME_OBJECTID, GET_CITY_OPERATION, City.class, p);
        return city;
    }

    @Override
    public Player getPlayerInTurn() {
        Player playerInTurn = requestor.sendRequestAndAwaitReply(GAME_OBJECTID, GET_PLAYER_IN_TURN_OPERATION, Player.class);
        return playerInTurn;
    }

    @Override
    public Player getWinner() {
        Player winner = requestor.sendRequestAndAwaitReply(GAME_OBJECTID, GET_WINNER_OPERATION, Player.class);
        return winner;
    }

    @Override
    public int getAge() {
        int age = requestor.sendRequestAndAwaitReply(GAME_OBJECTID, GET_AGE_OPERATION, int.class);
        return age;
    }

    @Override
    public boolean moveUnit(Position from, Position to) {
        boolean hasMoved = requestor.sendRequestAndAwaitReply(GAME_OBJECTID, MOVE_UNIT_OPERATION,
                boolean.class, from, to);
        return hasMoved;
    }

    @Override
    public void endOfTurn() {
        requestor.sendRequestAndAwaitReply(GAME_OBJECTID, END_TURN_OPERATION, String.class); // TODO: er string class ok
    }

    @Override
    public void changeWorkForceFocusInCityAt(Position p, String balance) {

    }

    @Override
    public void changeProductionInCityAt(Position p, String unitType) {

    }

    @Override
    public void performUnitActionAt(Position p) {

    }

    @Override
    public void addObserver(GameObserver observer) {

    }

    @Override
    public void setTileFocus(Position position) {

    }
}
