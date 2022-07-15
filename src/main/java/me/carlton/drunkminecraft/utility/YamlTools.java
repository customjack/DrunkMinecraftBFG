package me.carlton.drunkminecraft.utility;

import org.bukkit.Material;
import org.bukkit.advancement.Advancement;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.ArrayList;

public class YamlTools {
    YamlConfiguration config;

    public YamlTools(YamlConfiguration config) {
        this.config =config;
    }
    public String getYamlString(String path) {
        return getYamlString(path,true,null);
    }
    public String getYamlString(String path, boolean verbose,String defaultValue) {
        if (config.contains(path)) {
            return config.getString(path);
        }
        if (verbose) {
            DrunkMinecraftPrint.print("Warning: Missing " + path);
        }
        return null;
    }

    public int getYamlInt(String path) {
        return getYamlInt(path,true,0);
    }
    public int getYamlInt(String path, boolean verbose, int defaultValue) {
        if (config.contains(path)) {
            return config.getInt(path);
        }
        if (verbose) {
            DrunkMinecraftPrint.print("Warning: Missing " + path);
        }
        return defaultValue;
    }

    public double getYamlDouble(String path) {
        return getYamlDouble(path,true,0.0);
    }
    public double getYamlDouble(String path, boolean verbose,double defaultValue) {
        if (config.contains(path)) {
            return config.getDouble(path);
        }
        if (verbose) {
            DrunkMinecraftPrint.print("Warning: Missing " + path);
        }
        return defaultValue;
    }

    public boolean getYamlBoolean(String path) {
        return getYamlBoolean(path,true,false);
    }
    public boolean getYamlBoolean(String path, boolean verbose, boolean defaultValue) {
        if (config.contains(path)) {
            return config.getBoolean(path);
        }
        if (verbose) {
            DrunkMinecraftPrint.print("Warning: Missing " + path);
        }
        return defaultValue;
    }

    public Material getYamlMaterial(String path) {
        return getYamlMaterial(path,true,null);
    }
    public Material getYamlMaterial(String path, boolean verbose, Material defaultValue) {
        if (config.contains(path)) {
            Material material = Material.matchMaterial(config.getString(path).toUpperCase().replace(" ","_"));
            return material;
        }
        if (verbose) {
            DrunkMinecraftPrint.print("Warning: Missing " + path);
        }
        return defaultValue;
    }

    public EntityType getYamlEntityType(String path) {
        return getYamlEntityType(path,true,null);
    }
    public EntityType getYamlEntityType(String path, boolean verbose, EntityType defaultValue) {
        if (config.contains(path)) {
            EntityType entityType = EntityType.valueOf(config.getString(path).toUpperCase().replace(" ","_"));
            return entityType;
        }
        if (verbose) {
            DrunkMinecraftPrint.print("Warning: Missing " + path);
        }
        return defaultValue;
    }

    public EntityDamageEvent.DamageCause getYamlDamageCause(String path) {
        return getYamlDamageCause(path,true,null);
    }
    public EntityDamageEvent.DamageCause getYamlDamageCause(String path, boolean verbose, EntityDamageEvent.DamageCause defaultValue) {
        if (config.contains(path)) {
            EntityDamageEvent.DamageCause damageCause = EntityDamageEvent.DamageCause.valueOf(config.getString(path).toUpperCase().replace(" ","_"));
            return damageCause;
        }
        if (verbose) {
            DrunkMinecraftPrint.print("Warning: Missing " + path);
        }
        return defaultValue;
    }
    public PlayerTeleportEvent.TeleportCause getYamlTeleportCause(String path) {
        return getYamlTeleportCause(path,true,null);
    }
    public PlayerTeleportEvent.TeleportCause getYamlTeleportCause(String path, boolean verbose, PlayerTeleportEvent.TeleportCause defaultValue) {
        if (config.contains(path)) {
            PlayerTeleportEvent.TeleportCause teleportCause = PlayerTeleportEvent.TeleportCause.valueOf(config.getString(path).toUpperCase().replace(" ","_"));
            return teleportCause;
        }
        if (verbose) {
            DrunkMinecraftPrint.print("Warning: Missing " + path);
        }
        return defaultValue;
    }

    public ArrayList<Integer> getYamlIntegerArray(String path) {
        return getYamlIntegerArray(path,true,null);
    }
    public ArrayList<Integer> getYamlIntegerArray(String path, boolean verbose, ArrayList<Integer> defaultValue) {
        if (config.contains(path)) {
            ArrayList<Integer> integerArray = new ArrayList<>(config.getIntegerList(path));
        }
        if (verbose) {
            DrunkMinecraftPrint.print("Warning: Missing " + path);
        }
        return defaultValue;
    }


}
