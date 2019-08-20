package controller;

import java.lang.reflect.Array;
import java.util.Scanner;
import model.*;
import view.*;

public class Main{

//    public static int Class (){
//        System.out.println("1) Death knight.\n" +
//                           "2) model.Druid.\n" +
//                           "3) model.Hunter.");
//    }

    public static Warrior nameHero(int type, Scanner input) {
        Warrior hero = null;

        String types[] = {"Druid", "DeathNight", "Hunter"};

        System.out.println("Give your " + types[type - 1] + " a name:");
        System.out.print(">> ");
        String name = input.nextLine();
        switch (type) {
            case 1:
                hero = new Druid(name);
                break;
            case 2:
                hero = new DeathNight(name);
                break;
            case 3:
                hero = new Hunter(name);
                break;

        }

        return hero;
    }
    public static Warrior createHero() {

        String name;
        Warrior hero = null;

        Scanner input = new Scanner(System.in);
        String cmd = input.nextLine();

        //Save Selection
        if (Integer.parseInt(cmd) > 0 && Integer.parseInt(cmd) < 4) {
            hero = nameHero(Integer.parseInt(cmd), input);
        } else {
            System.out.println("Wrong! Try Again!");
            createHero();
        }
//        switch (cmd) {
//            case 1:
////                System.out.println("Give your Druid a name:");
////                System.out.print(">> ");
////                name = input.nextLine();
////                hero = new Druid(name);
//                hero = nameHero(Integer.parseInt(cmd), input);
//                break;
//            case 2:
////                System.out.println("Give your DeathNight a name:");
////                System.out.print(">> ");
////                name = input.nextLine();
////                hero = new DeathNight(name);
//                hero = nameHero(Integer.parseInt(cmd), input);
//                break;
//            case 3:
////                System.out.println("Give your Hunter a name:");
////                System.out.print(">> ");
////                name = input.nextLine();
////                hero = new Hunter(name);
//                hero = nameHero(Integer.parseInt(cmd), input);
//                break;
//            default:
//                System.out.println("Wrong! Try Again!");
//                createHero();
//                break;
//        }
        return hero;
    }

        public static void main(String[] args) {
        Warrior hero;

        ConsoleView.printWelcome();
        System.out.print(">> ");
        while (true){
            Scanner input = new Scanner(System.in);
            int cmd = input.nextInt();
            switch (cmd){
                case 1:
                    //Method Choose model.Hero
                    ConsoleView.printHeroClasses();
                    System.out.print(">> ");
                    hero = createHero();
                    System.out.println(hero.getName());
                    System.out.println("3) Exit");
                    break;
                case 2:
                    //Load Game
                    System.out.println("Load your game!");
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
