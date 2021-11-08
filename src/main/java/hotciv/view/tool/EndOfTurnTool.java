package hotciv.view.tool;

import hotciv.framework.Game;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.standard.NullTool;

import static hotciv.view.GfxConstants.*;

import java.awt.event.MouseEvent;

/** Template for the EndOfTurn Tool exercise (FRS 36.42)...
 *
 * @author Henrik Bærbak Christensen, Aarhus University
 */
public class EndOfTurnTool extends NullTool {
  private final DrawingEditor editor;
  private final Game game;
  private int width;
  private int height;

  public EndOfTurnTool(DrawingEditor editor, Game game) {
    this.editor = editor;
    this.game = game;

    Figure f = editor.drawing().findFigure(TURN_SHIELD_X, TURN_SHIELD_Y);
    width = f.displayBox().width;
    height = f.displayBox().height;
  }

  @Override
  public void mouseDown(MouseEvent e, int x, int y) {
    super.mouseDown(e, x, y);
    // TODO evt behøves dette ikke at blive håndteret her (men kun i compositiontool)
    if (x < TURN_SHIELD_X || TURN_SHIELD_X + width < x) return;
    if (y < TURN_SHIELD_Y || TURN_SHIELD_Y + height < y) return;

    game.endOfTurn();
  }

}
