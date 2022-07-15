package me.carlton.drunkminecraft.newevents;

import me.carlton.drunkminecraft.dataholder.DrinkAction;
import me.carlton.drunkminecraft.dataholder.DrinkActionManager;
import me.carlton.drunkminecraft.utility.DrunkMinecraftPrint;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashMap;

public class DrinkEventCaller implements Listener {
    private HashMap<Class, ArrayList<DrinkAction>> eventToDrinkActionMap = DrinkActionManager.getEventToDrinkActionMap();

    private boolean doesDrinkEventExist(Event e) {
        Class eventClass = e.getClass();
        if (eventToDrinkActionMap.containsKey(eventClass)) {
            if (!eventToDrinkActionMap.get(eventClass).isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private boolean isPossibleDrinkEvent(Event e, Player p) {
        if (p != null && doesDrinkEventExist(e)) {
            return true;
        }
        return false;
    }

    private Material getCaughtMaterial(PlayerFishEvent e) {
        if (e.getState() == PlayerFishEvent.State.CAUGHT_FISH) {
            Entity hookedEntity = e.getCaught();
            if (hookedEntity instanceof Item) {
                Material material = ((Item) hookedEntity).getItemStack().getType();
                return material;
            }
        }
        return null;
    }

    private void callDrinkEvent(Event e, Player p, DrinkAction drinkAction) {
        PluginManager pluginManager = Bukkit.getServer().getPluginManager();
        pluginManager.callEvent(new DrinkEvent(e,drinkAction,p)); //Call event
    }

    private ArrayList<Material> getHarvestedMaterials(PlayerHarvestBlockEvent e) {
        ArrayList<Material> materialList = new ArrayList<>();
        for (ItemStack itemstack : e.getItemsHarvested()) {
            if (itemstack != null) {
                materialList.add(itemstack.getType());
            }
        }
        return materialList;
    }

    private int getAmountHarvest(PlayerHarvestBlockEvent e) {
        int amountHarvested = 0;
        for (ItemStack itemstack : e.getItemsHarvested()) {
            if (itemstack != null) {
                amountHarvested += itemstack.getAmount();
            }
        }
        return amountHarvested;
    }

    private boolean isLeftClick(PlayerInteractEvent e) {
        Action a = e.getAction();
        if ((a.equals(Action.LEFT_CLICK_AIR)) || (a.equals(Action.LEFT_CLICK_BLOCK))) {
            return true;
        }
        return false;
    }

    private boolean isRightClick(PlayerInteractEvent e) {
        Action a = e.getAction();
        if ((a.equals(Action.RIGHT_CLICK_AIR)) || (a.equals(Action.RIGHT_CLICK_BLOCK))) {
            return true;
        }
        return false;
    }

    @EventHandler(priority = EventPriority.NORMAL)
    void onBreakBlock(BlockBreakEvent e) {
        Player p = e.getPlayer();
        if (isPossibleDrinkEvent(e,p)) {
            Material blockType = e.getBlock().getType();
            for (DrinkAction drinkAction : eventToDrinkActionMap.get(e.getClass())) {
                if (drinkAction.getBlockType() == null) {
                    callDrinkEvent(e,p,drinkAction);
                } else if (drinkAction.getBlockType().equals(blockType)) {
                    callDrinkEvent(e,p,drinkAction);
                }
            }
        }

    }

    @EventHandler(priority = EventPriority.NORMAL)
    void onPlayerChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        if (isPossibleDrinkEvent(e,p)) {
            String message = e.getMessage();
            for (DrinkAction drinkAction : eventToDrinkActionMap.get(e.getClass())) {
                if (drinkAction.getMessage() == null && drinkAction.getMessageContains() == null) {
                    callDrinkEvent(e,p,drinkAction);
                } else if (drinkAction.getMessage().equalsIgnoreCase(message)) {
                    callDrinkEvent(e,p,drinkAction);
                } else if (message.contains(drinkAction.getMessageContains())) {
                    callDrinkEvent(e,p,drinkAction);
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    void onPlayerConsume(PlayerItemConsumeEvent e) {
        Player p = e.getPlayer();
        if (isPossibleDrinkEvent(e,p)) {
            Material itemType = e.getItem().getType();
            for (DrinkAction drinkAction : eventToDrinkActionMap.get(e.getClass())) {
                if (drinkAction.getItemType() == null) {
                    callDrinkEvent(e,p,drinkAction);
                } else if (drinkAction.getItemType().equals(itemType)) {
                    callDrinkEvent(e,p,drinkAction);
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    void onPlayerDie(PlayerDeathEvent e) {
        Player p = e.getEntity();
        if (isPossibleDrinkEvent(e,p)) {
            DamageCause damageCause = p.getLastDamageCause().getCause();
            EntityType entityType   = p.getLastDamageCause().getEntityType();
            for (DrinkAction drinkAction : eventToDrinkActionMap.get(e.getClass())) {
                if (drinkAction.getDamageCause() == null && drinkAction.getEntityType() == null) {
                    callDrinkEvent(e,p,drinkAction);
                } else if (drinkAction.getDamageCause().equals(damageCause) && drinkAction.getEntityType() == null) {
                    callDrinkEvent(e,p,drinkAction);
                } else if (drinkAction.getDamageCause() == null && drinkAction.getEntityType().equals(entityType)) {
                    callDrinkEvent(e,p,drinkAction);
                } else if (drinkAction.getDamageCause().equals(damageCause) && drinkAction.getEntityType().equals(entityType)) {
                    callDrinkEvent(e,p,drinkAction);
                }

            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    void onPlayerDropItem(PlayerDropItemEvent e) {
        Player p = e.getPlayer();
        if (isPossibleDrinkEvent(e,p)) {
            int amount = e.getItemDrop().getItemStack().getAmount();
            Material itemType = e.getItemDrop().getItemStack().getType();
            for (DrinkAction drinkAction : eventToDrinkActionMap.get(e.getClass())) {
                if (drinkAction.getAmount() == 0 && drinkAction.getItemType() == null) {
                    callDrinkEvent(e,p,drinkAction);
                } else if (drinkAction.getAmount() == amount && drinkAction.getItemType() == null) {
                    callDrinkEvent(e,p,drinkAction);
                } else if (drinkAction.getAmount() == 0 && drinkAction.getItemType().equals(itemType)) {
                    callDrinkEvent(e,p,drinkAction);
                } else if (drinkAction.getAmount() == amount && drinkAction.getItemType().equals(itemType)) {
                    callDrinkEvent(e,p,drinkAction);
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    void onLevelChange(PlayerLevelChangeEvent e) {
        Player p = e.getPlayer();
        if (isPossibleDrinkEvent(e,p)) {
            int newLevel = e.getNewLevel();
            for (DrinkAction drinkAction : eventToDrinkActionMap.get(e.getClass())) {
                if (drinkAction.getLevelAchieved() == -1) {
                    callDrinkEvent(e,p,drinkAction);
                } else if (drinkAction.getLevelAchieved() == newLevel) {
                    callDrinkEvent(e, p, drinkAction);
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    void onPlayerFish(PlayerFishEvent e) {
        Player p = e.getPlayer();
        if (isPossibleDrinkEvent(e,p)) {
            Material itemType = getCaughtMaterial(e);
            if (itemType == null) {
                return;
            }
            for (DrinkAction drinkAction : eventToDrinkActionMap.get(e.getClass())) {
                if (drinkAction.getItemType() == null) {
                    callDrinkEvent(e,p,drinkAction);
                } else if (drinkAction.getItemType().equals(itemType)) {
                    callDrinkEvent(e,p,drinkAction);
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    void onPlayerHarvest(PlayerHarvestBlockEvent e) {
        Player p = e.getPlayer();
        if (isPossibleDrinkEvent(e,p)) {
            int amount = getAmountHarvest(e);
            ArrayList<Material> materialList = getHarvestedMaterials(e);
            for (DrinkAction drinkAction : eventToDrinkActionMap.get(e.getClass())) {
                if (drinkAction.getAmount() == 0 && drinkAction.getItemType() == null) {
                    callDrinkEvent(e,p,drinkAction);
                } else if (drinkAction.getAmount() == amount && drinkAction.getItemType() == null) {
                    callDrinkEvent(e,p,drinkAction);
                } else if (drinkAction.getAmount() == 0 && materialList.contains(drinkAction.getItemType())) {
                    callDrinkEvent(e,p,drinkAction);
                } else if (drinkAction.getAmount() == amount && materialList.contains(drinkAction.getItemType())) {
                    callDrinkEvent(e,p,drinkAction);
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    void onPlayerInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (isPossibleDrinkEvent(e,p)) {
            boolean isRightClick = isRightClick(e);
            boolean isLeftClick = isLeftClick(e);
            Material itemType = e.getClickedBlock().getType();
            for (DrinkAction drinkAction : eventToDrinkActionMap.get(e.getClass())) {
                if (isRightClick && drinkAction.isRightClick()) {
                    if (drinkAction.getItemType() == null) {
                        callDrinkEvent(e,p,drinkAction);
                    } else if (drinkAction.getItemType().equals(itemType)) {
                        callDrinkEvent(e,p,drinkAction);
                    }
                } else if (isLeftClick && drinkAction.isLeftClick()) {
                    if (drinkAction.getItemType() == null) {
                        callDrinkEvent(e,p,drinkAction);
                    } else if (drinkAction.getItemType().equals(itemType)) {
                        callDrinkEvent(e,p,drinkAction);
                    }
                } else if (!drinkAction.isRightClick() && !drinkAction.isLeftClick()) {
                    if (drinkAction.getItemType() == null) {
                        callDrinkEvent(e,p,drinkAction);
                    } else if (drinkAction.getItemType().equals(itemType)) {
                        callDrinkEvent(e,p,drinkAction);
                    }
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    void onPlayerInteractEntity(PlayerInteractEntityEvent e) {
        Player p = e.getPlayer();
        if (isPossibleDrinkEvent(e,p)) {
            EntityType entityType = e.getRightClicked().getType();
            for (DrinkAction drinkAction : eventToDrinkActionMap.get(e.getClass())) {
                if (drinkAction.getEntityType() == null) {
                    callDrinkEvent(e,p,drinkAction);
                } else if (drinkAction.getEntityType().equals(entityType)) {
                    callDrinkEvent(e,p,drinkAction);
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    void onPlayerItemBreak(PlayerItemBreakEvent e) {
        Player p = e.getPlayer();
        if (isPossibleDrinkEvent(e,p)) {
            Material itemType = e.getBrokenItem().getType();
            for (DrinkAction drinkAction : eventToDrinkActionMap.get(e.getClass())) {
                if (drinkAction.getItemType() == null) {
                    callDrinkEvent(e,p,drinkAction);
                } else if (drinkAction.getItemType().equals(itemType)) {
                    callDrinkEvent(e,p,drinkAction);
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (isPossibleDrinkEvent(e,p)) {
            for (DrinkAction drinkAction : eventToDrinkActionMap.get(e.getClass())) {
                callDrinkEvent(e,p,drinkAction);
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    void onPlayerKill(EntityDeathEvent e) {
        if (e.getEntity().getKiller() instanceof Player) {
            Player p = e.getEntity().getKiller();
            if (isPossibleDrinkEvent(e, p)) {
                EntityType entityType = e.getEntityType();
                for (DrinkAction drinkAction : eventToDrinkActionMap.get(e.getClass())) {
                    if (drinkAction.getEntityType() == null) {
                        callDrinkEvent(e,p,drinkAction);
                    } else if (drinkAction.getEntityType().equals(entityType)) {
                        callDrinkEvent(e,p,drinkAction);
                    }
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    void onPlayerMend(PlayerItemMendEvent e) {
        Player p = e.getPlayer();
        if (isPossibleDrinkEvent(e,p)) {
            Material itemType = e.getItem().getType();
            for (DrinkAction drinkAction : eventToDrinkActionMap.get(e.getClass())) {
                if (drinkAction.getItemType() == null) {
                    callDrinkEvent(e,p,drinkAction);
                } else if (drinkAction.getItemType().equals(itemType)) {
                    callDrinkEvent(e,p,drinkAction);
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    void onPlayerMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if (isPossibleDrinkEvent(e,p)) {
            double speed = e.getPlayer().getVelocity().distance(new Vector(0,0,0));
            for (DrinkAction drinkAction : eventToDrinkActionMap.get(e.getClass())) {
                if (drinkAction.getSpeed() < speed) {
                    callDrinkEvent(e,p,drinkAction);
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    void onPlayerPickup(EntityPickupItemEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (isPossibleDrinkEvent(e, p)) {
                Material itemType = e.getItem().getItemStack().getType();
                int amount = e.getItem().getItemStack().getAmount();
                for (DrinkAction drinkAction : eventToDrinkActionMap.get(e.getClass())) {
                    if (drinkAction.getAmount() == 0 && drinkAction.getItemType() == null) {
                        callDrinkEvent(e,p,drinkAction);
                    } else if (drinkAction.getAmount() == amount && drinkAction.getItemType() == null) {
                        callDrinkEvent(e,p,drinkAction);
                    } else if (drinkAction.getAmount() == 0 && drinkAction.getItemType().equals(itemType)) {
                        callDrinkEvent(e,p,drinkAction);
                    } else if (drinkAction.getAmount() == amount && drinkAction.getItemType().equals(itemType)) {
                        callDrinkEvent(e,p,drinkAction);
                    }
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    void onPlaceBlock(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        if (isPossibleDrinkEvent(e,p)) {
            Material blockType = e.getBlock().getType();
            for (DrinkAction drinkAction : eventToDrinkActionMap.get(e.getClass())) {
                if (drinkAction.getBlockType() == null) {
                    callDrinkEvent(e,p,drinkAction);
                } else if (drinkAction.getBlockType().equals(blockType)) {
                    callDrinkEvent(e,p,drinkAction);
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    void onPlayerTakeDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (isPossibleDrinkEvent(e, p)) {
                DamageCause damageCause = e.getCause();
                for (DrinkAction drinkAction : eventToDrinkActionMap.get(e.getClass())) {
                    if (drinkAction.getDamageCause() == null) {
                        callDrinkEvent(e,p,drinkAction);
                    } else if (drinkAction.getDamageCause().equals(damageCause)) {
                        callDrinkEvent(e,p,drinkAction);
                    }
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    void onPlayerTame(EntityTameEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (isPossibleDrinkEvent(e, p)) {
                EntityType entityType = e.getEntityType();
                for (DrinkAction drinkAction : eventToDrinkActionMap.get(e.getClass())) {
                    if (drinkAction.getEntityType() == null) {
                        callDrinkEvent(e,p,drinkAction);
                    } else if (drinkAction.getEntityType().equals(entityType)) {
                        callDrinkEvent(e,p,drinkAction);
                    }
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    void onPlayerTeleport(PlayerTeleportEvent e) {
        Player p = e.getPlayer();
        if (isPossibleDrinkEvent(e, p)) {
            double distance = e.getTo().distance(e.getFrom());
            PlayerTeleportEvent.TeleportCause teleportCause = e.getCause();
            for (DrinkAction drinkAction : eventToDrinkActionMap.get(e.getClass())) {
                if (drinkAction.getDistance() == 0 && drinkAction.getTeleportCause() == null) {
                    callDrinkEvent(e,p,drinkAction);
                } else if (drinkAction.getDistance() == distance && drinkAction.getTeleportCause() == null) {
                    callDrinkEvent(e,p,drinkAction);
                } else if (drinkAction.getDistance() == 0 && teleportCause.equals(drinkAction.getTeleportCause())) {
                    callDrinkEvent(e,p,drinkAction);
                } else if (drinkAction.getDistance() == distance && teleportCause.equals(drinkAction.getTeleportCause())) {
                    callDrinkEvent(e,p,drinkAction);
                }
            }
        }
    }
}
