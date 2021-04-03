package me.mcuper.compass.events;

import me.mcuper.compass.Main;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;

public class eventHandler implements Listener {
     private final Main plugin = Main.getPlugin( Main.class );

    @EventHandler
    public void onPlayerUse(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        ItemStack item = event.getItem();

        if (item != null && item.getType() == Material.COMPASS && plugin.hunters.contains(p.getUniqueId())) {

            Location pos = p.getLocation();
            Location temp;
            if (!plugin.runners.isEmpty()) {
               // p.sendMessage("processing...");
                    /*for (UUID ids : plugin.runners) {
                        temp = plugin.getServer().getPlayer(ids).getLocation();
                        p.sendMessage(plugin.getServer().getPlayer(ids).getName());
                        if (p.getLocation() == pos) {
                            pos = temp;
                        } else {
                            if (p.getLocation().distance(pos) > p.getLocation().distance(temp)) {
                                pos = temp;
                            }
                        }
                    }*/
                temp = plugin.getServer().getPlayer(plugin.runners.stream().findFirst().get()).getLocation();

                p.sendMessage(ChatColor.GREEN + "Now pointing to the closest player!");
                p.setCompassTarget(temp);
            } else {
                p.sendMessage(ChatColor.GREEN + "Compass is pointing towards your last position!");
                p.setCompassTarget(p.getLocation());
            }

        }
    }

    @EventHandler
    public void onDeth(PlayerDeathEvent event) {
        Player p = event.getEntity();

        if(!plugin.gameState) return;
        else if( plugin.runners.contains(p) ) {
            Bukkit.broadcastMessage(ChatColor.RED+"RUNNER HAS DIED!");
        } else if( plugin.runners.contains(p) && plugin.auto_detect_wins ) {
            plugin.gameState = false;

            for (UUID ids : plugin.hunters) {
                Player pl = plugin.getServer().getPlayer(ids).getPlayer();

                pl.playSound(pl.getLocation(),Sound.UI_TOAST_CHALLENGE_COMPLETE ,2f,1f);
                pl.sendTitle(ChatColor.GREEN +"you have WON",ChatColor.GREEN +"Runners have won",70,10,10);
            }
            for( UUID ids : plugin.runners ) {
                Player pl = plugin.getServer().getPlayer(ids).getPlayer();

                pl.playSound(pl.getLocation(),Sound.ENTITY_GHAST_SHOOT,2f,1f);
                pl.sendTitle(ChatColor.RED +"You have lost",ChatColor.BLUE +"Hunters have won",70,10,10);
            }
            Bukkit.broadcastMessage(ChatColor.BOLD+""+ChatColor.RED+"Manhunt has ended!!!");

        }
    }
}