package me.reinatix.MoGolemsV3.spawners;

import org.bukkit.Location;

/**
 * MoSpawner class.
 *
 * Holds information on the type of spawner and its location.
 */
public class MoSpawner {

    private MoSpawnerType moSpawnerType;
    private Location location;

    /**
     * Creates a new MoSpawner object which holds a type and location.
     *
     * @param moSpawnerType the type of spawner
     * @param location the location of the spawner
     */
    public MoSpawner(MoSpawnerType moSpawnerType, Location location) {
        this.moSpawnerType = moSpawnerType;
        this.location = location;
    }

    /**
     * @return the type of spawner
     */
    public MoSpawnerType getMoSpawnerType() {
        return moSpawnerType;
    }

    /**
     * @return the location of spawner
     */
    public Location getLocation() {
        return location;
    }
}
