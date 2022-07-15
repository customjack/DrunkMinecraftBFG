package me.carlton.drunkminecraft.dataholder;
import me.carlton.drunkminecraft.drinktracking.DrinkScoreboard;
import me.carlton.drunkminecraft.drinktracking.PlayerDrinkProfile;
import me.carlton.drunkminecraft.drinktracking.PlayerProfileStorage;
import me.carlton.drunkminecraft.utility.DrinkMessager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.advancement.Advancement;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class DrinkAction {
    // Information any action can have
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

    //Information only relevant to some action types
    private Material blockType;
    private String message;
    private String messageContains;
    private Material itemType;
    private DamageCause damageCause;
    private EntityType killer;
    private int amount;
    private int levelAchieved;
    private Advancement advancement;
    private EntityType entityType;
    private double speed;
    private PlayerTeleportEvent.TeleportCause teleportCause;
    private double distance;
    private boolean isRightClick;
    private boolean isLeftClick;


    /*
    Constructors
     */
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

    /*
    Getters and setters for shraed information
     */
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
            /*
            String s = String.valueOf(getDrinkValue());
            String drinksToDrink =  s.indexOf(".") < 0 ? s : s.replaceAll("0*$", "").replaceAll("\\.$", "");
             */
            String reasonString = "";
            if (isGiveReason()) {
                reasonString = getReason();
            }
            return ("$playerName$" + " must drink $drinkValue$ $drinks$ " + reasonString);
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

    /*
    Getters and setters for specific information
     */

    public DamageCause getDamageCause() {
        return damageCause;
    }

    public PlayerTeleportEvent.TeleportCause getTeleportCause() {
        return teleportCause;
    }

    public double getSpeed() {
        return speed;
    }

    public EntityType getEntityType() {
        return entityType;
    }
    public int getAmount() {
        return amount;
    }

    public Advancement getAdvancement() {
        return advancement;
    }

    public double getDistance() {
        return distance;
    }

    public EntityType getKiller() {
        return killer;
    }

    public Material getBlockType() {
        return blockType;
    }

    public Material getItemType() {
        return itemType;
    }

    public String getBroadcastString() {
        return broadcastString;
    }

    public String getMessage() {
        return message;
    }

    public String getMessageContains() {
        return messageContains;
    }

    public int getLevelAchieved() {
        return levelAchieved;
    }

    public boolean isLeftClick() {
        return isLeftClick;
    }

    public boolean isRightClick() {
        return isRightClick;
    }

    public void setDamageCause(DamageCause damageCause) {
        this.damageCause = damageCause;
    }

    public void setTeleportCause(PlayerTeleportEvent.TeleportCause teleportCause) {
        this.teleportCause = teleportCause;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setEntityType(EntityType entityType) {
        this.entityType = entityType;
    }


    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setAdvancement(Advancement advancement) {
        this.advancement = advancement;
    }

    public void setBlockType(Material blockType) {
        this.blockType = blockType;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setItemType(Material itemType) {
        this.itemType = itemType;
    }

    public void setKiller(EntityType killer) {
        this.killer = killer;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setMessageContains(String messageContains) {
        this.messageContains = messageContains;
    }

    public void setLeftClick(boolean leftClick) {
        isLeftClick = leftClick;
    }

    public void setRightClick(boolean rightClick) {
        isRightClick = rightClick;
    }

    public void setLevelAchieved(int levelAchieved) {
        this.levelAchieved = levelAchieved;
    }

    public void makePlayerDrink(Player p) {
        PlayerProfileStorage profileStorage = new PlayerProfileStorage();
        PlayerDrinkProfile playerProfile = profileStorage.getPlayerDrinkProfile(p);
        if (Math.random() < getChance()) {
            double playerDrinkValueGained = getDrinkValue();
            int playerDrinksToGiveGained = getDrinksToGive();
            playerProfile.addDrinksTaken(playerDrinkValueGained);
            playerProfile.addDrinksToGive(playerDrinksToGiveGained);
            playerProfile.startCooldown(this,(int) Math.round(getCooldown()));
            String playerReason = parseString(getReason(),p,playerDrinkValueGained,playerDrinksToGiveGained);
            String playerInstructions = parseString(getInstructions(),p,playerDrinkValueGained,playerDrinksToGiveGained);
            String playerBroadcastMessage = parseString(getBroadcastMessage(),p,playerDrinkValueGained,playerDrinksToGiveGained);
            DrinkMessager.sendInstructionsAndReason(p,playerInstructions,playerReason,isGiveReason());
            Bukkit.getServer().broadcastMessage(playerBroadcastMessage);
            DrinkScoreboard drinkScoreboard = new DrinkScoreboard();
            drinkScoreboard.updateAllScoreboards();
        }
    }
    private String parseString(String string,Player p, double drinkValue,int drinksToGive) {
        DecimalFormat format = new DecimalFormat("0.#");

        string = string.replace("$playerName$",p.getDisplayName());
        string = string.replace("$drinkValue$",format.format(drinkValue));
        string = string.replace("$drinkToGive$",String.valueOf(drinksToGive));
        if (drinkValue == 1) {
            string = string.replace("$drinks$","drink");
            string = string.replace("$shots$","shot");
            string = string.replace("$sips$","sip");
            string = string.replace("$chugs$","chug");
        } else{
            string = string.replace("$drinks$","drinks");
            string =string.replace("$shots$","shots");
            string = string.replace("$sips$","sips");
            string = string.replace("$chugs$","chugs");
        }
        return string;
    }

    @Override
    public String toString() {
        return "DrinkAction{" +
                "name='" + name + '\'' +
                ", instructions='" + instructions + '\'' +
                ", drinkMin=" + drinkMin +
                ", drinkMax=" + drinkMax +
                ", drinkValue=" + drinkValue +
                ", reason='" + reason + '\'' +
                ", actionName='" + actionName + '\'' +
                ", actionEventClass=" + actionEventClass +
                ", chance=" + chance +
                ", cooldown=" + cooldown +
                ", broadcastString='" + broadcastString + '\'' +
                ", giveReason=" + giveReason +
                ", drinksToGive=" + drinksToGive +
                ", blockType=" + blockType +
                ", message='" + message + '\'' +
                ", messageContains='" + messageContains + '\'' +
                ", itemType=" + itemType +
                ", damageCause=" + damageCause +
                ", killer=" + killer +
                ", amount=" + amount +
                ", levelAchieved=" + levelAchieved +
                ", advancement=" + advancement +
                ", entityType=" + entityType +
                ", speed=" + speed +
                ", teleportCause=" + teleportCause +
                ", distance=" + distance +
                ", isRightClick=" + isRightClick +
                ", isLeftClick=" + isLeftClick +
                '}';
    }
}
