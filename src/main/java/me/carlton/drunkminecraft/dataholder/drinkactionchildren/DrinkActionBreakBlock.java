package me.carlton.drunkminecraft.dataholder.drinkactionchildren;

import me.carlton.drunkminecraft.dataholder.DrinkAction;
import org.bukkit.Material;

public class DrinkActionBreakBlock extends DrinkAction {
    private Material brokenBlock;

    public DrinkActionBreakBlock(String name, String instructions, String actionName, double drinkValue) {
        super(name, instructions, actionName, drinkValue);
    }

    public DrinkActionBreakBlock(String name, String instructions, String actionName, int drinkMin, int drinkMax) {
        super(name, instructions, actionName, drinkMin, drinkMax);
    }

    public Material getBrokenBlock() {
        return brokenBlock;
    }

    public void setBrokenBlock(Material brokenBlock) {
        this.brokenBlock = brokenBlock;
    }
}
