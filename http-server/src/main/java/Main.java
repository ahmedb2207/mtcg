import app.App;
import server.Server;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        App app = new App();
        Server server = new Server(9988, app);
        try {
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
