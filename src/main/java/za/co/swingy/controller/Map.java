package za.co.swingy.controller;

import za.co.swingy.model.Hero;
import za.co.swingy.view.ConsoleView;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Map {

    private String[][] map;
    private int middle;
    private int dimension;
    private int y;
    private int x;

    public Map(){}

    Map(Hero hero) {
        int level = hero.getLvl();

        dimension = getMapDimensions(level);
        middle = getMiddleOfMap(dimension);
        map = generateMap(dimension, middle);

        hero.setY(middle);
        hero.setX(middle);
    }

    String[][] getMap() {
        return map;
    }

    int getDimension(){
        return dimension;
    }

    private static int getMapDimensions(int level)
    {
        if (level < 1) {
            level = 1;
        }
        return  ((level - 1) * 5 + 10 - (level % 2));
    }

    private int getMiddleOfMap(int dimension) {
        return ((dimension - 1) / 2);
    }

    private boolean edgeOfMap(Hero hero) {
        y = hero.getY();
        x = hero.getX();
        if (y == 0 || x == 0 || y == map.length - 1 || x == map[y].length - 1)
            return (true);
        return (false);
    }

    private static boolean inArray(int[][] array, int[] vals)
    {
        for (int x = 0; x < array.length; x++)
        {
            if (array[x][0] == vals[0] && array[x][1] == vals[1])
                return (true);
        }
        return (false);
    }

    private static String[][] generateMap(int dimension, int middle)
    {
        String[][] newMap = new String[dimension][dimension];
        int numOfVillans = Math.round(((dimension * dimension) * 50) / 100);
        int[][] villansCoordinates = new int[numOfVillans][2];
        int numOfItems = Math.round(((dimension * dimension) * 12) / 100);
        int[][] itemsCoordinates = new int[numOfItems][2];


        for (int i = 0; i < numOfVillans; i++)
        {
            int[] position = generatePosition(dimension);
            if (position[1] != middle && !inArray(villansCoordinates, position))
                villansCoordinates[i] = position;
            else
                i--;
        }

        for (int i = 0; i < numOfItems; i++)
        {
            int[] position = generatePosition(dimension);
            if (position[1] != middle && !inArray(itemsCoordinates, position) && !inArray(villansCoordinates, position))
                itemsCoordinates[i] = position;
            else
                i--;
        }

        for (int y = 0; y < dimension; y++)
        {
            for (int x = 0; x < dimension; x++)
            {
                int[] tmp = {y,x};
                if (inArray(villansCoordinates, tmp))
                    newMap[y][x] = "X";
                else if (inArray(itemsCoordinates, tmp))
                    newMap[y][x] = "C";
                else if (x == middle && y == middle)
                    newMap[y][x] = "1";
                else
                    newMap[y][x] = "0";
            }
        }
        return (newMap);
    }


    private static int []generatePosition(int dimension)
    {
        Random rand = new Random();
        int[] pos = new int[2];
        int x = rand.nextInt(dimension);
        int y = rand.nextInt(dimension);

        pos[0] = x;
        pos[1] = y;

        return (pos);
    }

    private void newMap(Hero hero) {
        dimension = Map.getMapDimensions(hero.getLvl());
        middle = getMiddleOfMap(dimension);
        map = Map.generateMap(dimension, middle);
        hero.setX(middle);
        hero.setY(middle);
    }

    private void moveUp(Hero hero) {
        y--;
        hero.setY(hero.getY() - 1);
    }

    private void moveDown(Hero hero) {
        y++;
        hero.setY(hero.getY() + 1);
    }

    private void moveLeft(Hero hero) {
        x--;
        hero.setX(hero.getX() - 1);
    }

    private void moveRight(Hero hero) {
        x++;
        hero.setX(hero.getX() + 1);
    }

    private boolean isEnemy() {
            return "X".equalsIgnoreCase(map[y][x]);
        }

    private boolean isItem() {
        return "C".equalsIgnoreCase(map[y][x]);
    }

    private void fight(Hero hero, String enemy)  throws SQLException {
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

    private void fightOrFlight(Hero hero, String direction) throws SQLException{
        int min = 0;
        int max = 4;
        int range = max - min - 1;

        String[] enemy = {"Bob", "Larry", "Jeff", "Justin", "Mufaro"};
        int rand = (int)(Math.random() * range) + min;
        boolean possibility = Math.random() < 0.5;

        ConsoleView.printFightOrFlight(enemy[rand]);
        Scanner input = new Scanner(System.in);
        int cmd = input.nextInt();

        if (cmd == 1){
            fight(hero,enemy[rand]);
        } else if (cmd == 2){
            if (possibility){
                goBack(hero, direction);
                System.out.print(ConsoleView.CLR_CLI);
            } else {
                ConsoleView.printToughShit();
                fight(hero,enemy[rand]);
            }
        } else {
            System.out.println("Not a valid command");
            fightOrFlight(hero, direction);
        }
    }

    private void goBack(Hero hero, String direction) {
        if (direction.equals("n"))
            moveDown(hero);
        if (direction.equals("e"))
            moveLeft(hero);
        if (direction.equals("s"))
            moveUp(hero);
        if (direction.equals("w"))
            moveRight(hero);
    }

    void moveHero(String direction, Map mapObj, Hero hero) throws SQLException
    {
        String[] directions = new String[]{"n", "s", "e", "w"};
        boolean result = Arrays.asList(directions).contains(direction);
        String[][] map = mapObj.getMap();
        Factory factory = new Factory();

        if (result) {
            map[hero.getY()][hero.getX()] = ".";
            if (!mapObj.edgeOfMap(hero)) {
                switch (direction) {
                    case "n":
                        moveUp(hero);
                    if(isItem()) {
                        factory.newItem().applyItem(hero);
                    } else if (isEnemy()) {
                        // Fight or flight
                        fightOrFlight(hero,direction);
                    }
                        break;
                    case "s":
                        moveDown(hero);
                    if(isItem()) {
                        factory.newItem().applyItem(hero);
                    } else if (isEnemy()) {
                        // Fight or flight
                        fightOrFlight(hero,direction);
                    }
                        break;
                    case "e":
                        moveRight(hero);
                    if(isItem()) {
                        factory.newItem().applyItem(hero);
                    } else if (isEnemy()) {
                        // Fight or flight
                        fightOrFlight(hero,direction);
                    }
                        break;
                    case "w":
                        moveLeft(hero);
                    if(isItem()) {
                        factory.newItem().applyItem(hero);
                    } else if (isEnemy()) {
                        // Fight or flight
                        fightOrFlight(hero,direction);
                    }
                        break;
                }
                map[hero.getY()][hero.getX()] = "1";
            }
            else {
                hero.gainXp(hero.getLvl() * 555);
                mapObj.newMap(hero);
            }
        }
    }
}
