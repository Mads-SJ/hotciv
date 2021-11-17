package hotciv.standard.broker;

import frds.broker.ClientRequestHandler;
import frds.broker.Invoker;
import frds.broker.Requestor;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotciv.framework.*;
import hotciv.stub.broker.LocalMethodClientRequestHandler;
import hotciv.stub.broker.NullObserver;
import hotciv.stub.broker.StubBrokerGame;
import org.junit.jupiter.api.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestGameProxy {
    private Game game;

    @BeforeEach
    public void setup() {
        Game gameServant = new StubBrokerGame();
        GameObserver nullObserver = new NullObserver();
        gameServant.addObserver(nullObserver);

        Invoker invoker = new GameInvoker(gameServant);

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

    @Test
    public void shouldHaveAge() {
        int age = game.getAge();
        assertThat(age, is(42));
    }

    @Test
    public void shouldHavePlayerInTurn() {
        Player player = game.getPlayerInTurn();
        assertThat(player, is(Player.GREEN));
    }

    /*
    @Test
    public void shouldHaveCity() {
        City city = game.getCityAt(new Position(1,1));
        assertThat(city.getOwner(), is(Player.GREEN));
    }
     */

    @Test
    public void shouldMove() {
        boolean hasMoved = game.moveUnit(new Position(2,2), new Position(2,3));
        assertThat(hasMoved, is(true));
    }

    @Test
    public void manualTesting() {
        game.endOfTurn(); // TODO: empty todo
        assert(true);
    }
}
