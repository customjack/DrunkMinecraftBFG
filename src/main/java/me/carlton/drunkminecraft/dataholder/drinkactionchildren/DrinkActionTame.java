package me.carlton.drunkminecraft.dataholder.drinkactionchildren;

import me.carlton.drunkminecraft.dataholder.DrinkAction;
import org.bukkit.entity.EntityType;

public class DrinkActionTame extends DrinkAction {
    private EntityType tamedAnimal;

    public DrinkActionTame(String name, String instructions, String actionName, double drinkValue) {
        super(name, instructions, actionName, drinkValue);
    }

    public DrinkActionTame(String name, String instructions, String actionName, int drinkMin, int drinkMax) {
        super(name, instructions, actionName, drinkMin, drinkMax);
    }

    public EntityType getTamedAnimal() {
        return tamedAnimal;
    }

    public void setTamedAnimal(EntityType tamedAnimal) {
        this.tamedAnimal = tamedAnimal;
    }
}
