package za.co.swingy.controller;

import za.co.swingy.model.Hero;
import za.co.swingy.model.ValidationModel;
import za.co.swingy.view.ConsoleView;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleController {
    private static Hero hero;
    private static StorageController sc = new StorageController();
    private static Factory factory = new Factory();
    private static Map map = new Map();

    private static void fight(Hero hero, String enemy)  throws SQLException {
        StorageController sc = new StorageController();

        boolean probability = Math.random() < 0.5;

        if (probability){
            System.out.println("You killed "+ enemy);
            hero.gainXp(50);
        }
        else{
            ConsoleView.printGameOver(enemy);
            System.out.println("Good Bye!!!!!");
            System.out.println("b) back to main menu");
            Scanner input = new Scanner(System.in);
            String la = input.next();
            StorageController.deleteGame(hero);
            ConsoleController.gameLoop();
        }
    }

    private static void fightOrFlight(Hero hero, String direction) throws SQLException, InputMismatchException{
        try {
            int min = 0;
            int max = 4;
            int range = max - min - 1;

            String[] enemy = {"Bob", "Larry", "Jeff", "Justin", "Mufaro"};
            int rand = (int) (Math.random() * range) + min;
            boolean possibility = Math.random() < 0.5;

            ConsoleView.printFightOrFlight(enemy[rand]);
            Scanner input = new Scanner(System.in);
            int cmd = input.nextInt();

            if (cmd == 1) {
                fight(hero, enemy[rand]);
            } else if (cmd == 2) {
                if (possibility) {
                    goBack(hero, direction);
                    System.out.print(ConsoleView.CLR_CLI);
                } else {
                    ConsoleView.printToughShit();
                    fight(hero, enemy[rand]);
                }
            }
        }catch (InputMismatchException e) {
            System.out.println("Not a valid command");
            fightOrFlight(hero, direction);
        }
    }

    private static void goBack(Hero hero, String direction) {
        if (direction.equals("n"))
            map.moveDown(hero);
        if (direction.equals("e"))
            map.moveLeft(hero);
        if (direction.equals("s"))
            map.moveUp(hero);
        if (direction.equals("w"))
            map.moveRight(hero);
    }

    private static void moveHero(String direction, Map mapObj, Hero hero) throws SQLException
    {
        String[] directions = new String[]{"n", "s", "e", "w"};
        boolean result = Arrays.asList(directions).contains(direction);
        String[][] lvl = mapObj.getMap();
        Factory factory = new Factory();

        if (result) {
            lvl[hero.getY()][hero.getX()] = ".";
            if (!mapObj.edgeOfMap(hero)) {
                switch (direction) {
                    case "n":
                        map.moveUp(hero);
                        if(map.isItem()) {
                            factory.newItem().applyItem(hero);
                        } else if (map.isEnemy()) {
                            // Fight or flight
                            fightOrFlight(hero,direction);
                        }
                        break;
                    case "s":
                        map.moveDown(hero);
                        if(map.isItem()) {
                            factory.newItem().applyItem(hero);
                        } else if (map.isEnemy()) {
                            // Fight or flight
                            fightOrFlight(hero,direction);
                        }
                        break;
                    case "e":
                        map.moveRight(hero);
                        if(map.isItem()) {
                            factory.newItem().applyItem(hero);
                        } else if (map.isEnemy()) {
                            // Fight or flight
                            fightOrFlight(hero,direction);
                        }
                        break;
                    case "w":
                        map.moveLeft(hero);
                        if(map.isItem()) {
                            factory.newItem().applyItem(hero);
                        } else if (map.isEnemy()) {
                            // Fight or flight
                            fightOrFlight(hero,direction);
                        }
                        break;
                }
                lvl[hero.getY()][hero.getX()] = "1";
            }
            else {
                hero.gainXp(hero.getLvl() * 555);
                mapObj.newMap(hero);
            }
        }
    }

    private static Hero nameHero(int type) {

        String[] types = {
            "Druid",
            "Hunter",
            "DeathNight"
        };
        Scanner input = new Scanner(System.in);
        System.out.println("Give your " + types[type - 1] + " a name:");
        System.out.print(">> ");
        String name = input.nextLine();

        ValidationModel validationModel = new ValidationModel(name, 4);
        if (validationModel.validator(0, name, 4)) {
            return factory.newHero(types[type - 1], name);
        }
        return nameHero(type);
    }

    private static Hero createHero(StorageController sc) throws NumberFormatException {
        Hero hero;
        System.out.print(ConsoleView.CLR_CLI);
        ConsoleView.printHeroClasses();
        System.out.print(">> ");

        try {
            Scanner input = new Scanner(System.in);
            String result = input.nextLine();
            int cmd = Integer.parseInt(result);
            ValidationModel validationModel = new ValidationModel(cmd, 2);

            if (!validationModel.validator(cmd, null, 2))
                return createHero(sc);

            hero = nameHero(cmd);
            sc.saveHero(hero);
        } catch (NumberFormatException e){
            return createHero(sc);
        }
        return hero;
    }

    private static void startGame(Hero hero) throws InputMismatchException, SQLException {
        map.setMap(hero);
        String move;
        Scanner input = new Scanner(System.in);

        System.out.print(ConsoleView.CLR_CLI);
        while (true) {
            ConsoleView.printHeroStats(hero);
            ConsoleView.drawMap(map.getDimension(), map.getMap());
            System.out.println("North (n)|South (s)|East (e)|West (w)");
            move = input.next();
            System.out.print(ConsoleView.CLR_CLI);
            ValidationModel validationModel = new ValidationModel(move, 5);
            if (!validationModel.validator(0, move, 5))
                continue;
            moveHero(move, map, hero);
        }
    }

    public static void gameLoop() throws SQLException, InputMismatchException {
        sc.createDB();
        sc.createTB();
        System.out.print(ConsoleView.CLR_CLI);
        while (true) {
            try {
                ConsoleView.printWelcome();
                System.out.print(">> ");
                Scanner input = new Scanner(System.in);
                int cmd = input.nextInt();
                System.out.print(ConsoleView.CLR_CLI);
                ValidationModel validationModel = new ValidationModel(cmd, 1);

                if (!validationModel.validator(cmd, null, 1))
                    continue;

                switch (cmd) {
                    case 1:
                        hero = createHero(sc);
                        startGame(hero);
                        break;
                    case 2:
                        System.out.print(ConsoleView.CLR_CLI);
//                        if (sc.loadGameData() == null){
//                            System.out.println("No games found");
//                            System.out.println("b) back");
//                            String la = input.next();
//                            System.out.print(ConsoleView.CLR_CLI);
//                            continue;
//                        }
                        ConsoleView.printSavedHeroes(sc.loadGameData());
                        System.out.print("\n>> ");
                        cmd = input.nextInt();
                        hero = StorageController.loadHero(sc.loadGameData(), cmd);
                        startGame(hero);
                        break;
                    case 3:
                        //Exit
                        System.out.print(ConsoleView.CLR_CLI);
                        System.out.println("Good Bye!!\nCome again");
                        System.exit(0);
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.print(ConsoleView.CLR_CLI);
            }
        }
    }
}