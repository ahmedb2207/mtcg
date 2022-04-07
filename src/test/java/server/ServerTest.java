package server;

import app.App;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServerTest{

    @Test
    void runFalse() {
      App app = new App();
      Server server = new Server(10001, app) ;
        assertFalse(server.isRunning);
    }
}