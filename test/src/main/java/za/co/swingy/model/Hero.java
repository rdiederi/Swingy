package za.co.swingy.model;

import za.co.swingy.controller.StorageController;

public abstract class Hero{


    private StorageController sc = new StorageController();
    protected int id;
    protected int attack;
    protected int defense;
    protected int hp;
    protected int xp = 0;
    protected int lvl = 1;
    protected int y,x;
    public String name;
    protected String type = new StringBuilder(String.valueOf(this.getClass())).delete(0, 36).toString();

    public Hero(){}

    public Hero(String name){
        this.name = name;
    }


    protected void setBase(int attack, int defense, int hp, int xp, int lvl, int x, int y){
        setAttack(attack);
        setDefense(defense);
        setHp(hp);
        setLvl(lvl);
        setX(x);
        setY(y);
        setXp(xp);
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public int getAttack() {
        return this.attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return this.defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    
    public int getHp() {
        return this.hp;
    }

    
    public void setHp(int hp) {
        this.hp = hp;
    }

    
    public int getXp() {
        return this.xp;
    }

    
    public void setXp(int xp) {
        this.xp = xp;
    }

    
    public int getLvl() {
        return this.lvl;
    }

    
    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    
    public int getY() {
        return this.y;
    }

    
    public void setY(int y) {
        this.y = y;
    }

    
    public int getX() {
        return this.x;
    }

    
    public void setX(int x) {
        this.x = x;
    }

    
    public String getType() {
        return this.type;
    }
    public void levelUp() {
        lvl++;
        this.attack = (int)(attack * 1.3);
        this.defense = (int)(defense * 1.3);
        this.hp = (int)(hp * 1.5);

        System.out.println("Congrats! You leveled up!");
        System.out.println("Map size increased.");
    }

    public void gainXp(int points) {
        this.xp += points;
        int maxXp = this.lvl * 1000 + (lvl - 1) * (lvl - 1) * 450;
        if (this.xp >= maxXp) {
            levelUp();
        }
        sc.updateStats(this);
    }

}
