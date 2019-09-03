package za.co.swingy.model;

public abstract class Hero{

    protected int id;
    protected int attack;
    protected int defense;
    protected int hp;
    protected int xp = 0;
    protected int maxXp;
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

    public abstract int getId();
    public abstract void setId(int id);

    public abstract String getName();
    public abstract void setName(String name);

    public abstract int getAttack();
    public abstract void setAttack(int attack);

    public abstract int getDefense();
    public abstract void setDefense(int defense);

    public abstract int getHp();
    public abstract void setHp(int hp);

    public abstract int getXp();
    public abstract void setXp(int xp);

    public abstract int getLvl();
    public abstract void setLvl(int lvl);

    public abstract int getY();
    public abstract void setY(int y);

    public abstract int getX();
    public abstract void setX(int x);

    public abstract String getType();

    public abstract void levelUp();

    public abstract void fight();
    public abstract void run();

    public abstract void gainXp(int points);

    public abstract void reduceHp(int damage);

}
