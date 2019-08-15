import java.lang.reflect.Array;
import java.util.Scanner;

public class Main{

//    public static int Class (){
//        System.out.println("1) Death knight.\n" +
//                           "2) Druid.\n" +
//                           "3) Hunter.");
//    }

    public static void Hello (){
        System.out.println("Welcome!");
        System.out.println("Choose an option:");
        System.out.println("1) Create a hero");
        System.out.println("2) Select a previously created hero");
        System.out.println("3) Exit");
    }

    public static void createHero(int cmd){
        //Display Hero's
        String[] types = {"Druid", "DeatNight", "Hunter"};
        Hero hero;
        //Save Selection
        if(cmd == 0){
//            hero = new Druid()
            System.out.println("This is a Druid");
        } else if (cmd == 1) {
//            hero = new DeatNight()
            System.out.println("This is a DeatNight");
        } else if (cmd == 2) {
//            hero = new Hunter()
            System.out.println("This is a Hunter");
        } else {
            System.out.println("WRONG!");
        }
    }

    public static void main(String[] args) {
        Hello();
        while (true){
            Scanner input = new Scanner(System.in);
            int cmd = input.nextInt();
                switch (cmd){
                    case 1:
                        //Method Choose Hero
                        System.out.println("Choose your hero!");
                        cmd = input.nextInt();
                        createHero(cmd);

                        System.out.println("1) What is your heros name?");
                        String name = input.nextLine();

                        System.out.println("2) Choose a class:");

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
                        Hello();
                }
            }
        }
}