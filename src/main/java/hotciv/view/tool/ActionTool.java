package hotciv.view.tool;

import hotciv.framework.Game;
import hotciv.framework.Position;
import minidraw.framework.DrawingEditor;
import minidraw.standard.NullTool;

import static hotciv.framework.GameConstants.WORLDSIZE;
import static hotciv.view.GfxConstants.*;

import java.awt.event.MouseEvent;

public class ActionTool extends NullTool {
    private final DrawingEditor editor;
    private final Game game;

    public ActionTool(DrawingEditor editor, Game game) {
        this.editor = editor;
        this.game = game;
    }

    @Override
    public void mouseDown(MouseEvent e, int x, int y) {
        super.mouseDown(e, x, y);

        if (! e.isShiftDown()) return;

        Position tilePos = getPositionFromXY(x, y);
        if(tilePos.getRow() > WORLDSIZE - 1) return;
        if(tilePos.getColumn() > WORLDSIZE - 1) return;

        game.performUnitActionAt(tilePos);
    }
}

