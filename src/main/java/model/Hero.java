package model;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public abstract class Hero implements Warrior{

    private int attack;
    private int defense;
    private int hp;
    private int xp;
    private int lvl;
    private int y,x;
    private String name;
    private String weapon;
    private String armor;
    private String helm;

    Hero(){}

    Hero(String name){
        this.name = name;
    }

    public void setBase(int attack, int defense, int hp, int xp, int lvl, int x, int y, String name, String weapon, String armor, String helm){
        setAttack(attack);
        setDefense(defense);
        setHp(hp);
        setLvl(lvl);
        setX(x);
        setY(y);
        setXp(xp);
        setName(name);
        setWeapon(weapon);
        setArmor(armor);
        setHelm(helm);
    }
}
