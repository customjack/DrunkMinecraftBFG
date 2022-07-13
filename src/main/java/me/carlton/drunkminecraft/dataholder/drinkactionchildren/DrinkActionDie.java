package me.carlton.drunkminecraft.dataholder.drinkactionchildren;

import me.carlton.drunkminecraft.dataholder.DrinkAction;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class DrinkActionDie extends DrinkAction {
    private DamageCause lastDamageCause;
    private EntityType entityKiller;

    public DrinkActionDie(String name, String instructions, String actionName, double drinkValue) {
        super(name, instructions, actionName, drinkValue);
    }

    public DrinkActionDie(String name, String instructions, String actionName, int drinkMin, int drinkMax) {
        super(name, instructions, actionName, drinkMin, drinkMax);
    }

    public DamageCause getLastDamageCause() {
        return lastDamageCause;
    }

    public void setLastDamageCause(DamageCause lastDamageCause) {
        this.lastDamageCause = lastDamageCause;
    }

    public EntityType getEntityKiller() {
        return entityKiller;
    }

    public void setEntityKiller(EntityType entityKiller) {
        this.entityKiller = entityKiller;
    }
}
