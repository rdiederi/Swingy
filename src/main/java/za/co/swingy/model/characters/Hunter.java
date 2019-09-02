package za.co.swingy.model.characters;

import za.co.swingy.model.Hero;

public class Hunter extends Hero {
    public Hunter(){
        this.setBase(25,15,80,0,0,0,0);
    }
    public Hunter(String name){
        super(name);
        this.setBase(25,15,80,0,0,0,0);
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getAttack() {
        return this.attack;
    }

    @Override
    public void setAttack(int attack) {
        this.attack = attack;
    }

    @Override
    public int getDefense() {
        return this.defense;
    }

    @Override
    public void setDefense(int defense) {
        this.defense = defense;
    }

    @Override
    public int getHp() {
        return this.hp;
    }

    @Override
    public void setHp(int hp) {
        this.hp = hp;
    }

    @Override
    public int getXp() {
        return this.xp;
    }

    @Override
    public void setXp(int xp) {
        this.xp = xp;
    }

    @Override
    public int getLvl() {
        return this.lvl;
    }

    @Override
    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public void levelUp() {
        System.out.println("This is temp");
    }

//    @Override
//    public void attack(){
//        System.out.println("SPAAAARRRRTTTTTAAAAA!!!!!");
//    };
//    @Override
//    public void escape() {
//        System.out.println("this bitch ran");
//    }
}
