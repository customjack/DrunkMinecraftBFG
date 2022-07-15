package me.carlton.drunkminecraft.utility;

import me.carlton.drunkminecraft.DrunkMinecraft;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class DrinkMessager {
    private static int fadeInTicks;
    private static int fadeOutTicks;
    private static int stayTicks;

    public static void initialize() {
        Plugin plugin = DrunkMinecraft.getPlugin(DrunkMinecraft.class);
        FileConfiguration config = plugin.getConfig();
        fadeInTicks = config.getInt("drinkNotification.fadeInTicks");
        fadeOutTicks = config.getInt("drinkNotification.fadeoutTicks");
        stayTicks = config.getInt("drinkNotification.stayTicks");
    }

    public static void sendInstructionsAndReason(Player p, String instruction, String reason, boolean giveReason) {
        if (giveReason) {
            p.sendTitle(ChatColor.YELLOW + instruction, ChatColor.GRAY + reason, fadeInTicks, stayTicks, fadeOutTicks);
        } else {
            p.sendTitle(ChatColor.YELLOW + instruction, "", fadeInTicks, stayTicks, fadeOutTicks);
        }

    }
}
