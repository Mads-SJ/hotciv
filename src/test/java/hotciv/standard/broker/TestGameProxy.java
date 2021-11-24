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
    private StubBrokerGame gameServant;
    private Position fakePosition;

    @BeforeEach
    public void setup() {
        gameServant = new StubBrokerGame();
        GameObserver nullObserver = new NullObserver();
        gameServant.addObserver(nullObserver);

        Invoker invoker = new GameInvoker(gameServant, new InMemoryNameService());

        ClientRequestHandler crh = new LocalMethodClientRequestHandler(invoker);

        Requestor requestor = new StandardJSONRequestor(crh);

        game = new GameProxy(requestor);
        game.addObserver(nullObserver);

        fakePosition = new Position(100, 100);
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

    @Test
    public void shouldMove() {
        boolean hasMoved = game.moveUnit(new Position(2,2), new Position(2,3));
        assertThat(hasMoved, is(true));
    }

    @Test
    public void shouldHaveEndOfTurn() {
        game.endOfTurn();
        assertThat(gameServant.getLastVoidMethodCalled(), is("endOfTurn"));
    }

    @Test
    public void shouldHaveChangeWorkForceFocusInCityAt() {
        game.changeWorkForceFocusInCityAt(fakePosition, "gold");
        assertThat(gameServant.getLastVoidMethodCalled(), is("changeWorkForceFocusInCityAt"));
    }

    @Test
    public void shouldHaveChangeProductionInCityAt() {
        game.changeProductionInCityAt(fakePosition, "F16");
        assertThat(gameServant.getLastVoidMethodCalled(), is("changeProductionInCityAt"));
    }

    @Test
    public void shouldHavePerformUnitActionAt() {
        game.performUnitActionAt(fakePosition);
        assertThat(gameServant.getLastVoidMethodCalled(), is("performUnitActionAt"));
    }

    @Test
    public void shouldHaveTileAt() {
        Tile tile = game.getTileAt(fakePosition);
        assertThat(tile, is(notNullValue()));
    }
}
