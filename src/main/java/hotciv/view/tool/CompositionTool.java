package hotciv.view.tool;

import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.framework.Unit;
import hotciv.view.GfxConstants;
import hotciv.view.figure.HotCivFigure;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Tool;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;

import static hotciv.framework.GameConstants.WORLDSIZE;
import static hotciv.view.GfxConstants.*;

/** Template for the CompositionTool exercise (FRS 36.44).
 * Composition tool is basically a State Pattern, similar
 * to MiniDraw's SelectionTool. That is, upon mouse-down
 * it must determine what the user wants (from analyzing
 * what graphical elements have been clicked - unit?
 * city? tile? turn-shield? etc.) and then set its
 * internal tool state to the appropriate tool - and
 * then delegate the mouse down request to that tool.
 *
 * @author Henrik Bærbak Christensen, Aarhus University
 */
public class CompositionTool extends NullTool {
  private final DrawingEditor editor;
  private final Game game;
  private HotCivFigure figureBelowClickPoint;

  private Tool state;
  private ActionTool actionTool;
  private EndOfTurnTool endOfTurnTool;
  private SetFocusTool setFocusTool;
  private UnitMoveTool unitMoveTool;
  private NullTool nullTool;

  public CompositionTool(DrawingEditor editor, Game game) {
    this.editor = editor;
    this.game = game;

    actionTool = new ActionTool(editor, game);
    endOfTurnTool = new EndOfTurnTool(editor, game);
    setFocusTool = new SetFocusTool(editor, game);
    unitMoveTool = new UnitMoveTool(editor, game);
    nullTool = new NullTool();

    state = nullTool;
  }

  @Override
  public void mouseDown(MouseEvent e, int x, int y) {
    // Find the figure (if any) just below the mouse click position
    figureBelowClickPoint = (HotCivFigure) editor.drawing().findFigure(x, y);

    // Next determine the state of tool to use
    if (figureBelowClickPoint == null) {
      Position tilePos = getPositionFromXY(x, y);
      if (tilePos.getRow() <= WORLDSIZE - 1 && tilePos.getColumn() <= WORLDSIZE - 1) {
        state = setFocusTool;
      }
      else state = nullTool;
    } else {
      String figureTypeString = figureBelowClickPoint.getTypeString();
      if (figureTypeString.equals(TURN_SHIELD_TYPE_STRING)) {
        state = endOfTurnTool;
      }
      else if (e.isShiftDown() && figureTypeString.equals(UNIT_TYPE_STRING)) {
        state = actionTool;
      }
      else if (figureTypeString.equals(UNIT_TYPE_STRING)) {
        setFocusTool.mouseDown(e, x, y);
        state = unitMoveTool;
        // dette vil fungere for setFocusTool hvis distancen der flyttes er 0.
      }
      else if (figureTypeString.equals(CITY_TYPE_STRING)) {
        state = setFocusTool;
      }
      else {
        state = nullTool;
      }
    }
    // Finally, delegate to the selected state
    state.mouseDown(e, x, y);
  }

  @Override
  public void mouseUp(MouseEvent e, int x, int y) {
    if (state != unitMoveTool) return;
    state.mouseUp(e, x, y);
  }
}