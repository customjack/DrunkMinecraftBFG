package me.carlton.drunkminecraft.dataholder.drinkactionchildren;

import me.carlton.drunkminecraft.dataholder.DrinkAction;
import org.bukkit.Material;

public class DrinkActionConsume extends DrinkAction {
    private Material consumedItem;

    public DrinkActionConsume(String name, String instructions, String actionName, double drinkValue) {
        super(name, instructions, actionName, drinkValue);
    }

    public DrinkActionConsume(String name, String instructions, String actionName, int drinkMin, int drinkMax) {
        super(name, instructions, actionName, drinkMin, drinkMax);
    }

    public Material getConsumedItem() {
        return consumedItem;
    }

    public void setConsumedItem(Material consumedItem) {
        this.consumedItem = consumedItem;
    }
}
