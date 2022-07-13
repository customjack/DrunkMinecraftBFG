package me.carlton.drunkminecraft.dataholder.drinkactionchildren;

import me.carlton.drunkminecraft.dataholder.DrinkAction;
import org.bukkit.Material;

public class DrinkActionPickup extends DrinkAction {
    private Material pickedUpItem;
    private int pickedUpAmount;

    public DrinkActionPickup(String name, String instructions, String actionName, double drinkValue) {
        super(name, instructions, actionName, drinkValue);
    }

    public DrinkActionPickup(String name, String instructions, String actionName, int drinkMin, int drinkMax) {
        super(name, instructions, actionName, drinkMin, drinkMax);
    }

    public int getPickedUpAmount() {
        return pickedUpAmount;
    }

    public void setPickedUpAmount(int pickedUpAmount) {
        this.pickedUpAmount = pickedUpAmount;
    }

    public Material getPickedUpItem() {
        return pickedUpItem;
    }

    public void setPickedUpItem(Material pickedUpItem) {
        this.pickedUpItem = pickedUpItem;
    }
}
