package me.carlton.drunkminecraft.dataholder.drinkactionchildren;

import me.carlton.drunkminecraft.dataholder.DrinkAction;
import org.bukkit.Material;

public class DrinkActionInteract extends DrinkAction {
    private Material blockPlaced;
    private Material blockInteractedWith;

    public DrinkActionInteract(String name, String instructions, String actionName, double drinkValue) {
        super(name, instructions, actionName, drinkValue);
    }

    public DrinkActionInteract(String name, String instructions, String actionName, int drinkMin, int drinkMax) {
        super(name, instructions, actionName, drinkMin, drinkMax);
    }

    public Material getBlockPlaced() {
        return blockPlaced;
    }

    public void setBlockPlaced(Material blockPlaced) {
        this.blockPlaced = blockPlaced;
    }

    public Material getBlockInteractedWith() {
        return blockInteractedWith;
    }

    public void setBlockInteractedWith(Material blockInteractedWith) {
        this.blockInteractedWith = blockInteractedWith;
    }
}
