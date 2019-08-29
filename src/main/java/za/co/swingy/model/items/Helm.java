package za.co.swingy.model.items;

import za.co.swingy.model.Hero;
import za.co.swingy.model.Item;

public class Helm implements Item {
    private String name;
    private int stat;


    public Helm() {
        name = "Helm";
        stat =  (int) (10 + Math.floor( (Math.random() * 70) + 1));
    }

    public Helm(String name) {
        this.name = name;
        stat =  (int) (10 + Math.floor( (Math.random() * 70) + 1));
    }

    public String getName() {
        return name;
    }

    public int getStat() {
        return stat;
    }


    @Override
    public void applyItem(Hero hero) {
        hero.setHp(hero.getHp() + this.stat);
    }
}
