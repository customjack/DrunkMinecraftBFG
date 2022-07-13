package me.carlton.drunkminecraft.dataholder.drinkactionchildren;

import me.carlton.drunkminecraft.dataholder.DrinkAction;
import org.bukkit.Material;

public class DrinkActionHarvest extends DrinkAction {
    private Material harvestedItem;
    private int amount;

    public DrinkActionHarvest(String name, String instructions, String actionName, double drinkValue) {
        super(name, instructions, actionName, drinkValue);
    }

    public DrinkActionHarvest(String name, String instructions, String actionName, int drinkMin, int drinkMax) {
        super(name, instructions, actionName, drinkMin, drinkMax);
    }

    public Material getHarvestedItem() {
        return harvestedItem;
    }

    public void setHarvestedItem(Material harvestedItem) {
        this.harvestedItem = harvestedItem;
    }
}
