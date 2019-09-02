package za.co.swingy.controller;


import za.co.swingy.model.Hero;
import za.co.swingy.view.ConsoleView;
import za.co.swingy.view.ConsoleView.*;
import za.co.swingy.model.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class ConsoleController {

    public static Hero nameHero(int type) {

        Factory factory = new Factory();
        String[] types = {"Druid", "Hunter", "DeathNight"};
        Scanner input = new Scanner(System.in);
        System.out.println("Give your " + types[type - 1] + " a name:");
        System.out.print(">> ");
        String name = input.nextLine();
//        input.close();
        ValidationModel val_1 = new ValidationModel(name, 4);
        if (val_1.validator(0, name, 4))
            return factory.newHero(types[type - 1], name);
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
        ValidationModel val_1 = new ValidationModel(cmd, 2);

        //Save Selection
        if (val_1.validator(cmd, "", 2)){
            hero = nameHero(cmd);
            sc.saveHero(hero);
            return hero;
        }

        createHero(sc);
        input.close();
        return null;
    }

    public static void startGame(Hero hero) throws SQLException {
        Factory factory = new Factory();
        Map map;
        map = factory.newMap(hero);
        String move;
        Scanner input = new Scanner(System.in);

        while(true)
        {
            System.out.print(ConsoleView.CLR_CLI);
            ConsoleView.printHeroStats(hero);
            ConsoleView.drawMap(map.getDimension(), map.getMap());
            System.out.println("North (n)|South (s)|East (e)|West (w)");
            move = input.next();
            ValidationModel val_1 = new ValidationModel(move, 5);
            if (!val_1.validator(0, move, 5))
                continue;
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
            System.out.print(ConsoleView.CLR_CLI);
            ConsoleView.printWelcome();
            System.out.print(">> ");
            Scanner input = new Scanner(System.in);
            int cmd = input.nextInt();
            ValidationModel val_1 = new ValidationModel(cmd, 1);

            if (!val_1.validator(cmd, "", 1))
                continue;

            switch (cmd){
                case 1:
                   hero = createHero(sc);
                    startGame(hero);
//                    hero.setLvl(7);
                    break;
                case 2:
                    System.out.print(ConsoleView.CLR_CLI);
                    ConsoleView.printSavedHeroes(sc.loadGameData());
                    System.out.print("\n>> ");
                    cmd = input.nextInt();
                    hero = StorageController.loadHero(sc.loadGameData(), cmd);
//                    startGame(hero);
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
