package me.carlton.drunkminecraft.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DmCommands implements CommandExecutor {

    private void sendPlayerHelpMessage(Player p) {
        p.sendMessage(ChatColor.RED + "------| " + ChatColor.GREEN + ChatColor.BOLD.toString() + " Help" +
                ChatColor.RESET + ChatColor.RED.toString() + " |-----");
        p.sendMessage(ChatColor.GOLD + "/dm" + ChatColor.RESET + ChatColor.GRAY.toString() + " - " +
                ChatColor.RESET + ChatColor.WHITE + "Opens Drunk Minecraft help menu");
        p.sendMessage(ChatColor.GOLD + "/dm help" + ChatColor.RESET + ChatColor.GRAY.toString() + " - " +
                ChatColor.RESET + ChatColor.WHITE + "Opens Drunk Minecraft help menu");
        p.sendMessage(ChatColor.GOLD + "/dm assignDrink [playerName] [amount]" + ChatColor.RESET + ChatColor.GRAY.toString() + " - " +
                ChatColor.RESET + ChatColor.WHITE + "Uses drinks to give to make a prompt a player to drink");
        p.sendMessage(ChatColor.GOLD + "/frpg setDrinks [playerName] [amount]" + ChatColor.RESET + ChatColor.GRAY.toString() + " - " +
                ChatColor.RESET + ChatColor.WHITE + "Sets a player's drink total");
        p.sendMessage(ChatColor.GOLD + "/frpg setDrinksToGive [playerName] [amount]" + ChatColor.RESET + ChatColor.GRAY.toString() + " - " +
                ChatColor.RESET + ChatColor.WHITE + "Sets a player's drinks to give total");
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
            CommandHelper commandHelper = new CommandHelper(sender,args,2,3,"/dm help");
            commandHelper.setPermissionName("assignDrink");
            commandHelper.setPlayerOnlyCommand(true);
            if (!commandHelper.isProperCommand()) {
                return true; //Command Restricted or Improper
            }
            Player p = (Player) sender;
            sendPlayerHelpMessage(p);
            return true;
        }

        return false;
    }
}
