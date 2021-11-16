package hotciv.standard;

import frds.broker.ClientRequestHandler;
import frds.broker.Invoker;
import frds.broker.Requestor;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotciv.framework.Game;
import hotciv.framework.GameObserver;
import hotciv.framework.Player;
import hotciv.stub.LocalMethodClientRequestHandler;
import hotciv.stub.NullObserver;
import hotciv.stub.StubBrokerGame;
import org.junit.jupiter.api.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestBroker {
    Game game;

    @BeforeEach
    public void setup() {
        Game servant = new StubBrokerGame();
        GameObserver nullObserver = new NullObserver();
        servant.addObserver(nullObserver);

        Invoker invoker = new HotCivGameInvoker(servant);

        ClientRequestHandler crh = new LocalMethodClientRequestHandler(invoker);

        Requestor requestor = new StandardJSONRequestor(crh);

        game = new GameProxy(requestor);
        game.addObserver(nullObserver);
    }

    @Test
    public void shouldHaveWinner() {
        Player winner = game.getWinner();
        assertThat(winner, is(Player.YELLOW));
    }
}
