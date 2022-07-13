package me.carlton.drunkminecraft.dataholder.drinkactionchildren;

import me.carlton.drunkminecraft.dataholder.DrinkAction;
import org.bukkit.Material;

public class DrinkActionFish extends DrinkAction {
    private Material itemFished;

    public DrinkActionFish(String name, String instructions, String actionName, double drinkValue) {
        super(name, instructions, actionName, drinkValue);
    }

    public DrinkActionFish(String name, String instructions, String actionName, int drinkMin, int drinkMax) {
        super(name, instructions, actionName, drinkMin, drinkMax);
    }

    public Material getItemFished() {
        return itemFished;
    }

    public void setItemFished(Material itemFished) {
        this.itemFished = itemFished;
    }
}
