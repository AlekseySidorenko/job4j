package ru.job4j.io.sockets;

import com.google.common.base.Joiner;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Class ServerTest | Task Solution: Bot [#7921]
 * @author @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 28.05.2019
 */
public class ServerTest {

    private static final String SEPARATOR = System.lineSeparator();

    private void testServer(String input, String expected) throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        Server server = new Server(socket);
        server.start();
        assertThat(out.toString(), is(expected));
    }

    @Test
    public void whenSendExitThenGetExitAnswer() throws IOException {
        this.testServer("exit", "See u next time" + SEPARATOR);
    }

    @Test
    public void whenSendHelloThenGetHelloAnswer() throws IOException {
        this.testServer(
                Joiner.on(SEPARATOR).join(
                        "hello",
                        "exit"
                ),
                Joiner.on(SEPARATOR).join(
                        "Hello, my friend",
                        "See u next time",
                        ""
                )
        );
    }

    @Test
    public void whenSendAnyRequestThenGetRandomAnswer() throws IOException {
        Socket socket = mock(Socket.class);
        String request = "sample" + SEPARATOR + "exit";
        ByteArrayInputStream in = new ByteArrayInputStream(request.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        String random1 = "Do your best" + SEPARATOR + "See u next time" + SEPARATOR;
        String random2 = "Kill them all" + SEPARATOR + "See u next time" + SEPARATOR;
        String random3 = "F*ck it" + SEPARATOR + "See u next time" + SEPARATOR;
        Server server = new Server(socket);
        server.start();
        assertTrue(out.toString().equalsIgnoreCase(random1)
                || out.toString().equalsIgnoreCase(random2)
                || out.toString().equalsIgnoreCase(random3));
    }
}