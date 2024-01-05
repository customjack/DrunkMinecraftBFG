package me.carlton.drunkminecraft.utility;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class DrunkMinecraftPrint {
    public static void print(Object printable) {
        if (printable != null) {
            System.out.println("[Drunk Minecraft] " + printable.toString());
        } else {
            System.out.println("[Drunk Minecraft] null");
        }
    }

    public static void broadcast(Object printable) {
        if (printable != null) {
            Bukkit.getServer().broadcastMessage(ChatColor.GRAY + "[Drunk Minecraft] " + ChatColor.YELLOW + printable.toString());
        } else {
            Bukkit.getServer().broadcastMessage(ChatColor.GRAY + "[Drunk Minecraft] " +ChatColor.YELLOW + "null");
        }
    }
}
