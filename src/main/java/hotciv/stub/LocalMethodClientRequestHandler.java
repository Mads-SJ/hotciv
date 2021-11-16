package hotciv.stub;

import frds.broker.ClientRequestHandler;
import frds.broker.Invoker;

public class LocalMethodClientRequestHandler implements ClientRequestHandler {
    private Invoker invoker;
    private String lastRequest;
    private String lastReply;

    public LocalMethodClientRequestHandler(Invoker invoker) {
        this.invoker = invoker;
    }

    @Override
    public String sendToServerAndAwaitReply(String request) {
        lastRequest = request;
        String reply = invoker.handleRequest(request);
        lastReply = reply;
        return reply;
    }

    @Override
    public void setServer(String hostname, int port) {

    }

    @Override
    public void close() {

    }

    // Methods below are 'test retrieval' methods, used
    // to validate request/replies going through the CRH
    public String getLastRequest() {
        return lastRequest;
    }
    public String getLastReply() {
        return lastReply;
    }

    // TODO: mangler en metode i forhold til interface
}
