package hotciv.view.tool;

import hotciv.framework.Game;
import hotciv.framework.Position;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.standard.NullTool;

import static hotciv.framework.GameConstants.WORLDSIZE;
import static hotciv.view.GfxConstants.*;

import java.awt.event.MouseEvent;

public class SetFocusTool extends NullTool {
    private final DrawingEditor editor;
    private final Game game;

    public SetFocusTool(DrawingEditor editor, Game game) {
        this.editor = editor;
        this.game = game;
    }

    @Override
    public void mouseDown(MouseEvent e, int x, int y) {
        super.mouseDown(e, x, y);
        Position tilePos = getPositionFromXY(x, y);
        game.setTileFocus(tilePos);
    }
}
