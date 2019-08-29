package za.co.swingy.controller;

import za.co.swingy.model.Item;
import za.co.swingy.model.items.Armor;
import za.co.swingy.model.items.Helm;
import za.co.swingy.model.items.Weapon;

import java.util.Random;

public class ItemController {
    private String itemTypes[] = {"Weapon", "Armor", "Helm"};
    private int randomItemIndex;
    private String itemType;
    private Item item;

    public ItemController() {
        Random rand = new Random();
        randomItemIndex = rand.nextInt(2);
        itemType = itemTypes[randomItemIndex];
    }

    public static Item dropItem() {
        if (itemType.equalsIgnoreCase("weapon")) {
            item = new Weapon();
        } else if (itemType.equalsIgnoreCase("armor")) {
            return new Armor();
        } else if (itemType.equalsIgnoreCase("helm")) {
            return new Helm();
        }
        return null;
    }
}
