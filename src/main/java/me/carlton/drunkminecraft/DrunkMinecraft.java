package me.carlton.drunkminecraft;

import me.carlton.drunkminecraft.commands.DmCommands;
import me.carlton.drunkminecraft.dataholder.DrinkAction;
import me.carlton.drunkminecraft.dataholder.DrinkActionManager;
import me.carlton.drunkminecraft.drinktracking.DrinkDistributor;
import me.carlton.drunkminecraft.drinktracking.DrinkScoreboard;
import me.carlton.drunkminecraft.drinktracking.PlayerProfileStorage;
import me.carlton.drunkminecraft.newevents.DrinkEventCaller;
import me.carlton.drunkminecraft.utility.DrinkMessager;
import me.carlton.drunkminecraft.utility.DrunkMinecraftPrint;
import org.bukkit.plugin.java.JavaPlugin;

public final class DrunkMinecraft extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        //Save all configs
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        saveResource("drinkConfig.yml",false);

        //initialize static information
        DrinkActionManager.initalizeEventToDrinkActionMap();
        PlayerProfileStorage.initializeUUIDtoProfileMap();
        DrinkScoreboard.initialize();
        DrinkMessager.initialize();

        //register events
        getServer().getPluginManager().registerEvents(new DrinkEventCaller(), this);
        getServer().getPluginManager().registerEvents(new DrinkDistributor(), this);
        getServer().getPluginManager().registerEvents(new PlayerProfileStorage(), this);
        getServer().getPluginManager().registerEvents(new DrinkScoreboard(), this);

        //Registers commands
        getCommand("dm").setExecutor(new DmCommands());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
