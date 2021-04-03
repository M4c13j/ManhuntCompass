package me.mcuper.compass;

import me.mcuper.compass.commendy.*;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import me.mcuper.compass.events.eventHandler;

import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import java.util.logging.Logger;

public class Main extends JavaPlugin {
    public Logger loger = getLogger();

    public Set<UUID> hunters = new TreeSet<UUID>();
    public Set<UUID> runners = new TreeSet<UUID>();

    public boolean gameState = false;
    public boolean nether = false;
    public boolean auto_detect_wins = false;
    @Override
    public void onEnable() {
        // commandy
        Commands commands = new Commands();
        getCommand("test").setExecutor( commands );
        getCommand("hunter").setExecutor( new Hunter() );
        getCommand("runner").setExecutor( new Runner() );
        getCommand("manhunt").setExecutor( new Manhunt() );
        getCommand("getCompass").setExecutor( new Compass() );
        getCommand("toggleNether").setExecutor(commands );
        getCommand("debugCompass").setExecutor(commands);

        // Eventy
        getServer().getPluginManager().registerEvents(new eventHandler(),this);

        loger.info("Manhunt Compass has been enabled! Let's play the game");

        nether = getConfig().getBoolean("enable-nether");
        loger.info("Nether compass is set to: "+nether);
    }

    @Override
    public void onDisable() {
        // jak się wyłączam to co zrobię
    }
}
