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

    Hero(){}

    Hero(String name){
        this.name = name;
    }

    public void setBase(int attack, int defense, int hp, int xp, int lvl, int x, int y){
        setAttack(attack);
        setDefense(defense);
        setHp(hp);
        setLvl(lvl);
        setX(x);
        setY(y);
        setXp(xp);
    }
}
