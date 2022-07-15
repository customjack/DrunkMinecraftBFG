package me.carlton.drunkminecraft.drinktracking;

import me.carlton.drunkminecraft.newevents.DrinkEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.*;

public class PlayerProfileStorage implements Listener {
    private static HashMap<UUID,PlayerDrinkProfile> UUIDtoProfileMap;

    public static void initializeUUIDtoProfileMap(){
        UUIDtoProfileMap = new HashMap<>();
    }

    @EventHandler(priority = EventPriority.LOWEST)
    void onPlayerJoin(PlayerJoinEvent e) {
        addPlayerDrinkProfile(e.getPlayer());

    }

    public boolean doesPlayerHaveProfile(Player p) {
        if (UUIDtoProfileMap.containsKey(p.getUniqueId())) {
            return true;
        }
        return false;
    }

    public PlayerDrinkProfile getPlayerDrinkProfile(UUID playerUUID) {
        if (UUIDtoProfileMap.containsKey(playerUUID)) {
            return UUIDtoProfileMap.get(playerUUID);
        }
        addPlayerDrinkProfile(playerUUID);
        return getPlayerDrinkProfile(playerUUID);
    }
    public PlayerDrinkProfile getPlayerDrinkProfile(Player p) {
        return getPlayerDrinkProfile(p.getUniqueId());
    }

    public void addPlayerDrinkProfile(UUID playerUUID) {
        if (!UUIDtoProfileMap.containsKey(playerUUID)) {
            UUIDtoProfileMap.put(playerUUID,new PlayerDrinkProfile(playerUUID));
        }
    }

    public void addPlayerDrinkProfile(Player player) {
        addPlayerDrinkProfile(player.getUniqueId());
    }

    public ArrayList<PlayerDrinkProfile> getTopDrinkers(int listSize) {
        ArrayList<PlayerDrinkProfile> drinkersSorted = new ArrayList<>();
        for (UUID key : UUIDtoProfileMap.keySet()) {
            drinkersSorted.add(UUIDtoProfileMap.get(key));
        }
        drinkersSorted.sort(new Comparator<PlayerDrinkProfile>() {
            @Override
            public int compare(PlayerDrinkProfile o1, PlayerDrinkProfile o2) {
                if (o1.getDrinksTaken() < o2.getDrinksTaken()) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });
        ArrayList<PlayerDrinkProfile> topDrinkers = new ArrayList<>();
        int numOnLeaderBoard = Math.min(drinkersSorted.size(),listSize);
        for (int i=0; i<numOnLeaderBoard; i++) {
            topDrinkers.add(drinkersSorted.get(i));
        }
        return topDrinkers;
    }

    public ArrayList<PlayerDrinkProfile> getTopDrinkers() {
        return getTopDrinkers(UUIDtoProfileMap.size());
    }
}
