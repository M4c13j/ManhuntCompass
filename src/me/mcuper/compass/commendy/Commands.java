package me.mcuper.compass.commendy;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import me.mcuper.compass.Main;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

public class Commands implements CommandExecutor {
    // constructor
    private final Main plugin = Main.getPlugin(Main.class);


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] strings) {
        Player p = (Player) sender;
        if( cmd.getName().equalsIgnoreCase("test")) {
            p.sendMessage(ChatColor.RED + "PLUGIN działa ez");

            // Location testa = new Location(Bukkit.getWorld("world"), 0, 0, 0);

            p.setCompassTarget( p.getLocation() );

            p.sendMessage("Compass is pointing towards your last position ");

        } else if ( cmd.getName().equalsIgnoreCase("toggleNether")) {
           plugin.nether = !plugin.nether;
           p.sendMessage(ChatColor.RED+"Now compass is "+(plugin.nether?"":"ŃOT")+" working in the nether!");
        } else if ( cmd.getName().equalsIgnoreCase("debugCompass")) {
            p.setCompassTarget( p.getLocation() );
            p.sendMessage("Compass pointing to your postion.");
        }

        return true;
    }
}
