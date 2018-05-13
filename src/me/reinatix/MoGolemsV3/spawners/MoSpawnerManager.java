package me.reinatix.MoGolemsV3.spawners;

import me.reinatix.MoGolemsV3.MoGolems;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.regex.Pattern;

/**
 * MoSpawnerManager class.
 *
 * Stores all MoSpawners.
 */
public class MoSpawnerManager {

    // hashmap containing all the spawners, stored with location as key for faster access
    private static HashMap<Location, MoSpawner> spawnerHashMap = new HashMap<>();

    /**
     * Registers a MoSpawner.
     *
     * @param moSpawner the MoSpawner to register.
     */
    public static void addMoSpawner(MoSpawner moSpawner) {
        spawnerHashMap.put(moSpawner.getLocation(), moSpawner);
    }

    /**
     * Check if a MoSpawner exists at given location.
     *
     * @param location the location to check
     * @return true if exists, false if not
     */
    public static boolean moSpawnerExistsAt(Location location) {
        return spawnerHashMap.containsKey(location);
    }

    /**
     * Removes a MoSpawner.
     *
     * @param location the location of which to remove.
     */
    public static void removeMoSpawner(Location location) {
        spawnerHashMap.remove(location);
    }

    /**
     * Removes a MoSpawner.
     *
     * @param moSpawner the spawner of which to remove.
     */
    public static void removeMoSpawner(MoSpawner moSpawner) {
        removeMoSpawner(moSpawner.getLocation());
    }

    /**
     * Get a collection containing all MoSpawners.
     *
     * @return a collection of MoSpawner.
     */
    public static Collection<MoSpawner> getMoSpawners() {
        return spawnerHashMap.values();
    }

    /**
     * Save all MoSpawners to disk. Call the async method if this is happening outside server startup/shutdown.
     */
    public static void saveToDisk() {
        File data = new File(MoGolems.getInstance().getDataFolder() + File.separator + "data.yml");
        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(data);
        String root = "spawners.";

        for (MoSpawner moSpawner : getMoSpawners()) {
            String locationString = moSpawner.getLocation().getWorld().getName() + ", " + moSpawner.getLocation().getBlockX() + ", " + moSpawner.getLocation().getBlockY() + ", " + moSpawner.getLocation().getBlockZ();
            yaml.set(root + locationString + ".type", moSpawner.getMoSpawnerType().toString());
        }

        try {
            yaml.save(data);
        } catch (IOException e) {
            // shit + fan
            e.printStackTrace();
        }
    }

    /**
     * Save all MoSpawners to disk asynchronously. Call the sync method if this is happening during server startup/shutdown.
     */
    public static void saveToDiskAsync() {
        // executes save in async thread
        new BukkitRunnable() {
            @Override
            public void run() {
                saveToDisk();
            }
        }.runTaskAsynchronously(MoGolems.getInstance());
    }

    /**
     * Reload all MoSpawners from disk. This will clear already loaded MoSpawners.
     */
    public static void reloadFromDisk() {
        spawnerHashMap.clear();
        File data = new File(MoGolems.getInstance().getDataFolder() + File.separator + "data.yml");
        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(data);

        if (yaml.contains("spawners")) {
            for (String key : yaml.getConfigurationSection("spawners").getKeys(false)) {
                String[] locParts = key.split(Pattern.quote(", "));
                String worldString = locParts[0];
                String xString = locParts[1];
                String yString = locParts[2];
                String zString = locParts[3];

                World w;
                int x = Integer.parseInt(xString);
                int y = Integer.parseInt(yString);
                int z = Integer.parseInt(zString);
                // if no such world exists anymore...
                if ((w = Bukkit.getWorld(worldString)) == null) {
                    continue;
                }

                Location location = new Location(w, x, y, z);
                MoSpawnerType moSpawnerType = MoSpawnerType.valueOf(yaml.getString("spawners." + key + ".type"));

                addMoSpawner(new MoSpawner(moSpawnerType, location));
            }
        }
    }
}
