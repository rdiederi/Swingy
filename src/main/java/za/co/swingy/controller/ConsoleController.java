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
            sc.setHeroId(hero);
            return hero;
        } else {
            System.out.println("Wrong! Try Again!");
            createHero(sc);
        }
        return null;
    }

    public static void startGame(Hero hero) throws SQLException {
        hero.setAttack(500);
        hero.setDefense(800);
        hero.setLvl(17);
        hero.setXp(18);
        StorageController.updateStats(hero);
        ConsoleView.printHeroStats(hero);
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
                    startGame(hero);
                    break;
                case 2:
                    ConsoleView.printSavedHeroes(sc.loadGameData());
                    cmd = input.nextInt();
                    hero = StorageController.loadHero(sc.loadGameData(), cmd);
                    startGame(hero);
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
