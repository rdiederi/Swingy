import za.co.swingy.controller.ConsoleController;

import java.io.IOException;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws IOException, SQLException {
        ConsoleController.gameLoop();
    }
}
