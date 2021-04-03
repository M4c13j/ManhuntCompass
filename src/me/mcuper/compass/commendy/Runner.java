package me.mcuper.compass.commendy;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import me.mcuper.compass.Main;

import java.util.UUID;

public class Runner implements CommandExecutor {
    // constructor
    private final Main plugin = Main.getPlugin( Main.class );

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args ) {
        /*
          /runner - make a player executing the command a runner
          /runner set <player> - make <player> a runner
          /runner remove - removes all runners
          /runner list - returns all runners
         */
        if( cmd.getName().equalsIgnoreCase("runner") ) {
            if( !(sender instanceof Player) && args.length == 0 ) {
                plugin.loger.info("Only player can use this command without arguments!");
                return true;
            }
            Player p = (Player) sender;
            UUID id = p.getUniqueId();

            if( args.length == 0 ) {
                if( !plugin.hunters.isEmpty() && plugin.hunters.contains(id) ) plugin.hunters.remove(id);
                plugin.runners.add(id);
                p.sendMessage(ChatColor.AQUA+"You have been succesfully added to RUNNERS");
            } else if( args[0].equalsIgnoreCase("add") ) {
                if( args.length < 2 ) {
                    p.sendMessage(ChatColor.RED+"You must specify a player nickname!");
                    return true;
                }
                UUID newRunner = plugin.getServer().getPlayer(args[1]).getUniqueId();
                if( !plugin.hunters.isEmpty() && plugin.hunters.contains(newRunner) ) plugin.hunters.remove(newRunner);
                plugin.runners.add(newRunner);
                p.sendMessage(ChatColor.AQUA+"Succesfully added "+args[1]+" to RUNNERS.");
            } else if( args[0].equalsIgnoreCase("remove") ) {
                if( args.length < 2 ) {
                    p.sendMessage(ChatColor.RED+"You must specify a player nickname!");
                    return true;
                }
                UUID newRunner = plugin.getServer().getPlayer(args[1]).getUniqueId();
                plugin.runners.remove(newRunner);
                p.sendMessage(ChatColor.AQUA+"Succesfully removed "+args[1]+" from RUNNERS.");
            } else if( args[0].equalsIgnoreCase("clear") ) {
                plugin.runners.clear();
                p.sendMessage(ChatColor.RED+"All hunters have been removed!");
            } else if( args[0].equalsIgnoreCase("list")) {
                String text = "";
                if( !plugin.runners.isEmpty() ) {
                    for (UUID ids : plugin.runners) {
                        text += plugin.getServer().getPlayer(ids).getName() + ",";
                    }
                }
                text = text.substring(0, text.length() - 1);
                p.sendMessage(ChatColor.DARK_PURPLE + "RUNNERS: "+ChatColor.GREEN + text);
            }
        }
        return true;
    }
}
