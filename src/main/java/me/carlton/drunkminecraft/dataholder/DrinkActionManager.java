package me.carlton.drunkminecraft.dataholder;

import me.carlton.drunkminecraft.DrunkMinecraft;
import me.carlton.drunkminecraft.dataholder.drinkactionchildren.DrinkActionBreakBlock;
import me.carlton.drunkminecraft.utility.DrunkMinecraftPrint;
import me.carlton.drunkminecraft.utility.YamlTools;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.*;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DrinkActionManager {
    private static HashMap<Class, ArrayList<DrinkAction>> eventToDrinkActionMap;
    /*
    private static final List<String> DRINK_ACTIONS        = Arrays.asList(new String[]{"BREAK_BLOCK","CHAT","CONSUME","DIE","DROP",
                                                                               "EXP_CHANGE","FISH","GET_ADVANCEMENT",
                                                                               "HARVEST","INTERACT","INTERACT_ENTITY",
                                                                               "ITEM_BREAK","JOIN","KILL","MEND","MOVE",
                                                                               "PICKUP","PLACE_BLOCK","TAKE_DAMAGE","TAME",
                                                                               "TELEPORT"});

    private static final List<String> BREAK_BLOCK_KEYS     = Arrays.asList(new String[]{"blockType"});
    private static final List<String> CHAT_KEYS            = Arrays.asList(new String[]{"message","messageContains"});
    private static final List<String> CONSUME_KEYS         = Arrays.asList(new String[]{"itemType"});
    private static final List<String> DIE_KEYS             = Arrays.asList(new String[]{"damageCause","killer"});
    private static final List<String> DROP_KEYS            = Arrays.asList(new String[]{"itemType","amount"});
    private static final List<String> EXP_CHANGE_KEYS      = Arrays.asList(new String[]{"itemType","amount"});
    private static final List<String> FISH_KEYS            = Arrays.asList(new String[]{"itemType"});
    private static final List<String> GET_ADVANCEMENT_KEYS = Arrays.asList(new String[]{"advancement"});
    private static final List<String> HARVEST_KEYS         = Arrays.asList(new String[]{"itemType","amount"});
    private static final List<String> INTERACT_KEYS        = Arrays.asList(new String[]{"itemType"});
    private static final List<String> INTERACT_ENTITY_KEYS = Arrays.asList(new String[]{"entity"});
    private static final List<String> ITEM_BREAK_KEYS      = Arrays.asList(new String[]{"itemType"});
    private static final List<String> JOIN_KEYS            = Arrays.asList(new String[]{});
    private static final List<String> KILL_KEYS            = Arrays.asList(new String[]{"entity"});
    private static final List<String> MEND_KEYS            = Arrays.asList(new String[]{"itemType"});
    private static final List<String> MOVE_KEYS            = Arrays.asList(new String[]{"speed"});
    private static final List<String> PICKUP_KEYS          = Arrays.asList(new String[]{"itemType","amount"});
    private static final List<String> PLACE_BLOCK_KEYS     = Arrays.asList(new String[]{"blockType"});
    private static final List<String> TAKE_DAMAGE_KEYS     = Arrays.asList(new String[]{"damageCause"});
    private static final List<String> TAME_KEYS            = Arrays.asList(new String[]{"entity"});
    private static final List<String> TELEPORT_KEYS        = Arrays.asList(new String[]{"teleportCause","distance"});
     */

    private static HashMap<String, Class> ACTION_EVENT_MAP;


    public static HashMap<Class, ArrayList<DrinkAction>> getEventToDrinkActionMap() {
        return eventToDrinkActionMap;
    }

    private static void addToEventToDrinkActionMap(Class eventClass, DrinkAction action) {
        ArrayList<DrinkAction> drinkActions;
        if (eventToDrinkActionMap.containsKey(eventClass)) {
            drinkActions = eventToDrinkActionMap.get(eventClass);
        } else {
            drinkActions = new ArrayList<>();
        }
        drinkActions.add(action);
        eventToDrinkActionMap.put(eventClass,drinkActions);
    }

    private static void initializeMaps() {
        eventToDrinkActionMap = new HashMap<>();
        ACTION_EVENT_MAP = new HashMap<>();
        ACTION_EVENT_MAP.put("BREAK_BLOCK", BlockBreakEvent.class);
        ACTION_EVENT_MAP.put("CHAT", AsyncPlayerChatEvent.class);
        ACTION_EVENT_MAP.put("CONSUME", PlayerItemConsumeEvent.class);
        ACTION_EVENT_MAP.put("DIE", PlayerDeathEvent.class);
        ACTION_EVENT_MAP.put("DROP", PlayerDropItemEvent.class);
        ACTION_EVENT_MAP.put("LEVEL_CHANGE", PlayerLevelChangeEvent.class);
        ACTION_EVENT_MAP.put("FISH", PlayerFishEvent.class);
        ACTION_EVENT_MAP.put("GET_ADVANCEMENT", PlayerAdvancementDoneEvent.class);
        ACTION_EVENT_MAP.put("HARVEST", PlayerHarvestBlockEvent.class);
        ACTION_EVENT_MAP.put("INTERACT", PlayerInteractEvent.class);
        ACTION_EVENT_MAP.put("INTERACT_ENTITY", PlayerInteractEntityEvent.class);
        ACTION_EVENT_MAP.put("ITEM_BREAK", PlayerItemBreakEvent.class);
        ACTION_EVENT_MAP.put("JOIN", PlayerJoinEvent.class);
        ACTION_EVENT_MAP.put("KILL", EntityDeathEvent.class);
        ACTION_EVENT_MAP.put("MEND", PlayerItemMendEvent.class);
        ACTION_EVENT_MAP.put("MOVE", PlayerMoveEvent.class);
        ACTION_EVENT_MAP.put("PICKUP", EntityPickupItemEvent.class);
        ACTION_EVENT_MAP.put("PLACE_BLOCK", BlockPlaceEvent.class);
        ACTION_EVENT_MAP.put("TAKE_DAMAGE", EntityDamageEvent.class);
        ACTION_EVENT_MAP.put("TAME", EntityTameEvent.class);
        ACTION_EVENT_MAP.put("TELEPORT", PlayerTeleportEvent.class);
    }

    public static void initalizeEventToDrinkActionMap() {
        initializeMaps();
        Plugin plugin = DrunkMinecraft.getPlugin(DrunkMinecraft.class);
        File f = new File(plugin.getDataFolder(),"drinkConfig.yml");
        f.setReadable(true,false);
        f.setWritable(true,false);
        YamlConfiguration drinkConfig = YamlConfiguration.loadConfiguration(f);
        for (String key : drinkConfig.getKeys(false)) {
            DrinkAction drinkAction = createDrinkAction(drinkConfig,key);
            if (drinkAction == null) {
                continue;
            }
            YamlTools yamlTools = new YamlTools(drinkConfig);
            String preparseActionName = yamlTools.getYamlString(key+".action");
            String actionName = preparseActionName.toUpperCase().replace(" ","_");
            Class eventClass = getEventClassFromAction(actionName);
            addToEventToDrinkActionMap(eventClass,drinkAction);
        }

    }

    private static DrinkAction createDrinkAction(YamlConfiguration drinkConfig, String key) {
        YamlTools yamlTools = new YamlTools(drinkConfig);
        String name = key.replace("-", " ");
        String instruction = yamlTools.getYamlString( key + ".instruction");
        String preparseActionName = yamlTools.getYamlString(key+".action");
        if ((name == null || instruction == null || preparseActionName == null)) {
            DrunkMinecraftPrint.print("Skipping " + key + " ...");
            return null;
        }
        String actionName = preparseActionName.toUpperCase().replace(" ","_");
        Class action = getEventClassFromAction(actionName);
        double drinkValue = yamlTools.getYamlDouble( key + ".drinkValue",true,1);
        int drinkMin = yamlTools.getYamlInt( key + ".drinkMin",false, 0);
        int drinkMax = yamlTools.getYamlInt( key + ".drinkMax",false, 0);
        String reason = yamlTools.getYamlString( key + ".reason",false, null );
        String broadcastMessage = yamlTools.getYamlString( key + ".broadcastMessage",false, null);
        boolean giveReason = yamlTools.getYamlBoolean( key + ".giveReason",false,true);
        double chance = yamlTools.getYamlDouble( key + ".chance",false,1.0);
        double cooldown = yamlTools.getYamlDouble( key + ".cooldown",false, 0);
        int drinksToGive = yamlTools.getYamlInt( key + ".drinksToGive",false, 0);

        DrinkAction drinkAction = new DrinkAction(name,instruction,actionName,drinkValue);
        setDrinkInformation(drinkAction,drinkMin,drinkMax,reason,broadcastMessage,giveReason,chance,cooldown,drinksToGive);
        if (drinkConfig.contains(key+".actionConditions")) {
            String conditionsPath = key+".actionConditions";
            if (action.equals(BlockBreakEvent.class)) {
                Material material = yamlTools.getYamlMaterial(conditionsPath+".blockType",false,null);
                drinkAction.setBlockType(material);
            } else if (action.equals(AsyncPlayerChatEvent.class)) {
                drinkAction.setMessage(yamlTools.getYamlString(conditionsPath+".message",false,null));
                drinkAction.setMessageContains(yamlTools.getYamlString(conditionsPath+".messageContains",false,null));
            } else if (action.equals(PlayerItemConsumeEvent.class)) {
                Material material = yamlTools.getYamlMaterial(conditionsPath+".itemType",false,null);
                drinkAction.setItemType(material);
            } else if (action.equals(PlayerDeathEvent.class)) {
                DamageCause damageCause = yamlTools.getYamlDamageCause(conditionsPath+".damageCause",false,null);
                drinkAction.setDamageCause(damageCause);
                EntityType entityType = yamlTools.getYamlEntityType(conditionsPath+".killer",false,null);
                drinkAction.setKiller(entityType);
            } else if (action.equals(PlayerDropItemEvent.class)) {
                Material material = yamlTools.getYamlMaterial(conditionsPath+".itemType",false,null);
                drinkAction.setItemType(material);
                drinkAction.setAmount(yamlTools.getYamlInt(conditionsPath+".amount",false,0));
            } else if (action.equals(PlayerLevelChangeEvent.class)) {
                drinkAction.setLevelAchieved(yamlTools.getYamlInt(conditionsPath+".levelAchieved",false,-1));
            } else if (action.equals(PlayerFishEvent.class)) {
                Material material = yamlTools.getYamlMaterial(conditionsPath+".itemType",false,null);
                drinkAction.setItemType(material);
            } else if (action.equals(PlayerAdvancementDoneEvent.class)) {
                //To do, if possible
                //drinkAction.setAdvancement();
            } else if (action.equals(PlayerHarvestBlockEvent.class)) {
                Material material = yamlTools.getYamlMaterial(conditionsPath+".itemType",false,null);
                drinkAction.setItemType(material);
                drinkAction.setAmount(yamlTools.getYamlInt(conditionsPath+".amount",false,0));
            } else if (action.equals(PlayerInteractEvent.class)) {
                Material material = yamlTools.getYamlMaterial(conditionsPath+".itemType",false,null);
                drinkAction.setItemType(material);
                drinkAction.setLeftClick(yamlTools.getYamlBoolean(conditionsPath+".isLeftClick",false,false));
                drinkAction.setRightClick(yamlTools.getYamlBoolean(conditionsPath+".isRightClick",false,false));
            } else if (action.equals(PlayerInteractEntityEvent.class)) {
                EntityType entityType = yamlTools.getYamlEntityType(conditionsPath+".entity",false,null);
                drinkAction.setEntityType(entityType);
            } else if (action.equals(PlayerItemBreakEvent.class)) {
                Material material = yamlTools.getYamlMaterial(conditionsPath+".itemType",false,null);
                drinkAction.setItemType(material);
            } else if (action.equals(PlayerJoinEvent.class)) {
                //Nothing in here for now
            } else if (action.equals(EntityDeathEvent.class)) {
                EntityType entityType = yamlTools.getYamlEntityType(conditionsPath+".entity",false,null);
                drinkAction.setEntityType(entityType);
            } else if (action.equals(PlayerItemMendEvent.class)) {
                Material material = yamlTools.getYamlMaterial(conditionsPath+".itemType",false,null);
                drinkAction.setItemType(material);
            } else if (action.equals(PlayerMoveEvent.class)) {
                drinkAction.setSpeed(yamlTools.getYamlDouble(conditionsPath+".speed",false,1000.0));
            } else if (action.equals(EntityPickupItemEvent.class)) {
                Material material = yamlTools.getYamlMaterial(conditionsPath+".itemType",false,null);
                drinkAction.setItemType(material);
                drinkAction.setAmount(yamlTools.getYamlInt(conditionsPath+".amount",false,0));
            } else if (action.equals(BlockPlaceEvent.class)) {
                Material material = yamlTools.getYamlMaterial(conditionsPath+".blockType",false,null);
                drinkAction.setBlockType(material);
            } else if (action.equals(EntityDamageEvent.class)) {
                DamageCause damageCause = yamlTools.getYamlDamageCause(conditionsPath+".damageCause",false,null);
                drinkAction.setDamageCause(damageCause);
            } else if (action.equals(EntityTameEvent.class)) {
                EntityType entityType = yamlTools.getYamlEntityType(conditionsPath+".entity",false,null);
                drinkAction.setEntityType(entityType);
            } else if (action.equals(PlayerTeleportEvent.class)) {
                PlayerTeleportEvent.TeleportCause teleportCause = yamlTools.getYamlTeleportCause(conditionsPath+".teleportCause",false,null);
                drinkAction.setTeleportCause(teleportCause);
                drinkAction.setDistance(yamlTools.getYamlDouble(conditionsPath+".distance",false,999999999.0));
            }
        }
        return drinkAction;
    }

    private static Class getEventClassFromAction(String actionName) {
        if (ACTION_EVENT_MAP.containsKey(actionName)) {
            return ACTION_EVENT_MAP.get(actionName);
        }
        return null;
    }

    private static void setDrinkInformation(DrinkAction drinkAction, int drinkMin, int drinkMax, String reason,
                                            String broadcastMessage, boolean giveReason, double chance,
                                            double cooldown, int drinksToGive) {
        drinkAction.setDrinkMin(drinkMin);
        drinkAction.setDrinkMax(drinkMax);
        drinkAction.setReason(reason);
        drinkAction.setBroadcastString(broadcastMessage);
        drinkAction.setGiveReason(giveReason);
        drinkAction.setChance(chance);
        drinkAction.setCooldown(cooldown);
        drinkAction.setDrinksToGive(drinksToGive);
    }

}
