package me.carlton.drunkminecraft.dataholder.drinkactionchildren;

import me.carlton.drunkminecraft.dataholder.DrinkAction;

public class DrinkActionJoin extends DrinkAction {

    public DrinkActionJoin(String name, String instructions, String actionName, double drinkValue) {
        super(name, instructions, actionName, drinkValue);
    }

    public DrinkActionJoin(String name, String instructions, String actionName, int drinkMin, int drinkMax) {
        super(name, instructions, actionName, drinkMin, drinkMax);
    }
}
