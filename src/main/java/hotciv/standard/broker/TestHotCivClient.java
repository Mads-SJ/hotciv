package hotciv.standard.broker;

import frds.broker.ClientRequestHandler;
import frds.broker.ipc.socket.SocketClientRequestHandler;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotciv.framework.Game;
import hotciv.framework.Position;

public class TestHotCivClient {
    public static void main(String[] args) {
        new TestHotCivClient(args[0]);
    }

    public TestHotCivClient(String hostname) {
        System.out.println("=== HotCiv MANUAL TEST Client (Socket) (host:" + hostname + ") ===");

        ClientRequestHandler crh = new SocketClientRequestHandler();
        crh.setServer(hostname, 37321);

        StandardJSONRequestor requestor = new StandardJSONRequestor(crh);
        GameProxy gameProxy = new GameProxy(requestor);

        testSimpleMethods(gameProxy);
    }

    private void testSimpleMethods(Game game) {
        System.out.println("=== Testing simple methods ===");
        System.out.println(" -> Game age    " + game.getAge());
        System.out.println(" -> Game winner " + game.getWinner());
        System.out.println(" -> Game PIT    " + game.getPlayerInTurn());
        System.out.println(" -> Game move   " + game.moveUnit(new Position(0,0), new Position(1,0)));

        game.endOfTurn();
        System.out.println(" -> Now PIT after endOfTurn " + game.getPlayerInTurn());
    }
}
