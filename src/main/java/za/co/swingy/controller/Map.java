package za.co.swingy.controller;

import za.co.swingy.model.Hero;

import java.util.Random;

class Map {

    private String[][] map;
    private int middle;
    private int dimension;
    private int y;
    private int x;

    Map(){}

    String[][] getMap() {
        return map;
    }

    void setMap(Hero hero) {
        int level = hero.getLvl();

        dimension = getMapDimensions(level);
        middle = getMiddleOfMap(dimension);
        map = generateMap(dimension, middle);

        hero.setY(middle);
        hero.setX(middle);
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

    boolean edgeOfMap(Hero hero) {
        y = hero.getY();
        x = hero.getX();
        return (y == 0 || x == 0 || y == map.length - 1 || x == map[y].length - 1);
    }

    private static boolean inArray(int[][] array, int[] vals)
    {
        for (int[] ints : array) {
            if (ints[0] == vals[0] && ints[1] == vals[1])
                return (true);
        }
        return (false);
    }

    private static String[][] generateMap(int dimension, int middle)
    {
        String[][] newMap = new String[dimension][dimension];
        int nubOfVillains = Math.round(((dimension * dimension) * 50) / 100);
        int[][] villainsCoordinates = new int[nubOfVillains][2];
        int numOfItems = Math.round(((dimension * dimension) * 12) / 100);
        int[][] itemsCoordinates = new int[numOfItems][2];


        for (int i = 0; i < nubOfVillains; i++)
        {
            int[] position = generatePosition(dimension);
            if (position[1] != middle && !inArray(villainsCoordinates, position))
                villainsCoordinates[i] = position;
            else
                i--;
        }

        for (int i = 0; i < numOfItems; i++)
        {
            int[] position = generatePosition(dimension);
            if (position[1] != middle && !inArray(itemsCoordinates, position) && !inArray(villainsCoordinates, position))
                itemsCoordinates[i] = position;
            else
                i--;
        }

        for (int y = 0; y < dimension; y++)
        {
            for (int x = 0; x < dimension; x++)
            {
                int[] tmp = {y,x};
                if (inArray(villainsCoordinates, tmp))
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

    void newMap(Hero hero) {
        dimension = Map.getMapDimensions(hero.getLvl());
        middle = getMiddleOfMap(dimension);
        map = Map.generateMap(dimension, middle);
        hero.setX(middle);
        hero.setY(middle);
    }

    void moveUp(Hero hero) {
        y--;
        hero.setY(hero.getY() - 1);
    }

    void moveDown(Hero hero) {
        y++;
        hero.setY(hero.getY() + 1);
    }

    void moveLeft(Hero hero) {
        x--;
        hero.setX(hero.getX() - 1);
    }

    void moveRight(Hero hero) {
        x++;
        hero.setX(hero.getX() + 1);
    }

    boolean isEnemy() {
            return "X".equalsIgnoreCase(map[y][x]);
        }

    boolean isItem() {
        return "C".equalsIgnoreCase(map[y][x]);
    }
}
