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

public class Compass implements CommandExecutor {
    // constructor
    private final Main plugin = Main.getPlugin( Main.class );

    public boolean giveCompass( Player p ) {
        ItemStack item = new ItemStack(Material.COMPASS);
        ItemMeta meta = item.getItemMeta();
        String name = ChatColor.GOLD+"Manhunt Compass";

        meta.addEnchant(Enchantment.DURABILITY, 100, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.setDisplayName(name);
        item.setItemMeta(meta);

        PlayerInventory inventory = p.getInventory();
        inventory.addItem(item);

        p.sendMessage(ChatColor.GREEN+"Succesfully added "+ChatColor.RED+"Manhunt Compass");
        return true;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] strings) {
        /*
          /getCompass
         */

        Player p = (Player) sender;

        if( cmd.getName().equalsIgnoreCase("test")) {
            p.sendMessage(ChatColor.RED + "PLUGIN działa ez");
            // Location testa = new Location(Bukkit.getWorld("world"), 0, 0, 0);
            p.setCompassTarget( p.getLocation() );
            p.sendMessage("Compass is pointing towards your last position ");
        }else if ( cmd.getName().equalsIgnoreCase("getCompass")) {
            if( !plugin.hunters.contains(p.getUniqueId()) ) {
                p.sendMessage(ChatColor.RED+"Only hunter can execute this command!");
                return true;
            }

            giveCompass(p);
        }

        return true;
    }

}
