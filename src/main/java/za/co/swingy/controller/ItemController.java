package za.co.swingy.controller;

import za.co.swingy.model.Item;
import za.co.swingy.model.items.Armor;
import za.co.swingy.model.items.Helm;
import za.co.swingy.model.items.Weapon;

import java.util.Random;

public class ItemController {
    private String itemType;
    private Item item;

    public ItemController() {
        Random rand = new Random();
        int randomItemIndex = rand.nextInt(8);
        String[] itemTypes = {"Weapon", "Armor", "Helm", "Armor", "Helm", "Weapon", "Helm", "Weapon", "Armor"};
        itemType = itemTypes[randomItemIndex];
    }

    Item dropItem() {
        if (itemType.equalsIgnoreCase("weapon")) {
            item = new Weapon();
        } else if (itemType.equalsIgnoreCase("armor")) {
            item = new Armor();
        } else if (itemType.equalsIgnoreCase("helm")) {
            item = new Helm();
        }
        return item;
    }
}
