package hotciv.standard.broker;

import frds.broker.ClientRequestHandler;
import frds.broker.Invoker;
import frds.broker.Requestor;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotciv.framework.*;
import hotciv.stub.broker.LocalMethodClientRequestHandler;
import hotciv.stub.broker.StubBrokerCity;
import org.junit.jupiter.api.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestCityProxy {
    private City city;

    @BeforeEach
    public void setup() {
        NameService nameService = new InMemoryNameService();
        Invoker invoker = new CityInvoker(nameService);

        ClientRequestHandler crh = new LocalMethodClientRequestHandler(invoker);

        Requestor requestor = new StandardJSONRequestor(crh);

        String objectId = "singleton";
        city = new CityProxy(requestor, objectId);
        nameService.putCity(objectId, new StubBrokerCity());
    }

    @Test
    public void shouldHaveOwner() {
        Player owner = city.getOwner();
        assertThat(owner, is(Player.GREEN));
    }

    @Test
    public void shouldHavePopulation() {
        int size = city.getSize();
        assertThat(size, is(4));
    }

    @Test
    public void shouldHaveTreasury() {
        int amount = city.getTreasury();
        assertThat(amount, is(69));
    }

    @Test
    public void shouldHaveProduction() {
        String production = city.getProduction();
        assertThat(production, is("F16"));
    }

    @Test
    public void shouldHaveWorkforceFocus() {
        String workforce = city.getWorkforceFocus();
        assertThat(workforce, is("gold"));
    }
}
