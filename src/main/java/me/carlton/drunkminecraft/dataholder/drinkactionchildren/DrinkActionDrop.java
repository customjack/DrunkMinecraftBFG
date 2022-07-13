package me.carlton.drunkminecraft.dataholder.drinkactionchildren;

import me.carlton.drunkminecraft.dataholder.DrinkAction;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class DrinkActionDrop extends DrinkAction {
    private Material droppedItem;
    private int amount;

    public DrinkActionDrop(String name, String instructions, String actionName, double drinkValue) {
        super(name, instructions, actionName, drinkValue);
    }

    public DrinkActionDrop(String name, String instructions, String actionName, int drinkMin, int drinkMax) {
        super(name, instructions, actionName, drinkMin, drinkMax);
    }

    public Material getDroppedItem() {
        return droppedItem;
    }

    public void setDroppedItem(Material droppedItem) {
        this.droppedItem = droppedItem;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
