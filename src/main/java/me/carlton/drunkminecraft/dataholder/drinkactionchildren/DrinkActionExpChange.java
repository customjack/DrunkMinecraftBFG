package me.carlton.drunkminecraft.dataholder.drinkactionchildren;

import me.carlton.drunkminecraft.dataholder.DrinkAction;

import java.util.ArrayList;

public class DrinkActionExpChange extends DrinkAction {
    private ArrayList<Integer> experienceRange;
    private int totalExperience;

    public DrinkActionExpChange(String name, String instructions, String actionName, double drinkValue) {
        super(name, instructions, actionName, drinkValue);
    }

    public DrinkActionExpChange(String name, String instructions, String actionName, int drinkMin, int drinkMax) {
        super(name, instructions, actionName, drinkMin, drinkMax);
    }

    public ArrayList<Integer> getExperienceRange() {
        return experienceRange;
    }

    public void setExperienceRange(ArrayList<Integer> experienceRange) {
        this.experienceRange = experienceRange;
    }

    public int getTotalExperience() {
        return totalExperience;
    }

    public void setTotalExperience(int totalExperience) {
        this.totalExperience = totalExperience;
    }
}
