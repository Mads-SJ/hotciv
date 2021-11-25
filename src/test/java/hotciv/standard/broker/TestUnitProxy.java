package hotciv.standard.broker;

import frds.broker.ClientRequestHandler;
import frds.broker.Invoker;
import frds.broker.Requestor;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotciv.framework.*;
import hotciv.stub.broker.LocalMethodClientRequestHandler;
import hotciv.stub.broker.StubBrokerUnit;
import org.junit.jupiter.api.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestUnitProxy {
    private Unit unit;

    @BeforeEach
    public void setup() {
        NameService nameService = new InMemoryNameService();
        Invoker invoker = new UnitInvoker(nameService);

        ClientRequestHandler crh = new LocalMethodClientRequestHandler(invoker);

        Requestor requestor = new StandardJSONRequestor(crh);

        String objectId = "singleton";
        unit = new UnitProxy(requestor, objectId);
        nameService.putUnit(objectId, new StubBrokerUnit());
    }

    @Test
    public void shouldHaveTypeString() {
        String typeString = unit.getTypeString();
        assertThat(typeString, is("F16"));
    }

    @Test
    public void shouldHaveOwner() {
        Player owner = unit.getOwner();
        assertThat(owner, is(Player.YELLOW));
    }

    @Test
    public void shouldHaveMoves() {
        int moveCount = unit.getMoveCount();
        assertThat(moveCount, is(420));
    }

    @Test
    public void shouldHaveDefensiveStrength() {
        int defensiveStrength = unit.getDefensiveStrength();
        assertThat(defensiveStrength, is(50));
    }

    @Test
    public void shouldHaveAttackingStrength() {
        int attackingStrength = unit.getAttackingStrength();
        assertThat(attackingStrength, is(100));
    }
}
