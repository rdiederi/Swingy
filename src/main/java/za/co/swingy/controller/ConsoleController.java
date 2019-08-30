package za.co.swingy.controller;


import za.co.swingy.model.Hero;
import za.co.swingy.view.ConsoleView;
import za.co.swingy.model.ValidationModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class ConsoleController {
    public static String CONS_RESET = "\033[H\033[2J";

    public static Hero nameHero(int type, Scanner input) {

        Factory factory = new Factory();
        String[] types = {"Druid", "Hunter", "DeathNight"};

        System.out.println("Give your " + types[type - 1] + " a name:");
        System.out.print(">> ");
        String name = input.nextLine();

       return factory.newHero(types[type - 1], name);
    }

    public static Hero createHero(StorageController sc) {
        ValidationModel valid = new ValidationModel();
        Hero hero = null;

        ConsoleView.printHeroClasses();
        System.out.print(">> ");

        Scanner input = new Scanner(System.in);
        int cmd = input.nextInt();
        //Save Selection

        if (!valid.intValidator(cmd, "createHeroClass")){
            System.out.println("Wrong! Try Again!");
            createHero(sc);
        }

        hero = nameHero(cmd, input);
        sc.saveHero(hero);
        sc.setHeroId(hero);
        return hero;
    }


    public static boolean isEmpty(int x, int y, int map[][])
    {
        if (map[x][y] != 0)
            return (false);
        return true;
    }

//    public static boolean isEnemy() {
//        if ("X".equalsIgnoreCase(map[y][x])) {
//            return (true);
//        }
//        return (false);
//    }

//    public static boolean isItem() {
//        if ("C".equalsIgnoreCase(map[y][x])) {
//            return (true);
//        }
//        return (false);
//    }


    public static void startGame(Hero hero, Scanner input) throws SQLException {
        Factory factory = new Factory();
        Map map;
        map = factory.newMap(hero);
        String move;

        while(true)
        {
            System.out.print(CONS_RESET);
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
       ValidationModel valid = new ValidationModel();

        while (true){
            ConsoleView.printWelcome();
            System.out.print(">> ");
            Scanner input = new Scanner(System.in);
            int cmd = input.nextInt();
//            if(!valid.intValidator(cmd, "gameLoop")){
//                ConsoleView.printWelcome();
//            }

            switch (cmd){
                case 1:
                    System.out.print(CONS_RESET);
                    hero = createHero(sc);
                    startGame(hero, input);
//                    hero.setLvl(7);

                    break;
                case 2:
                    System.out.print(CONS_RESET);
                    ConsoleView.printSavedHeroes(sc.loadGameData());
                    System.out.print("\n>> ");
                    cmd = input.nextInt();
                    if (!valid.intValidator(cmd, "createHeroClass"))
                        ConsoleView.printWelcome();
                    hero = StorageController.loadHero(sc.loadGameData(), cmd);
//                    startGame(hero);
                    break;
                case 3:
                    //Exit
                    System.out.println("Good Bye!!\nCome again");
                    System.exit(0);
                    break;
            }
        }
    }
}
