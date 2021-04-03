package me.mcuper.compass.commendy;

import net.minecraft.server.v1_16_R3.SoundEffect;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import me.mcuper.compass.Main;


import java.util.UUID;

public class Manhunt implements CommandExecutor {
    // constructor
    private final Main plugin = Main.getPlugin( Main.class );

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        /*
          /getCompass
         */
        if( cmd.getName().equalsIgnoreCase("manhunt")) {
            if(args.length != 1 ) return false;

            if( args[0].equalsIgnoreCase("start") ) {
                Compass compass = new Compass();

                if( plugin.hunters.size()==0 || plugin.runners.size()==0) {
                    Bukkit.broadcastMessage(ChatColor.RED+"HUTNERS and RUNNERS must have at least 1 player.");
                    return true;
                } else {
                    plugin.gameState = true;
                    Sound sound = Sound.ENTITY_GHAST_WARN;
                    for (UUID ids : plugin.hunters) {
                        Player pl = plugin.getServer().getPlayer(ids).getPlayer();
                        compass.giveCompass(pl);

                        pl.playSound(pl.getLocation(),sound,2f,1f);
                        pl.sendTitle(ChatColor.RED +"Manhunt has just begun!",ChatColor.GREEN +"You're a hunter.",70,10,10);
                    }
                    for( UUID ids : plugin.runners ) {
                        Player pl = plugin.getServer().getPlayer(ids).getPlayer();

                        pl.playSound(pl.getLocation(),sound,2f,1f);
                        pl.sendTitle(ChatColor.RED +"Manhunt has just begun!",ChatColor.BLUE +"You're a runner.",70,10,10);
                    }
                    Bukkit.broadcastMessage(ChatColor.BOLD+""+ChatColor.RED+"Manhunt has just begun!!!");
                }

            } else if ( args[0].equalsIgnoreCase("stop")) {
                plugin.gameState = false;
                Bukkit.broadcastMessage(ChatColor.BOLD+""+ChatColor.BLUE+"Manhunt has been stopped!");
            }
        }
        return true;
    }
}
