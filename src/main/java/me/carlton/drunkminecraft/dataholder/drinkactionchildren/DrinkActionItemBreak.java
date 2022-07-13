package me.carlton.drunkminecraft.dataholder.drinkactionchildren;

import me.carlton.drunkminecraft.dataholder.DrinkAction;
import org.bukkit.Material;

public class DrinkActionItemBreak extends DrinkAction {
    private Material brokenItem;

    public DrinkActionItemBreak(String name, String instructions, String actionName, double drinkValue) {
        super(name, instructions, actionName, drinkValue);
    }

    public DrinkActionItemBreak(String name, String instructions, String actionName, int drinkMin, int drinkMax) {
        super(name, instructions, actionName, drinkMin, drinkMax);
    }

    public Material getBrokenItem() {
        return brokenItem;
    }

    public void setBrokenItem(Material brokenItem) {
        this.brokenItem = brokenItem;
    }
}
