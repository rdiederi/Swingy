package za.co.swingy.controller;

import java.util.Random;

public class Map {
    //Colors
    public static String ANSI_RED  = "\u001B[31m";
    public static String ANSI_WHITE  = "\u001B[37m";
    public static String ANSI_GREEN  = "\u001B[32m";
    public static String ANSI_BLUE   = "\u001B[34m";
    public static String ANSI_YELLOW = "\u001B[33m";
    public static String ANSI_RESET = "\u001B[0m";

    private String[][] map;

    public Map(){}

    public Map(Hero hero) {
        map = generateMap();
    }

    public static int getMapDimensions(int level)
    {
        int dimension = (level - 1) * 5 + 10 - (level % 2);
        return  (dimension);
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



    public static void drawMap(int dimension, String map[][])
    {
        for (int col = 0; col < dimension; col++)
        {
            for (int row = 0; row < dimension; row++)
            {
                if (map[col][row].equals("X"))
                    System.out.printf(ANSI_RED + map[col][row] + ANSI_RESET);
                else if (map[col][row].equals("C"))
                    System.out.printf(ANSI_YELLOW + map[col][row] + ANSI_RESET);
                else if (map[col][row].equals("1"))
                    System.out.printf(ANSI_GREEN + map[col][row] + ANSI_RESET);
                else if (map[col][row].equals("."))
                    System.out.printf(ANSI_BLUE + map[col][row] + ANSI_RESET);
                else
                    System.out.print(ANSI_WHITE + map[col][row] + ANSI_RESET);
            }
            System.out.println();
        }
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
}
