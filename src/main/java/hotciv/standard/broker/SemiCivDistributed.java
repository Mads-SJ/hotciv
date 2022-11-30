package hotciv.standard.broker;

import frds.broker.ClientRequestHandler;
import frds.broker.ipc.http.UriTunnelClientRequestHandler;
import frds.broker.ipc.socket.SocketClientRequestHandler;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.standard.GameImpl;
import hotciv.variants.factory.SemiRealFactory;
import hotciv.view.tool.CompositionTool;
import hotciv.visual.HotCivFactory4;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;

import static hotciv.framework.GameConstants.DELTA_BLUE_CITY_POS;
import static hotciv.framework.GameConstants.DELTA_RED_CITY_POS;

public class SemiCivDistributed {
    public static void main(String[] args) {
        new SemiCivDistributed(args[0]);
    }

    public SemiCivDistributed(String hostname) {
        System.out.println("=== SemiCiv Distributed Client (HTTP) (host:" + hostname + ") ===");

        ClientRequestHandler crh = new UriTunnelClientRequestHandler();
        crh.setServer(hostname, 37321);

        StandardJSONRequestor requestor = new StandardJSONRequestor(crh);
        Game gameProxy = new GameProxy(requestor);

        DrawingEditor editor =
                new MiniDrawApplication( "Semi Civ",
                        new HotCivFactory4(gameProxy) );
        editor.open();

        editor.setTool(new CompositionTool(editor, gameProxy));
    }

    private void testSimpleMethods(Game game) {
        System.out.println("=== Testing simple methods ===");
        System.out.println(" -> Game age    " + game.getAge());
        System.out.println(" -> Game winner " + game.getWinner());
        System.out.println(" -> Game PIT    " + game.getPlayerInTurn());

        System.out.println(" -> Red city owner: " + game.getCityAt(DELTA_RED_CITY_POS).getOwner());
        System.out.println(" -> Blue city owner: " + game.getCityAt(DELTA_BLUE_CITY_POS).getOwner());
        System.out.println(" -> Tile 0,0 type is: " + game.getTileAt(new Position(0,0)).getTypeString());
        System.out.println(" -> Tile 0,5 type is: " + game.getTileAt(new Position(0,5)).getTypeString());

        game.endOfTurn();
        System.out.println(" -> Now PIT after endOfTurn " + game.getPlayerInTurn());
    }
}
