package me.carlton.drunkminecraft.dataholder;
import org.bukkit.event.Event;

public class DrinkAction {
    private String name;
    private String instructions;
    private int drinkMin;
    private int drinkMax;
    private double drinkValue;
    private String reason;
    private String actionName;
    private Class actionEventClass;
    private double chance = 1.0;
    private double cooldown;
    private String broadcastString;
    private boolean giveReason = true;
    private int drinksToGive;

    public DrinkAction(String name, String instructions, String actionName, double drinkValue) {
        this.name = name;
        this.instructions = instructions;
        this.actionName = actionName;
        this.drinkValue = drinkValue;
    }

    public DrinkAction(String name, String instructions, String actionName, int drinkMin, int drinkMax) {
        this.name = name;
        this.instructions = instructions;
        this.actionName = actionName;
        this.drinkMin = drinkMin;
        this.drinkMax = drinkMax;
    }

    public double getChance() {
        return chance;
    }

    public double getDrinkValue() {
        if (drinkValue == 0.0) {
            return (double) (drinkMin + (int)(Math.random() * ((drinkMax - drinkMin) + 1)));
        }
        return drinkValue;
    }

    public String getBroadcastMessage() {
        if (broadcastString == null) {
            String reasonString = getReason();
            String s = String.valueOf(getDrinkValue());
            String drinksToDrink =  s.indexOf(".") < 0 ? s : s.replaceAll("0*$", "").replaceAll("\\.$", "");
            return ("$playerName$" + " must drink " + drinksToDrink + ' ' + reasonString);
        }
        return broadcastString;
    }

    public int getDrinksToGive() {
        return drinksToGive;
    }

    public boolean isGiveReason() {
        return giveReason;
    }

    public Class getActionEventClass() {
        return actionEventClass;
    }

    public int getDrinkMax() {
        return drinkMax;
    }

    public int getDrinkMin() {
        return drinkMin;
    }

    public String getActionName() {
        return actionName;
    }

    public String getInstructions() {
        return instructions;
    }

    public String getName() {
        return name;
    }

    public String getReason() {
        if (reason == null) {
            return "for " + name;
        }
        return reason;
    }

    public double getCooldown() {
        return cooldown;
    }

    public void setBroadcastString(String broadcastString) {
        this.broadcastString = broadcastString;
    }

    public void setGiveReason(boolean giveReason) {
        this.giveReason = giveReason;
    }

    public void setActionEventClass(Class actionEventClass) {
        this.actionEventClass = actionEventClass;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public void setChance(double chance) {
        this.chance = chance;
    }

    public void setDrinkMax(int drinkMax) {
        this.drinkMax = drinkMax;
    }

    public void setDrinkMin(int drinkMin) {
        this.drinkMin = drinkMin;
    }

    public void setDrinkValue(double drinkValue) {
        this.drinkValue = drinkValue;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setCooldown(double cooldown) {
        this.cooldown = cooldown;
    }

    public void setDrinksToGive(int drinksToGive) {
        this.drinksToGive = drinksToGive;
    }
}
