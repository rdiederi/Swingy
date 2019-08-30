package za.co.swingy.controller;


import za.co.swingy.model.Hero;
import za.co.swingy.view.ConsoleView;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class ConsoleController {

    public static Hero nameHero(int type, Scanner input) {

        Factory factory = new Factory();
        String[] types = {"Druid", "Hunter", "DeathNight"};

        System.out.println("Give your " + types[type - 1] + " a name:");
        System.out.print(">> ");
        String name = input.nextLine();

       return factory.newHero(types[type - 1], name);

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


    public static void startGame(Hero hero, Scanner input) throws SQLException {
        Factory factory = new Factory();
        Map map;
        map = factory.newMap(hero);
        String move;

        while(true)
        {
            ConsoleView.printHeroStats(hero);
            ConsoleView.drawMap(map.getDimension(), map.getMap());
            System.out.println("North (n)|South (s)|East (e)|West (w)");
            move = input.next();

            map.moveHero(move, map, hero);
            System.out.println(hero.getX() + " " + hero.getY());
//            clearConsole();
        }
    }

    public static void gameLoop() throws IOException, SQLException {
       Hero hero;
       StorageController sc = new StorageController();
       sc.createDB();
       sc.createTB();

        while (true){
            ConsoleView.printWelcome();
            System.out.print(">> ");
            Scanner input = new Scanner(System.in);
            int cmd = input.nextInt();
            switch (cmd){
                case 1:
                   hero = createHero(sc);
                    startGame(hero, input);
//                    hero.setLvl(7);

                    break;
                case 2:
                    ConsoleView.printSavedHeroes(sc.loadGameData());
                    System.out.print("\n>> ");
                    cmd = input.nextInt();
                    hero = StorageController.loadHero(sc.loadGameData(), cmd);
//                    startGame(hero);
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
