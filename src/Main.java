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

    public static Hero createHero(int cmd){

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