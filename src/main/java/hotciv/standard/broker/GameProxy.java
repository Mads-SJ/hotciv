package hotciv.standard.broker;

import hotciv.framework.*;
import frds.broker.*;

import java.util.ArrayList;
import java.util.List;

import static hotciv.framework.GameConstants.WORLDSIZE;
import static hotciv.framework.OperationNames.*;

public class GameProxy implements Game, ClientProxy {
    private static final String GAME_OBJECT_ID = "singleton";
    private final Requestor requestor;
    private List<GameObserver> observers;

    public GameProxy(Requestor requestor) {
        this.requestor = requestor;
        observers = new ArrayList<>();
    }

    @Override
    public Tile getTileAt(Position p) {
        String id = requestor.sendRequestAndAwaitReply(GAME_OBJECT_ID, GAME_GET_TILE_AT_OPERATION, String.class, p);
        TileProxy tileProxy = new TileProxy(requestor, id);
        return tileProxy;
    }

    @Override
    public Unit getUnitAt(Position p) {
        String id = requestor.sendRequestAndAwaitReply(GAME_OBJECT_ID, GAME_GET_UNIT_AT_OPERATION, String.class, p);
        if (id == null) return null;
        UnitProxy unitProxy = new UnitProxy(requestor, id);
        return unitProxy;
    }

    @Override
    public City getCityAt(Position p) {
        String id = requestor.sendRequestAndAwaitReply(GAME_OBJECT_ID, GAME_GET_CITY_AT_OPERATION, String.class, p);
        if (id == null) return null;
        CityProxy cityProxy = new CityProxy(requestor, id);
        return cityProxy;
    }

    @Override
    public Player getPlayerInTurn() {
        Player playerInTurn = requestor.sendRequestAndAwaitReply(GAME_OBJECT_ID, GAME_GET_PLAYER_IN_TURN_OPERATION, Player.class);
        return playerInTurn;
    }

    @Override
    public Player getWinner() {
        Player winner = requestor.sendRequestAndAwaitReply(GAME_OBJECT_ID, GAME_GET_WINNER_OPERATION, Player.class);
        return winner;
    }

    @Override
    public int getAge() {
        int age = requestor.sendRequestAndAwaitReply(GAME_OBJECT_ID, GAME_GET_AGE_OPERATION, int.class);
        return age;
    }

    @Override
    public boolean moveUnit(Position from, Position to) {
        boolean hasMoved = requestor.sendRequestAndAwaitReply(GAME_OBJECT_ID, GAME_MOVE_UNIT_OPERATION,
                boolean.class, from, to);
        if (hasMoved) {
            notifyWorldChangedAt(from);
            notifyWorldChangedAt(to);
        }
        return hasMoved;
    }

    @Override
    public void endOfTurn() {
        requestor.sendRequestAndAwaitReply(GAME_OBJECT_ID, GAME_END_TURN_OPERATION, void.class);
        notifyTurnEnds();
    }

    @Override
    public void changeWorkForceFocusInCityAt(Position p, String balance) {
        requestor.sendRequestAndAwaitReply(GAME_OBJECT_ID, GAME_CHANGE_WORKFORCE_FOCUS_IN_CITY_AT_OPERATION,
                void.class, p, balance);
    }

    @Override
    public void changeProductionInCityAt(Position p, String unitType) {
        requestor.sendRequestAndAwaitReply(GAME_OBJECT_ID, GAME_CHANGE_PRODUCTION_IN_CITY_AT_OPERATION,
                void.class, p, unitType);
    }

    @Override
    public void performUnitActionAt(Position p) {
        requestor.sendRequestAndAwaitReply(GAME_OBJECT_ID, GAME_PERFORM_UNIT_ACTION_AT_OPERATION,
                void.class, p);
        notifyWorldChangedAt(p);
    }

    @Override
    public void addObserver(GameObserver observer) {
        observers.add(observer);
    }

    public void setTileFocus(Position pos) {
        if (! isWithinWorldGrid(pos)) return;
        notifyTileFocusChangedAt(pos);
    }

    private void notifyWorldChangedAt(Position pos) {
        for (GameObserver observer : observers) {
            observer.worldChangedAt(pos);
        }
    }

    private void notifyTurnEnds() {
        for (GameObserver observer : observers) {
            observer.turnEnds(getPlayerInTurn(), getAge());
        }
    }

    private void notifyTileFocusChangedAt(Position pos) {
        for (GameObserver observer : observers) {
            observer.tileFocusChangedAt(pos);
        }
    }

    public boolean isWithinWorldGrid(Position p) {
        boolean isWithinWorldGrid = p.getRow() < WORLDSIZE && p.getColumn() < WORLDSIZE &&
                p.getRow() >= 0 && p.getColumn() >= 0;
        return isWithinWorldGrid;
    }
}
