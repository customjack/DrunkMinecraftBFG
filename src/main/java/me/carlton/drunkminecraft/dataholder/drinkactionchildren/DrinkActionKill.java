package me.carlton.drunkminecraft.dataholder.drinkactionchildren;

import me.carlton.drunkminecraft.dataholder.DrinkAction;
import org.bukkit.entity.EntityType;

public class DrinkActionKill extends DrinkAction {
    private EntityType killedEntityType;

    public DrinkActionKill(String name, String instructions, String actionName, double drinkValue) {
        super(name, instructions, actionName, drinkValue);
    }

    public DrinkActionKill(String name, String instructions, String actionName, int drinkMin, int drinkMax) {
        super(name, instructions, actionName, drinkMin, drinkMax);
    }

    public EntityType getKilledEntityType() {
        return killedEntityType;
    }

    public void setKilledEntityType(EntityType killedEntityType) {
        this.killedEntityType = killedEntityType;
    }
}
