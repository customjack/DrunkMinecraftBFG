package me.carlton.drunkminecraft.drinktracking;

import me.carlton.drunkminecraft.dataholder.DrinkAction;
import me.carlton.drunkminecraft.newevents.DrinkEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class DrinkDistributor implements Listener {

    @EventHandler(priority = EventPriority.NORMAL)
    void onDrinkEvent(DrinkEvent e) {
        DrinkAction drinkAction= e.getDrinkAction();
        Player player = e.getPlayer();
        drinkAction.makePlayerDrink(player);
    }
}
