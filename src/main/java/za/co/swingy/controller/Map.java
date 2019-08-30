package za.co.swingy.controller;

import za.co.swingy.model.Hero;

import java.util.Random;

public class Map {

    private String[][] map;
    private int middle;
    private int dimension;

    public Map(){}

    public Map(Hero hero) {
        int level = hero.getLvl();

        dimension = getMapDimensions(level);
        middle = getMiddleOfMap(dimension);
        map = generateMap(dimension, middle);
    }

    public String[][] getMap() {
        return map;
    }

    public int getDimension(){
        return dimension;
    }

    public static int getMapDimensions(int level)
    {
        if (level < 1) {
            level = 1;
        }
        int dimension = (level - 1) * 5 + 10 - (level % 2);
        return  (dimension);
    }

    private int getMiddleOfMap(int dimension) {
        return ((dimension - 1) / 2);
    }

    public static boolean inArray(int array[][], int vals[])
    {
        for (int x = 0; x < array.length; x++)
        {
            if (array[x][0] == vals[0] && array[x][1] == vals[1])
                return (true);
        }
        return (false);
    }

    public static String[][] generateMap(int dimension, int middle)
    {
        String newMap[][] = new String[dimension][dimension];
        int numOfVillans = Math.round(((dimension * dimension) * 50) / 100);
        int villansCoordinates[][] = new int[numOfVillans][2];
        int numOfItems = Math.round(((dimension * dimension) * 6) / 100);
        int itemsCoordinates[][] = new int[numOfItems][2];


        for (int i = 0; i < numOfVillans; i++)
        {
            int position[] = generatePosition(dimension);
            if (position[1] != middle && !inArray(villansCoordinates, position))
                villansCoordinates[i] = position;
            else
                i--;
        }

        for (int i = 0; i < numOfItems; i++)
        {
            int position[] = generatePosition(dimension);
            if (position[1] != middle && !inArray(itemsCoordinates, position) && !inArray(villansCoordinates, position))
                itemsCoordinates[i] = position;
            else
                i--;
        }

        for (int y = 0; y < dimension; y++)
        {
            for (int x = 0; x < dimension; x++)
            {
                int tmp[] = {y,x};
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


    public static int []generatePosition(int dimension)
    {
        Random rand = new Random();
        int pos[] = new int[2];
        int x = rand.nextInt(dimension);
        int y = rand.nextInt(dimension);

        pos[0] = x;
        pos[1] = y;

        return (pos);
    }

        public void newMap(Hero hero) {
        String[][] map = new String[0][];
        dimension = Map.getMapDimensions(hero.getLvl());
        middle = getMiddleOfMap(dimension);
        map = Map.generateMap(dimension, middle);
        hero.setX(middle);
        hero.setY(middle);
    }
}
