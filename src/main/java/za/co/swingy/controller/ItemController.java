package za.co.swingy.controller;

import org.jetbrains.annotations.Nullable;
import za.co.swingy.model.Item;
import za.co.swingy.model.items.Armor;
import za.co.swingy.model.items.Helm;
import za.co.swingy.model.items.Weapon;

import java.util.Random;

public class ItemController {
    private String itemTypes[] = {"Weapon", "Armor", "Helm"};
    private int randomItemIndex;
    private static String itemType;
    private static Item item;

    public ItemController() {
        Random rand = new Random();
        randomItemIndex = rand.nextInt(2);
        itemType = itemTypes[randomItemIndex];
    }

    @Nullable
    public static Item dropItem() {
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
