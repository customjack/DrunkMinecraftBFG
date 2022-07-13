package me.carlton.drunkminecraft.dataholder.drinkactionchildren;

import me.carlton.drunkminecraft.dataholder.DrinkAction;
import org.bukkit.Material;

public class DrinkActionMend extends DrinkAction {
    private Material itemMended;

    public DrinkActionMend(String name, String instructions, String actionName, double drinkValue) {
        super(name, instructions, actionName, drinkValue);
    }

    public DrinkActionMend(String name, String instructions, String actionName, int drinkMin, int drinkMax) {
        super(name, instructions, actionName, drinkMin, drinkMax);
    }

    public Material getItemMended() {
        return itemMended;
    }

    public void setItemMended(Material itemMended) {
        this.itemMended = itemMended;
    }


}
