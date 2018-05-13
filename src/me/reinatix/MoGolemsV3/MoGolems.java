package me.reinatix.MoGolemsV3;

import me.reinatix.MoGolemsV3.commands.SpawnerCommand;
import me.reinatix.MoGolemsV3.events.BlockBreak;
import me.reinatix.MoGolemsV3.events.BlockPlace;
import me.reinatix.MoGolemsV3.events.EntityDeath;
import me.reinatix.MoGolemsV3.events.EntitySpawn;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class MoGolems extends JavaPlugin {

    private static MoGolems instance;

    @Override
    public void onEnable() {
        instance = this;
        registerCommands();
        registerEvents();
    }

    @Override
    public void onDisable() {

    }

    private void registerCommands() {
        getCommand("spawner").setExecutor(new SpawnerCommand());
    }

    private void registerEvents() {
        Bukkit.getPluginManager().registerEvents(new BlockBreak(), this);
        Bukkit.getPluginManager().registerEvents(new BlockPlace(), this);
        Bukkit.getPluginManager().registerEvents(new EntityDeath(), this);
        Bukkit.getPluginManager().registerEvents(new EntitySpawn(), this);
    }

    public static MoGolems getInstance() {
        return instance;
    }
}
