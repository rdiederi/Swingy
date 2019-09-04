package za.co.swingy.model.items;

import za.co.swingy.controller.StorageController;
import za.co.swingy.model.Hero;
import za.co.swingy.model.Item;

public class Helm implements Item {
    private String name;
    private int stat;
    StorageController sc = new StorageController();

    public Helm() {
        name = "Helm";
        stat =  (int) (10 + Math.floor( (Math.random() * 70) + 1));
    }

    public String getName() {
        return name;
    }

    @Override
    public void applyItem(Hero hero) {
        hero.setHp(hero.getHp() + this.stat);
        sc.updateStats(hero);
        System.out.println("|Item found! Hero picks up -> " + name + ": +" + stat + "|");
    }
}
