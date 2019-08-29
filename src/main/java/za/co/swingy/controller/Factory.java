package za.co.swingy.controller;

import za.co.swingy.model.Hero;
import za.co.swingy.model.Item;
import za.co.swingy.model.characters.DeathNight;
import za.co.swingy.model.characters.Druid;
import za.co.swingy.model.characters.Hunter;

public class Factory {
    public static Hero newHero (String heroClass, String name) {

        if (heroClass.equalsIgnoreCase("hunter")) {
            return new Hunter(name);
        } else if (heroClass.equalsIgnoreCase("deathnight")) {
            return new DeathNight(name);
        } else if (heroClass.equalsIgnoreCase("druid")) {
            return new Druid(name);
        }
        return null;
    }

    public static Item newItem() {
        Item item = ItemController.dropItem();
        return item;
    }
}
