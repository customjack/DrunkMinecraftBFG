package me.carlton.drunkminecraft.dataholder.drinkactionchildren;

import me.carlton.drunkminecraft.dataholder.DrinkAction;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class DrinkActionTakeDamage extends DrinkAction {
    private DamageCause damageCause;
    public DrinkActionTakeDamage(String name, String instructions, String actionName, double drinkValue) {
        super(name, instructions, actionName, drinkValue);
    }

    public DrinkActionTakeDamage(String name, String instructions, String actionName, int drinkMin, int drinkMax) {
        super(name, instructions, actionName, drinkMin, drinkMax);
    }

    public DamageCause getDamageCause() {
        return damageCause;
    }

    public void setDamageCause(DamageCause damageCause) {
        this.damageCause = damageCause;
    }
}
