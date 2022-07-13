package me.carlton.drunkminecraft.dataholder.drinkactionchildren;

import me.carlton.drunkminecraft.dataholder.DrinkAction;
import org.bukkit.event.player.PlayerTeleportEvent;

public class DrinkActionTeleport extends DrinkAction {
    private PlayerTeleportEvent.TeleportCause teleportCause;
    private double distanceTeleported;

    public DrinkActionTeleport(String name, String instructions, String actionName, double drinkValue) {
        super(name, instructions, actionName, drinkValue);
    }

    public DrinkActionTeleport(String name, String instructions, String actionName, int drinkMin, int drinkMax) {
        super(name, instructions, actionName, drinkMin, drinkMax);
    }

    public PlayerTeleportEvent.TeleportCause getTeleportCause() {
        return teleportCause;
    }

    public void setTeleportCause(PlayerTeleportEvent.TeleportCause teleportCause) {
        this.teleportCause = teleportCause;
    }

    public double getDistanceTeleported() {
        return distanceTeleported;
    }

    public void setDistanceTeleported(double distanceTeleported) {
        this.distanceTeleported = distanceTeleported;
    }
}
