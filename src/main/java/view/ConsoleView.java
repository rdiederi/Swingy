package view;

import model.DeathNight;
import model.Druid;
import model.Hero;
import model.Hunter;

public class ConsoleView {
    public static void printWelcome (){
        System.out.println("Welcome!");
        System.out.println("Choose an option:");
        System.out.println("1) Create a hero");
        System.out.println("2) Select a previously created hero");
        System.out.println("3) Exit");
    }

    public static void printHeroClasses() {

        Druid druid = new Druid();
        Hunter hunter = new Hunter();
        DeathNight dN = new DeathNight();

        System.out.printf("%s\n", "                 CHOOSE YOUR CLASS");
        System.out.println("|------------------------------------------------|");

        System.out.printf("|%14s", "1");
        System.out.printf("%10s", "2");
        System.out.printf("%11s", "3");
        System.out.printf("%15s", "|\n");

        System.out.printf("|%16s", "Druid");
        System.out.printf("%10s", "Hunter");
        System.out.printf("%14s", "DeathNight");
        System.out.printf("%10s", "|\n");

        System.out.println("|                                                |");

        System.out.print("| Attack:  ");
        System.out.printf("%3d", druid.getAttack());
        System.out.printf("%9d", hunter.getAttack());
        System.out.printf("%10d", dN.getAttack());
        System.out.printf("%18s", "|\n");

        System.out.print("| Defense: ");
        System.out.printf("%3d", druid.getDefense());
        System.out.printf("%9d", hunter.getDefense());
        System.out.printf("%10d", dN.getDefense());
        System.out.printf("%18s", "|\n");

        System.out.print("| HP:      ");
        System.out.printf("%4d", druid.getHp());
        System.out.printf("%8d", hunter.getHp());
        System.out.printf("%11d", dN.getHp());
        System.out.printf("%17s", "|\n");

        System.out.println("|------------------------------------------------|");
    }

    public static void printHeroStats(Hero hero)
    {
        System.out.println("--------------------");
        System.out.println("Name: " + hero.getName());
        System.out.println("Type: " + hero.getType());
        System.out.println("Attack: " + hero.getAttack());
        System.out.println("Defense: " + hero.getDefense());
        System.out.println("HP: " + hero.getHp());
        System.out.println("Experience: " + hero.getXp());
        System.out.println("--------------------");
        System.out.println();
    }
}
