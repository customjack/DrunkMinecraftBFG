package me.carlton.drunkminecraft.commands;

import me.carlton.drunkminecraft.DrunkMinecraft;
import me.carlton.drunkminecraft.dataholder.DrinkAction;
import me.carlton.drunkminecraft.drinktracking.DrinkScoreboard;
import me.carlton.drunkminecraft.drinktracking.PlayerDrinkProfile;
import me.carlton.drunkminecraft.drinktracking.PlayerProfileStorage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Random;

public class DmCommands implements CommandExecutor {
    Plugin plugin = DrunkMinecraft.getPlugin(DrunkMinecraft.class);

    private void sendPlayerHelpMessage(Player p) {
        p.sendMessage(ChatColor.RED + "------| " + ChatColor.GREEN + ChatColor.BOLD.toString() + " Help" +
                ChatColor.RESET + ChatColor.RED.toString() + " |-----");
        p.sendMessage(ChatColor.GOLD + "/dm" + ChatColor.RESET + ChatColor.GRAY.toString() + " - " +
                ChatColor.RESET + ChatColor.WHITE + "Opens Drunk Minecraft help menu");
        p.sendMessage(ChatColor.GOLD + "/dm help" + ChatColor.RESET + ChatColor.GRAY.toString() + " - " +
                ChatColor.RESET + ChatColor.WHITE + "Opens Drunk Minecraft help menu");
        p.sendMessage(ChatColor.GOLD + "/dm assignDrink [playerName] [amount]" + ChatColor.RESET + ChatColor.GRAY.toString() + " - " +
                ChatColor.RESET + ChatColor.WHITE + "Uses drinks to give to make a prompt a player to drink");
        p.sendMessage(ChatColor.GOLD + "/dm forceAssignDrink [playerName] [amount]" + ChatColor.RESET + ChatColor.GRAY.toString() + " - " +
                ChatColor.RESET + ChatColor.WHITE + "Uses drinks to give to make a prompt a player to drink");
        p.sendMessage(ChatColor.GOLD + "/dm setDrinks [playerName] [amount]" + ChatColor.RESET + ChatColor.GRAY.toString() + " - " +
                ChatColor.RESET + ChatColor.WHITE + "Sets a player's drink total");
        p.sendMessage(ChatColor.GOLD + "/dm setDrinksToGive [playerName] [amount]" + ChatColor.RESET + ChatColor.GRAY.toString() + " - " +
                ChatColor.RESET + ChatColor.WHITE + "Sets a player's drinks to give total");
        p.sendMessage(ChatColor.GOLD + "/dm setDrinkMultiplier [playerName] [multiplier] [(optional) duration]" + ChatColor.RESET + ChatColor.GRAY.toString() + " - " +
                ChatColor.RESET + ChatColor.WHITE + "Sets a player's drink multiplier");
        p.sendMessage(ChatColor.GOLD + "/dm multiplierLottery [multiplier] [(optional) duration]" + ChatColor.RESET + ChatColor.GRAY.toString() + " - " +
                ChatColor.RESET + ChatColor.WHITE + "Sets a player's drink multiplier");
    }

    private int getArgumentAsInteger(String arg) {
        int integerValueOfArg = 0;
        try {
            integerValueOfArg = Integer.valueOf(arg);
            return integerValueOfArg;
        }
        catch (NumberFormatException e) {
            return Integer.MAX_VALUE; //This is a cheap trick; when we return max value, that's our way of saying "could not convert" without throwing error
        }
    }

    private double getArgumentAsDouble(String arg) {
        double doubleValueOfArg = 0;
        try {
            doubleValueOfArg = Double.valueOf(arg);
            return doubleValueOfArg;
        }
        catch (NumberFormatException e) {
            return Double.MAX_VALUE; //This is a cheap trick; when we return max value, that's our way of saying "could not convert" without throwing error
        }
    }

    private boolean isTargetOnline(Player target, CommandSender sender) {
        if (target == null) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                p.sendMessage(ChatColor.RED + "Player Offline");
            }
            else {
                sender.sendMessage("Player not online");
            }
            return false;
        }
        return true;
    }

    private void assignPlayerDrink(int drinks, String senderName, Player target) {
        DrinkAction drinkAction = new DrinkAction("being chosen to drink","Take $drinkValue$ $drinks$","ASSIGNED",drinks);
        drinkAction.setReason(senderName + " picked you to drink");
        drinkAction.setBroadcastString(target.getDisplayName() + " has to take $drinkValue$ $drinks$ (courtesy of " + senderName + " :) )");
        drinkAction.makePlayerDrink(target);
    }

    private Player getRandomPlayer() {
        ArrayList<Player> onlinePlayers = new ArrayList<>();
        for (Player player : Bukkit.getOnlinePlayers()) {
            onlinePlayers.add(player);
        }
        return onlinePlayers.get(new Random().nextInt(onlinePlayers.size()));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        //dm help
        if (args.length == 0) {
            CommandHelper commandHelper = new CommandHelper(sender,args,0,"/dm help");
            commandHelper.setPermissionName("help");
            commandHelper.setPlayerOnlyCommand(true);
            if (!commandHelper.isProperCommand()) {
                return true; //Command Restricted or Improper
            }
            Player p = (Player) sender;
            sendPlayerHelpMessage(p);
            return true;
        }

        if (args[0].equalsIgnoreCase("help")) {
            CommandHelper commandHelper = new CommandHelper(sender,args,1,"/dm help");
            commandHelper.setPermissionName("help");
            commandHelper.setPlayerOnlyCommand(true);
            if (!commandHelper.isProperCommand()) {
                return true; //Command Restricted or Improper
            }
            Player p = (Player) sender;
            sendPlayerHelpMessage(p);
            return true;
        }

        if (args[0].equalsIgnoreCase("assignDrink")) {
            CommandHelper commandHelper = new CommandHelper(sender,args,2,3,"/dm assignDrink [player] [amount]");
            commandHelper.setPermissionName("help");
            commandHelper.setPlayerOnlyCommand(true);
            if (!commandHelper.isProperCommand()) {
                return true; //Command Restricted or Improper
            }
            String targetName = args[1];
            Player player = (Player) sender;
            Player target = plugin.getServer().getPlayer(targetName);
            if (!isTargetOnline(target,sender)) {
                return true; //Player not online
            }
            int drinksAssigned = 1;
            if (args.length == 3) {
                drinksAssigned = getArgumentAsInteger(args[2]);
            }
            PlayerDrinkProfile playerProfile = new PlayerProfileStorage().getPlayerDrinkProfile(player);
            if (playerProfile.getDrinksToGive() >= drinksAssigned) {
                playerProfile.setDrinksToGive(playerProfile.getDrinksToGive()-drinksAssigned);
                assignPlayerDrink(drinksAssigned, player.getDisplayName(), target);
            }


            return true;
        }

        if (args[0].equalsIgnoreCase("forceAssignDrink")) {
            CommandHelper commandHelper = new CommandHelper(sender,args,2,3,"/dm forceAssignDrink [player] [amount]");
            commandHelper.setPermissionName("assignDrinkOP");
            if (!commandHelper.isProperCommand()) {
                return true; //Command Restricted or Improper
            }
            String targetName = args[1];
            Player target = plugin.getServer().getPlayer(targetName);
            if (!isTargetOnline(target,sender)) {
                return true; //Player not online
            }
            int drinksAssigned = 1;
            if (args.length == 3) {
                drinksAssigned = getArgumentAsInteger(args[2]);
                if (drinksAssigned == Integer.MAX_VALUE) {
                    commandHelper.sendImproperArgumentsMessage();
                    return true;
                }
            }
            assignPlayerDrink(drinksAssigned, sender.getName(), target);

            return true;
        }

        if (args[0].equalsIgnoreCase("setDrinks")) {
            CommandHelper commandHelper = new CommandHelper(sender,args,3,"/dm setDrinks [player] [drinks]");
            commandHelper.setPermissionName("setDrinks");
            if (!commandHelper.isProperCommand()) {
                return true; //Command Restricted or Improper
            }
            String targetName = args[1];
            int newDrinks = getArgumentAsInteger(args[2]);
            if (newDrinks == Integer.MAX_VALUE) {
                commandHelper.sendImproperArgumentsMessage();
                return true;
            }
            Player target = plugin.getServer().getPlayer(targetName);
            if (!isTargetOnline(target,sender)) {
                return true; //Player not online
            }
            PlayerDrinkProfile playerProfile = new PlayerProfileStorage().getPlayerDrinkProfile(target);
            playerProfile.setDrinksTaken(newDrinks);
            new DrinkScoreboard().updateAllScoreboards();
            return true;
        }

        if (args[0].equalsIgnoreCase("setDrinksToGive")) {
            CommandHelper commandHelper = new CommandHelper(sender,args,3,"/dm setDrinksToGive [player] [drinks]");
            commandHelper.setPermissionName("setDrinksToGive");
            if (!commandHelper.isProperCommand()) {
                return true; //Command Restricted or Improper
            }
            String targetName = args[1];
            int newDrinksToGive = getArgumentAsInteger(args[2]);
            if (newDrinksToGive == Integer.MAX_VALUE) {
                commandHelper.sendImproperArgumentsMessage();
                return true;
            }
            Player target = plugin.getServer().getPlayer(targetName);
            if (!isTargetOnline(target,sender)) {
                return true; //Player not online
            }
            PlayerDrinkProfile playerProfile = new PlayerProfileStorage().getPlayerDrinkProfile(target);
            playerProfile.setDrinksToGive(newDrinksToGive);
            new DrinkScoreboard().updateAllScoreboards();

            return true;
        }

        if (args[0].equalsIgnoreCase("setDrinkMultiplier")) {
            CommandHelper commandHelper = new CommandHelper(sender,args,3,4,"/dm setMultiplier [player] [mulitplier] [(optional) duration");
            commandHelper.setPermissionName("setDrinksToGive");
            if (!commandHelper.isProperCommand()) {
                return true; //Command Restricted or Improper
            }
            String targetName = args[1];
            double multiplier = getArgumentAsDouble(args[2]);
            if (multiplier == Double.MAX_VALUE) {
                commandHelper.sendImproperArgumentsMessage();
                return true;
            }
            Player target = plugin.getServer().getPlayer(targetName);
            if (!isTargetOnline(target,sender)) {
                return true; //Player not online
            }
            String broadcastString = target.getDisplayName() + " now has a " + multiplier + "x drink multiplier";
            int duration = -1;
            if (args.length == 4) {
                duration = getArgumentAsInteger(args[3]);
                broadcastString = broadcastString + " for " + duration + " seconds";
                if (duration == Integer.MAX_VALUE) {
                    commandHelper.sendImproperArgumentsMessage();
                    return true;
                }
            }
            PlayerDrinkProfile targetProfile = new PlayerProfileStorage().getPlayerDrinkProfile(target);
            targetProfile.startMultiplierTimer(multiplier,duration);
            Bukkit.broadcastMessage(broadcastString);
            return true;
        }

        if (args[0].equalsIgnoreCase("multiplierLottery")) {
            CommandHelper commandHelper = new CommandHelper(sender,args,2,3,"/dm multiplierLottery [mulitplier] [(optional) duration");
            commandHelper.setPermissionName("multiplierLottery");
            if (!commandHelper.isProperCommand()) {
                return true; //Command Restricted or Improper
            }
            double multiplier = getArgumentAsDouble(args[1]);
            if (multiplier == Double.MAX_VALUE) {
                commandHelper.sendImproperArgumentsMessage();
                return true;
            }
            Player target = getRandomPlayer();
            String broadcastString = target.getDisplayName() + " now has a " + multiplier + "x drink multiplier";
            int duration = -1;
            if (args.length == 3) {
                duration = getArgumentAsInteger(args[2]);
                broadcastString = broadcastString + " for " + duration + " seconds";
                if (duration == Integer.MAX_VALUE) {
                    commandHelper.sendImproperArgumentsMessage();
                    return true;
                }
            }
            PlayerDrinkProfile targetProfile = new PlayerProfileStorage().getPlayerDrinkProfile(target);
            targetProfile.startMultiplierTimer(multiplier,duration);
            Bukkit.broadcastMessage(broadcastString);
            return true;
        }
        else {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                p.sendMessage(ChatColor.RED + "Unknown Command");
            }
            else {
                sender.sendMessage("Unknown command");
            }
        }

        return false;
    }
}
