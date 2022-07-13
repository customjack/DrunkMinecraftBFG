package me.carlton.drunkminecraft;

import org.bukkit.plugin.java.JavaPlugin;

public final class DrunkMinecraft extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        //Events
        //getServer().getPluginManager().registerEvents(, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
