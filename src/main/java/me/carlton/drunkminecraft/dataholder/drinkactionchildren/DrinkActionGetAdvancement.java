package me.carlton.drunkminecraft.dataholder.drinkactionchildren;

import me.carlton.drunkminecraft.dataholder.DrinkAction;
import org.bukkit.advancement.Advancement;

public class DrinkActionGetAdvancement extends DrinkAction {
    private Advancement achieveAdvancement;

    public DrinkActionGetAdvancement(String name, String instructions, String actionName, double drinkValue) {
        super(name, instructions, actionName, drinkValue);
    }

    public DrinkActionGetAdvancement(String name, String instructions, String actionName, int drinkMin, int drinkMax) {
        super(name, instructions, actionName, drinkMin, drinkMax);
    }


    public Advancement getAchieveAdvancement() {
        return achieveAdvancement;
    }

    public void setAchieveAdvancement(Advancement achieveAdvancement) {
        this.achieveAdvancement = achieveAdvancement;
    }
}
