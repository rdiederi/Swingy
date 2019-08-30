package za.co.swingy.model.items;

import za.co.swingy.controller.StorageController;
import za.co.swingy.model.Hero;
import za.co.swingy.model.Item;

public class Weapon implements Item {
    private String name;
    private int stat;
    StorageController sc = new StorageController();

    public Weapon() {
        name = "Weapon";
        stat = (int) (10 + Math.floor( (Math.random() * 70) + 1));
    }

    public Weapon(String name) {
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
        hero.setAttack(hero.getAttack() + getStat());
        sc.updateStats(hero);
        System.out.println("|Item found! Hero picks up -> " + name + ": +" + stat + "|");
    }
}
