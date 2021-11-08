package hotciv.visual;

import hotciv.framework.Game;
import hotciv.standard.GameImpl;
import hotciv.stub.FakeObjectGame;
import hotciv.variants.factory.SemiRealFactory;
import hotciv.view.tool.CompositionTool;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;

public class ShowSemi {
    public static void main(String[] args) {
        Game game = new GameImpl(new SemiRealFactory());

        DrawingEditor editor =
                new MiniDrawApplication( "Semi Civ",
                        new HotCivFactory4(game) );
        editor.open();
        editor.showStatus("Click and drag any item to see Game's proper response.");

        editor.setTool(new CompositionTool(editor, game));
    }
}
