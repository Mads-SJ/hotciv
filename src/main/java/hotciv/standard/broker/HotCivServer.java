package hotciv.standard.broker;

import frds.broker.Invoker;
import frds.broker.ipc.socket.SocketServerRequestHandler;
import hotciv.framework.Game;
import hotciv.stub.broker.StubBrokerGame;

public class HotCivServer {
    private static Thread daemon;

    public static void main(String[] args) throws Exception {
        new HotCivServer(); // No error handling!
    }


    public HotCivServer() {
        int port = 37321;

        // Define the server side delegates
        Game gameServant = new StubBrokerGame();
        Invoker invoker = new GameInvoker(gameServant);

        // Configure a socket based server request handler
        SocketServerRequestHandler ssrh =
                new SocketServerRequestHandler();
        ssrh.setPortAndInvoker(port, invoker);

        // Welcome
        // Welcome
        System.out.println("=== TeleMed Socket based Server Request Handler (port:"
                + port + ") ===");
        System.out.println(" Use ctrl-c to terminate!");
        ssrh.start();

    }
}
