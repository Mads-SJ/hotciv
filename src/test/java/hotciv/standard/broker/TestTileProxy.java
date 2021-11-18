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
        Tile tileServant = new StubBrokerTile();

        Invoker invoker = new TileInvoker(tileServant);

        ClientRequestHandler crh = new LocalMethodClientRequestHandler(invoker);

        Requestor requestor = new StandardJSONRequestor(crh);

        tile = new TileProxy(requestor);
    }

    @Test
    public void shouldHaveTypeString() {
        String typeString = tile.getTypeString();
        assertThat(typeString, is("lava"));
    }
}
