package me.carlton.drunkminecraft.dataholder.drinkactionchildren;

import me.carlton.drunkminecraft.dataholder.DrinkAction;
import org.bukkit.Material;

public class DrinkActionPlaceBlock extends DrinkAction {
    private Material blockPlaced;

    public DrinkActionPlaceBlock(String name, String instructions, String actionName, double drinkValue) {
        super(name, instructions, actionName, drinkValue);
    }

    public DrinkActionPlaceBlock(String name, String instructions, String actionName, int drinkMin, int drinkMax) {
        super(name, instructions, actionName, drinkMin, drinkMax);
    }

    public Material getBlockPlaced() {
        return blockPlaced;
    }

    public void setBlockPlaced(Material blockPlaced) {
        this.blockPlaced = blockPlaced;
    }
}
