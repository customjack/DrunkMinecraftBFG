package me.carlton.drunkminecraft.drinktracking;

import me.carlton.drunkminecraft.DrunkMinecraft;
import me.carlton.drunkminecraft.dataholder.DrinkAction;
import me.carlton.drunkminecraft.utility.DrunkMinecraftPrint;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DrinkScoreboard implements Listener {
    private static boolean isScoreboardOn;
    private static boolean showDrinkTotal;
    private static boolean showDrinksToGive;
    private static boolean showTopDrinkers;
    private static int numberOfTopDrinkers;
    private static String title;

    public static void initialize() {
        Plugin plugin = DrunkMinecraft.getPlugin(DrunkMinecraft.class);
        FileConfiguration config = plugin.getConfig();
        isScoreboardOn      = config.getBoolean("scoreboard.scoreboardOn");
        showDrinkTotal      = config.getBoolean("scoreboard.showDrinks");
        showDrinksToGive    = config.getBoolean("scoreboard.showDrinksToGive");
        showTopDrinkers     = config.getBoolean("scoreboard.showTopDrinkers");
        numberOfTopDrinkers = config.getInt("scoreboard.numberOfTopDrinkers");
        title               = config.getString("scoreboard.title");
    }

    private String getEntryPlaceHolderFromLineNumber(int lineNumber) {
        ArrayList<ChatColor> chatColorList = new ArrayList<>(Arrays.asList(ChatColor.values()));
        if (lineNumber > chatColorList.size()*chatColorList.size()) {
            return null;
        }
        ChatColor color1 = chatColorList.get(lineNumber / chatColorList.size());
        ChatColor color2 = chatColorList.get(lineNumber % chatColorList.size());
        return (color1.toString() + "" + color2.toString() + "" + ChatColor.WHITE);
    }

    private void addNewLine(Scoreboard board, Objective obj, int lineNumber,String entryID, String entryValue) {
        Team newEntry = board.registerNewTeam(entryID);
        String entryPlaceHolder = getEntryPlaceHolderFromLineNumber(lineNumber);
        newEntry.addEntry(entryPlaceHolder);
        newEntry.setPrefix(entryValue);
        obj.getScore(entryPlaceHolder).setScore(lineNumber);
    }

    private void updateEntry(Scoreboard board, String entryID, String newEntryValue) {
        board.getTeam(entryID).setPrefix(newEntryValue);
    }

    public void initializePlayerScoreboard(Player player) {
        if (!isScoreboardOn) {
            return;
        }
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = board.registerNewObjective("DrunkMinecraft", "dummy", title);
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        int lineNumber = 0;
        if (showTopDrinkers) {
            for (int i=numberOfTopDrinkers; i>=1; i--) {
                addNewLine(board, obj, lineNumber, "drinkLeader"+String.valueOf(i), ChatColor.GRAY + String.valueOf(i) + ". ");
                lineNumber++;
            }
            addNewLine(board, obj, lineNumber, "leaderboard", ChatColor.GOLD + "Biggest Drinkers:");
            lineNumber++;
            addNewLine(board,obj,lineNumber,"blank_space_1"," ");
            lineNumber++;
        }
        if (showDrinksToGive) {
            addNewLine(board,obj,lineNumber,"drinks_to_give",ChatColor.GOLD + "Drinks To Give: " + ChatColor.GRAY + "0");
            lineNumber++;
        }
        if (showDrinkTotal) {
            addNewLine(board,obj,lineNumber,"drinks_taken",ChatColor.GOLD + "Drinks Taken: " + ChatColor.GRAY + "0");
            lineNumber++;
        }

        addNewLine(board,obj,lineNumber,"blank_space_2"," ");


        player.setScoreboard(board);
        updatePlayerScoreboard(player);
    }

    public void updatePlayerScoreboard(Player player) {
        if (!isScoreboardOn || !player.isOnline()) {
            return;
        }
        Scoreboard board = player.getScoreboard();
        if (board == null) {
            initializePlayerScoreboard(player);
            updatePlayerScoreboard(player);
            return;
        }

        PlayerProfileStorage profileStorage = new PlayerProfileStorage();
        PlayerDrinkProfile playerProfile = profileStorage.getPlayerDrinkProfile(player);
        int lineNumber = 0;
        if (showTopDrinkers) {
            ArrayList<PlayerDrinkProfile> topDrinkers = profileStorage.getTopDrinkers(numberOfTopDrinkers);
            for (int i=numberOfTopDrinkers; i>=1; i--) {
                if ((i-1) < topDrinkers.size()) {
                    updateEntry(board, "drinkLeader" + String.valueOf(i),
                            (ChatColor.GRAY + String.valueOf(i) + ". " +
                                    ChatColor.GOLD + topDrinkers.get(i-1).getPlayerName() +
                                    ChatColor.GRAY + " - " +
                                    ChatColor.BLUE + topDrinkers.get(i-1).getDrinksTaken()));
                } else {
                    updateEntry(board, "drinkLeader" + String.valueOf(i), ChatColor.GRAY + String.valueOf(i) + ". ");
                }
                lineNumber++;
            }
            updateEntry(board, "leaderboard", ChatColor.GOLD + "Biggest Drinkers:");
            lineNumber++;
            updateEntry(board,"blank_space_1"," ");
            lineNumber++;
        }
        if (showDrinksToGive) {
            updateEntry(board,"drinks_to_give",ChatColor.GOLD + "Drinks To Give: " + ChatColor.GRAY + playerProfile.getDrinksToGive());
            lineNumber++;
        }
        if (showDrinkTotal) {
            updateEntry(board,"drinks_taken",ChatColor.GOLD + "Drinks Taken: " + ChatColor.GRAY + playerProfile.getDrinksTaken());
            lineNumber++;
        }
        updateEntry(board,"blank_space_2"," ");
    }

    public void updateAllScoreboards() {
        PlayerProfileStorage profileStorage = new PlayerProfileStorage();
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (profileStorage.doesPlayerHaveProfile(p)) {
                updatePlayerScoreboard(p);
            }
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    void onPlayerJoin(PlayerJoinEvent e) {
        initializePlayerScoreboard(e.getPlayer());

    }


}
