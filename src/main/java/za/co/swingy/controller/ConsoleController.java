package za.co.swingy.controller;


import org.hibernate.validator.constraints.Range;
import za.co.swingy.model.Hero;
import za.co.swingy.model.characters.DeathNight;
import za.co.swingy.model.characters.Druid;
import za.co.swingy.model.characters.Hunter;
import za.co.swingy.view.ConsoleView;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class ConsoleController {

    public static Hero nameHero(int type, @NotNull Scanner input) {

        String[] types = {"Druid", "Hunter", "DeathNight"};

        System.out.println("Give your " + types[type - 1] + " a name:");
        System.out.print(">> ");
        String name = input.nextLine();
        switch (type) {
            case 1:
                return new Druid(name);
            case 2:
                return new Hunter(name);
            case 3:
               return new DeathNight(name);
        }
        return null;
    }

    public static Hero createHero(StorageController sc) {

        Hero hero = null;

        ConsoleView.printHeroClasses();
        System.out.print(">> ");

        Scanner input = new Scanner(System.in);
        String cmd = input.nextLine();

        //Save Selection
        if (Integer.parseInt(cmd) > 0 && Integer.parseInt(cmd) < 4) {
            hero = nameHero(Integer.parseInt(cmd), input);
            sc.saveHero(hero);
            return hero;
        } else {
            System.out.println("Wrong! Try Again!");
            createHero(sc);
        }
        return null;
    }

    public static void startGame(Hero hero) {
        ConsoleView.printHeroStats(hero);
    }

    public static void gameLoop() throws IOException, SQLException {
       @NotNull
       Hero hero;
       StorageController sc = new StorageController();
       sc.createDB();
       sc.createTB();

        while (true){
            ConsoleView.printWelcome();
            System.out.print(">> ");
            @Range()
            Scanner input = new Scanner(System.in);
            int cmd = input.nextInt();
            switch (cmd){
                case 1:
                   hero = createHero(sc);
                    startGame(hero);
                    break;
                case 2:
                    //Load Game
                    System.out.println("Load your game!");
                    ConsoleView.printSavedHeroes(sc.loadStoredHeroes());
                    break;
                case 3:
                    //Exit
                    System.out.println("Good Bye!!\nCome again");
                    System.exit(0);
                    break;
                default:
                    System.out.println("You Fucked Up!\nTry Again!\n\n");
                    ConsoleView.printWelcome();
            }
        }
    }
}
