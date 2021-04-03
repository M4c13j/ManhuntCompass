package me.mcuper.compass.commendy;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import me.mcuper.compass.Main;

import java.util.UUID;

public class Hunter implements CommandExecutor {
    // constructor
    private final Main plugin = Main.getPlugin( Main.class );

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args ) {
        /*
          /hunter - add a player executing that command to hunters
          /hunter add <player> - add a <player> to hunters
          /hunter remove <player> - remove a <player> from hunters
          /hunter clear - remove all players from hunters
          /hunter list - returns nicks of hunters
         */
        if( cmd.getName().equalsIgnoreCase("hunter") ) {
            if( !(sender instanceof Player) && args.length == 0 ) {
                plugin.loger.info("Only player can use this command without arguments!");
                return true;
            }
            Player p = (Player) sender;
            UUID id = p.getUniqueId();

            if( args.length == 0 ) {
                if( !plugin.runners.isEmpty() && plugin.runners.contains(id) ) plugin.runners.remove(id);
                plugin.hunters.add(id);
                p.sendMessage(ChatColor.AQUA+"You have been succesfully added to HUNTERS");
            } else if( args[0].equalsIgnoreCase("add") ) {
                if( args.length < 2 ) {
                    p.sendMessage(ChatColor.RED+"You must specify a player nickname!");
                    return true;
                }
                UUID newHunter = plugin.getServer().getPlayer(args[1]).getUniqueId();
                if( !plugin.runners.isEmpty() && plugin.runners.contains(newHunter) ) plugin.runners.remove(newHunter);
                if( newHunter!=null) plugin.hunters.add(newHunter);
                p.sendMessage(ChatColor.AQUA+"Succesfully added "+args[1]+" to HUNTERS.");
            } else if( args[0].equalsIgnoreCase("remove") ) {
                if( args.length < 2 ) {
                    p.sendMessage(ChatColor.RED+"You must specify a player nickname!");
                    return true;
                }
                UUID newHunter = plugin.getServer().getPlayer(args[1]).getUniqueId();
                plugin.hunters.remove(newHunter);
                p.sendMessage(ChatColor.AQUA+"Succesfully removed "+args[1]+" from HUNTERS.");
            } else if( args[0].equalsIgnoreCase("clear") ) {
                plugin.hunters.clear();
                p.sendMessage(ChatColor.RED+"All hunters have been removed!");
            } else if( args[0].equalsIgnoreCase("list")) {
                String text = "";
                if( !plugin.hunters.isEmpty() ) {
                    for (UUID ids : plugin.hunters) {
                        text += plugin.getServer().getPlayer(ids).getName() + ",";
                    }
                }
                text = text.substring(0, text.length() - 1);
                p.sendMessage(ChatColor.AQUA + "HUNTERS: "+ChatColor.GREEN+ text);
            }
        }

        return true;
    }
}
