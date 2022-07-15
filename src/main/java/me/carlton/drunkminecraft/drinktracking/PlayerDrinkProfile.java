package me.carlton.drunkminecraft.drinktracking;

import me.carlton.drunkminecraft.DrunkMinecraft;
import me.carlton.drunkminecraft.dataholder.DrinkAction;
import me.carlton.drunkminecraft.newevents.DrinkEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class PlayerDrinkProfile {
    private double drinksTaken;
    private int drinksToGive;
    private UUID playerUUID;
    private String playerName;
    private static final long refreshTicks = 20;
    private HashMap<DrinkAction,Integer> drinkActionCooldowns = new HashMap<>();

    public PlayerDrinkProfile(Player player) {
        this(player.getUniqueId());
    }

    public PlayerDrinkProfile(UUID playerUUID) {
        this.playerName = Bukkit.getPlayer(playerUUID).getDisplayName();
        if (this.playerName == null) {
            this.playerName = "???";
        }
        this.playerUUID = playerUUID;
    }

    public int getDrinksToGive() {
        return drinksToGive;
    }


    public double getDrinksTaken() {
        return drinksTaken;
    }

    public UUID getPlayerUUID() {
        return playerUUID;
    }

    public String getPlayerName() {
        return playerName;
    }

    public boolean isDrinkActionOnCooldown(DrinkAction drinkAction) {
        if (drinkActionCooldowns.containsKey(drinkAction)) {
            if (drinkActionCooldowns.get(drinkAction) > 0) {
                return true;
            }
        }
        return false;
    }

    public void startCooldown(DrinkAction drinkAction,int cooldown) {
        if (cooldown < 1) {
            return;
        }
        if (!isDrinkActionOnCooldown(drinkAction)) {
            drinkActionCooldowns.put(drinkAction,cooldown);
            coolDownTimer(drinkAction);
        }
    }

    public void setDrinksTaken(int drinksTaken) {
        this.drinksTaken = drinksTaken;
    }

    public void addDrinksTaken(double drinksTaken) {
        this.drinksTaken += drinksTaken;
    }

    public void setDrinksToGive(int drinksToGive) {
        this.drinksToGive = drinksToGive;
    }

    public void addDrinksToGive(int drinksToGive) {
        this.drinksToGive += drinksToGive;
    }

    public void setPlayerUUID(UUID playerUUID) {
        this.playerUUID = playerUUID;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void coolDownTimer(DrinkAction drinkAction) {
        Plugin plugin = DrunkMinecraft.getPlugin(DrunkMinecraft.class);
        int taskID = new BukkitRunnable() {
            @Override
            public void run() {
                if (drinkActionCooldowns.get(drinkAction) < 1) {
                    drinkActionCooldowns.put(drinkAction,0);
                    cancel();
                }
                drinkActionCooldowns.put(drinkAction,drinkActionCooldowns.get(drinkAction)-1);
            }
        }.runTaskTimer(plugin,refreshTicks,refreshTicks).getTaskId();
    }

}
