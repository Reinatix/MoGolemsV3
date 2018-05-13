package me.reinatix.MoGolemsV3;

import me.reinatix.MoGolemsV3.commands.SpawnerCommand;
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

    }

    public static MoGolems getInstance() {
        return instance;
    }
}
