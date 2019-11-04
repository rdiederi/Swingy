package za.co.swingy;

import za.co.swingy.controller.ConsoleController;

import java.io.IOException;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws IOException, SQLException {
        if (args.length >= 1) {
            if (args[0].equalsIgnoreCase("console")) {
                ConsoleController.gameLoop();
            } else if (args[0].equalsIgnoreCase("gui")) {
                System.out.println("this will be a gui");
            }
        }
        else {
            ConsoleController.gameLoop();
        }
    }
}
