package hotciv.standard.broker;

import frds.broker.ClientRequestHandler;
import frds.broker.ipc.socket.SocketClientRequestHandler;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotciv.view.tool.CompositionTool;
import hotciv.visual.HotCivFactory4;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;

public class SemiCivDistributed {
    public static void main(String[] args) {
        new SemiCivDistributed(args[0]);
    }

    public SemiCivDistributed(String hostname) {
        System.out.println("=== SemiCiv MANUAL TEST Client (Socket) (host:" + hostname + ") ===");

        ClientRequestHandler crh = new SocketClientRequestHandler();
        crh.setServer(hostname, 37321);

        StandardJSONRequestor requestor = new StandardJSONRequestor(crh);
        GameProxy gameProxy = new GameProxy(requestor);

        DrawingEditor editor =
                new MiniDrawApplication( "Semi Civ",
                        new HotCivFactory4(gameProxy) ); // TODO HotCivFactory4???
        editor.open();
        editor.showStatus("Click and drag any item to see Game's proper response.");

        editor.setTool(new CompositionTool(editor, gameProxy));
    }
}
