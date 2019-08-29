package za.co.swingy.controller;


import za.co.swingy.model.Hero;
import za.co.swingy.view.ConsoleView;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
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


    public static boolean isEmpty(int x, int y, int map[][])
    {
        if (map[x][y] != 0)
            return (false);
        return true;
    }

    private static void moveUp(int y)
    {
        y--;
    }

    private static void moveDown(int y)
    {
        y++;
    }

    private static void moveLeft(int x)
    {
        x--;
    }

    private static void moveRight(int x)
    {
        x++;
    }

    public static boolean isEnemy() {
        if ("X".equalsIgnoreCase(map[y][x])) {
            return (true);
        }
        return (false);
    }

    public static boolean isItem() {
        if ("C".equalsIgnoreCase(map[y][x])) {
            return (true);
        }
        return (false);
    }

    public static void newMap() {
        ConsoleMap consoleMap = new ConsoleMap();
        dimension = consoleMap.getMapDimensions(++level);
        middle = ((dimension - 1) / 2);
        map = consoleMap.generateMap(dimension, middle);
        x = middle;
        y = middle;
    }

    public  static void moveHero(String direction, String[][] map)
    {
        String[] directions = new String[]{"n", "s", "e", "w"};
        boolean result = Arrays.stream(directions).anyMatch(direction::equals);


        if (result) {
            map[y][x] = ".";
            if (!edgeOfMap()) {
                if (direction.equals("n")) {
                    moveUp();
                    if(isItem()) {
                        applyItem(item);
                    } else if (isEnemy()) {
                        // Fight or flight
                    }
                }
                else if (direction.equals("s")) {
                    moveDown();
                    if(isItem()) {
                        item = new DropItems(level).getItem();
                        applyItem(item);
                    } else if (isEnemy()) {
                        // Fight or flight
                    }
                }
                else if (direction.equals("e")) {
                    moveRight();
                    if(isItem()) {
                        item = new DropItems(level).getItem();
                        applyItem(item);
                    } else if (isEnemy()) {
                        // Fight or flight
                    }
                }
                else if (direction.equals("w")) {
                    moveLeft();
                    if(isItem()) {
                        item = new DropItems(level).getItem();
                        applyItem(item);
                    } else if (isEnemy()) {
                        // Fight or flight
                    }
                }
                map[y][x] = "1";
            }
            else {
                newMap();
            }
        }
    }

    public static void startGame(Hero hero, String[][] map) throws SQLException {
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
                    System.out.print("\n>> ");
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
