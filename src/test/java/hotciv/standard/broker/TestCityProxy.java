package hotciv.standard.broker;

import frds.broker.ClientRequestHandler;
import frds.broker.Invoker;
import frds.broker.Requestor;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotciv.framework.City;
import hotciv.stub.LocalMethodClientRequestHandler;
import hotciv.stub.StubBrokerCity;
import org.junit.jupiter.api.BeforeEach;

public class TestCityProxy {
    City city;

    @BeforeEach
    public void setup() {
        City cityServant = new StubBrokerCity();

        Invoker invoker = new CityInvoker(cityServant);

        ClientRequestHandler crh = new LocalMethodClientRequestHandler(invoker);

        Requestor requestor = new StandardJSONRequestor(crh);

        city = new CityProxy(requestor);
    }
}
