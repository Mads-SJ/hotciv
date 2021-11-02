package hotciv.variants.decorator;
import hotciv.framework.*;
import static hotciv.framework.GameConstants.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class GameTranscriber implements Game {
    private Game decoratee;
    private PrintStream ps;
    private ByteArrayOutputStream baos;

    public GameTranscriber(Game decoratee) {
        this.decoratee = decoratee;

        baos = new ByteArrayOutputStream();
        ps = new PrintStream(baos);
    }

    @Override
    public Tile getTileAt(Position p) {
        return decoratee.getTileAt(p);
    }

    @Override
    public Unit getUnitAt(Position p) {
        return decoratee.getUnitAt(p);
    }

    @Override
    public City getCityAt(Position p) {
        return decoratee.getCityAt(p);
    }

    @Override
    public Player getPlayerInTurn() {
        return decoratee.getPlayerInTurn();
    }

    @Override
    public Player getWinner() {
        return decoratee.getWinner();
    }

    @Override
    public int getAge() {
        return decoratee.getAge();
    }

    @Override
    public boolean moveUnit(Position from, Position to) {
        ps.println(decoratee.getPlayerInTurn() + " moves " + decoratee.getUnitAt(from).getTypeString() +
                " from " + from + " to " + to + ".");
        return decoratee.moveUnit(from, to);
    }

    @Override
    public void endOfTurn() {
        ps.println(decoratee.getPlayerInTurn() + " ends turn.");
        decoratee.endOfTurn();
    }

    @Override
    public void changeWorkForceFocusInCityAt(Position p, String balance) {
        ps.println(decoratee.getPlayerInTurn() + " changes work force focus in city at " + p
                    + " to " + balance + " focus.");
        decoratee.changeWorkForceFocusInCityAt(p, balance);
    }

    @Override
    public void changeProductionInCityAt(Position p, String unitType) {
        ps.println(decoratee.getPlayerInTurn() + " changes production in city at " + p
                    + " to " + unitType + ".");
        decoratee.changeProductionInCityAt(p, unitType);
    }

    @Override
    public void performUnitActionAt(Position p) {
        ps.println(decoratee.getPlayerInTurn() + " performs unit action at " + p +
                   " for " + decoratee.getUnitAt(p).getTypeString() + ".");
        decoratee.performUnitActionAt(p);
    }

    public ByteArrayOutputStream getBaos() {
        return baos;
    }
}
