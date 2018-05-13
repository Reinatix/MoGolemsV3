package me.reinatix.MoGolemsV3;

import me.reinatix.MoGolemsV3.commands.SpawnerCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static Main instance;

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

    }
}
