package hotciv.standard.broker;

import frds.broker.Invoker;
import frds.broker.ServerRequestHandler;
import frds.broker.ipc.http.UriTunnelServerRequestHandler;
import frds.broker.ipc.socket.SocketServerRequestHandler;
import hotciv.framework.Game;
import hotciv.standard.GameImpl;
import hotciv.stub.broker.StubBrokerGame;
import hotciv.variants.factory.SemiRealFactory;

public class HotCivServer {

    public static void main(String[] args) throws Exception {
        new HotCivServer(); // No error handling!
    }


    public HotCivServer() {
        int port = 37321;

        // Define the server side delegates
        Game gameServant = new GameImpl(new SemiRealFactory());
        Invoker invoker = new RootInvoker(gameServant);

        // Configure a socket based server request handler
        ServerRequestHandler srh =
                new UriTunnelServerRequestHandler(); // TODO ændret fra socket
        srh.setPortAndInvoker(port, invoker);

        // Welcome
        System.out.println("=== HotCiv Socket based Server Request Handler (port:"
                + port + ") ===");
        System.out.println(" Use ctrl-c to terminate!");
        srh.start();
    }
}
