package me.carlton.drunkminecraft.dataholder.drinkactionchildren;

import me.carlton.drunkminecraft.dataholder.DrinkAction;

public class DrinkActionMove extends DrinkAction {
    private double speed;

    public DrinkActionMove(String name, String instructions, String actionName, double drinkValue) {
        super(name, instructions, actionName, drinkValue);
    }

    public DrinkActionMove(String name, String instructions, String actionName, int drinkMin, int drinkMax) {
        super(name, instructions, actionName, drinkMin, drinkMax);
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
