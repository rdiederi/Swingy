package za.co.swingy.controller;


import za.co.swingy.model.Hero;
import za.co.swingy.model.ValidationModel;
import za.co.swingy.view.ConsoleView;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleController {
    static Hero hero;
    static StorageController sc = new StorageController();
    static Factory factory = new Factory();

    public static Hero nameHero(int type) {

        String[] types = {"Druid", "Hunter", "DeathNight"};
        Scanner input = new Scanner(System.in);
        System.out.println("Give your " + types[type - 1] + " a name:");
        System.out.print(">> ");
        String name = input.nextLine();
//        input.close();
        ValidationModel validationModel = new ValidationModel(name, 4);
        if (validationModel.validator(0, name, 4)) {
            return factory.newHero(types[type - 1], name);
        }
        nameHero(type);
        return null;
    }

    public static Hero createHero(StorageController sc) {
        Hero hero = null;
        System.out.print(ConsoleView.CLR_CLI);
        ConsoleView.printHeroClasses();
        System.out.print(">> ");
        
        Scanner input = new Scanner(System.in);
        int cmd = input.nextInt();
        ValidationModel validationModel = new ValidationModel(cmd, 2);

        //Save Selection
        if (validationModel.validator(cmd, null, 2)){
            hero = nameHero(cmd);
            sc.saveHero(hero);
            return hero;
        }

        createHero(sc);
        input.close();
        return null;
    }

    public static void startGame(Hero hero) throws InputMismatchException {
        Map map;
        map = factory.newMap(hero);
        String move;
        Scanner input = new Scanner(System.in);

        System.out.print(ConsoleView.CLR_CLI);
        while(true)
        {
            ConsoleView.printHeroStats(hero);
            ConsoleView.drawMap(map.getDimension(), map.getMap());
            System.out.println("North (n)|South (s)|East (e)|West (w)");
            move = input.next();
            System.out.print(ConsoleView.CLR_CLI);
            ValidationModel validationModel = new ValidationModel(move, 5);
            if (!validationModel.validator(0, move, 5))
                continue;
            map.moveHero(move, map, hero);
        }
    }

    public static void gameLoop() throws SQLException, InputMismatchException {
        sc.createDB();
        sc.createTB();

        while (true){
            System.out.print(ConsoleView.CLR_CLI);
            ConsoleView.printWelcome();
            System.out.print(">> ");
            Scanner input = new Scanner(System.in);
            int cmd = input.nextInt();
            ValidationModel validationModel = new ValidationModel(cmd, 1);

            if (!validationModel.validator(cmd, null, 1))
                continue;

            switch (cmd){
                case 1:
                   hero = createHero(sc);
                    startGame(hero);
                    break;
                case 2:
                    System.out.print(ConsoleView.CLR_CLI);
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
        }
    }
}
