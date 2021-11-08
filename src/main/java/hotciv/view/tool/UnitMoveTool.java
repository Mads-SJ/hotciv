package hotciv.view.tool;

import hotciv.framework.Game;
import hotciv.framework.Position;
import minidraw.framework.DrawingEditor;
import minidraw.standard.NullTool;

import static hotciv.view.GfxConstants.*;

import java.awt.event.MouseEvent;


public class UnitMoveTool extends NullTool {
    private final DrawingEditor editor;
    private final Game game;
    private Position from;

    public UnitMoveTool(DrawingEditor editor, Game game) {
        this.editor = editor;
        this.game = game;
    }

    @Override
    public void mouseDown(MouseEvent e, int x, int y) {
        super.mouseDown(e, x, y);

        from = getPositionFromXY(x, y);
    }

    @Override
    public void mouseUp(MouseEvent e, int x, int y) {
        super.mouseUp(e, x, y);

        Position to = getPositionFromXY(x, y);
        game.moveUnit(from, to);
    }
}
