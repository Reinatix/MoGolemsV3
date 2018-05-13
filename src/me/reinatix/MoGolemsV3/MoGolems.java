package me.reinatix.MoGolemsV3;

import me.reinatix.MoGolemsV3.commands.SpawnerCommand;
import me.reinatix.MoGolemsV3.events.BlockBreak;
import me.reinatix.MoGolemsV3.events.BlockPlace;
import me.reinatix.MoGolemsV3.events.EntityDeath;
import me.reinatix.MoGolemsV3.events.EntitySpawn;
import me.reinatix.MoGolemsV3.spawners.MoSpawnerManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class MoGolems extends JavaPlugin {

    private static MoGolems instance;
    private static MoSpawnerManager moSpawnerManager;

    @Override
    public void onEnable() {
        instance = this;

        // instantiate a new MoSpawnerManager
        moSpawnerManager = new MoSpawnerManager();

        if (!generateFiles()) {
            this.getLogger().log(Level.SEVERE,"An error occurred generating data files! See error above.");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }
        registerCommands();
        registerEvents();

        // this makes sure all worlds are loaded & server full started up before executing the following
        new BukkitRunnable() {
            @Override
            public void run() {
                MoSpawnerManager.reloadFromDisk();
            }
        }.runTask(this);
    }

    @Override
    public void onDisable() {
        MoSpawnerManager.saveToDisk();
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

    /**
     * Generate data files.
     *
     * @return if successful
     */
    private boolean generateFiles() {
        saveDefaultConfig();

        File data = new File(this.getDataFolder() + File.separator + "data.yml");
        if (!data.exists()) {
            try {
                return data.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    /**
     * Gets main JavaPlugin class.
     *
     * @return the JavaPlugin
     */
    public static MoGolems getInstance() {
        return instance;
    }

    /**
     * Gets the MoSpawnerManager.
     *
     * @return the MoSpawnerManager.
     * @deprecated MoSpawnerManager is static.
     */
    @Deprecated
    public static MoSpawnerManager getMoSpawnerManager() {
        return moSpawnerManager;
    }
}
