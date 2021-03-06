package test;

import java.net.URI;
import java.net.URISyntaxException;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_10;
import org.java_websocket.handshake.ServerHandshake;

/**
 * This example demonstrates how to create a websocket connection to a server.
 * Only the most important callbacks are overloaded.
 */
public class ExampleClient extends WebSocketClient {

    public ExampleClient(URI serverUri, Draft draft, String Uname) {
        super(serverUri, draft, Uname);
    }

    public ExampleClient(URI serverURI, String Uname) {
        super(serverURI, Uname);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("opened connection");
        // if you pan to refuse connection based on ip or httpfields overload:
        // onWebsocketHandshakeReceivedAsClient
    }

    @Override
    public void onMessage(String message) {
        System.out.println("received: " + message);
        // send( "you said: " + message );
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        // The codecodes are documented in class
        // org.java_websocket.framing.CloseFrame
        System.out.println("Connection closed by " + (remote ? "remote peer" : "us"));
    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
        // if the error is fatal then onClose will be called additionally
    }

    public static void main(String[] args) throws URISyntaxException {
        ExampleClient c = new ExampleClient(new URI("ws://localhost:8887"), new Draft_10(), "mName"); // more
        // about
        // drafts
        // here:
        // http://github.com/TooTallNate/Java-WebSocket/wiki/Drafts
        c.connect();
    }

}