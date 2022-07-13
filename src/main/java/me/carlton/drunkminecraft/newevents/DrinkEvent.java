package me.carlton.drunkminecraft.newevents;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

/*
 * This event constitutes multiple event in the case that an ability item is moved
 */
public class DrinkEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;
    private Event event;
    private Player drinkingPlayer;

    private double drinks;

    private DrinkEvent(Event event, double drinks, Player drinkingPlayer) {
        this.event = event;
        if (event instanceof Cancellable) {
            this.cancelled = ((Cancellable) event).isCancelled();
        }
        this.drinks = drinks;
        this.drinkingPlayer = drinkingPlayer;
    }

    public void setDrinks(double drinks) {
        this.drinks = drinks;
    }

    public double getDrinks() {
        return this.drinks;
    }


    /**
     * Getter for the player
     * @return Player who moved the ability item
     */
    public Player getPlayer() {
        return drinkingPlayer;
    }

    /**
     * Getter for cancelled
     * @return true if the associated event is cancelled
     */
    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    /**
     * Setter for cancrlled, also cancels the event
     * @param cancel true if the event is to be cancelled
     */
    @Override
    public void setCancelled(boolean cancel) {
        if (event instanceof Cancellable) {
            ((Cancellable) event).setCancelled(cancel);
        }
        this.cancelled = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
