package hotciv.standard.broker;

import frds.broker.ClientRequestHandler;
import frds.broker.Invoker;
import frds.broker.Requestor;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotciv.framework.*;
import hotciv.stub.broker.LocalMethodClientRequestHandler;
import hotciv.stub.broker.StubBrokerTile;
import org.junit.jupiter.api.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestTileProxy {
    private Tile tile;

    @BeforeEach
    public void setup() {
        NameService nameService = new InMemoryNameService();
        Invoker invoker = new TileInvoker(nameService);

        ClientRequestHandler crh = new LocalMethodClientRequestHandler(invoker);

        Requestor requestor = new StandardJSONRequestor(crh);

        String objectId = "singleton";
        tile = new TileProxy(requestor, objectId);
        nameService.putTile(objectId, new StubBrokerTile());
    }

    @Test
    public void shouldHaveTypeString() {
        String typeString = tile.getTypeString();
        assertThat(typeString, is("lava"));
    }
}
