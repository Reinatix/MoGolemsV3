package me.reinatix.MoGolemsV3.spawners;

import org.bukkit.Material;

/**
 * MoSpawnerType enum.
 *
 * Stores possible types of spawners.
 */
public enum MoSpawnerType {

    GOLD("Gold", Material.GOLD_INGOT),
    DIAMOND("Diamond", Material.DIAMOND),
    EMERALD("Emerald", Material.EMERALD);

    private String name;
    private Material associatedMaterial;

    /**
     * @param name a message-friendly name for the spawner type
     * @param associatedMaterial the associated material of the spawner type
     */
    MoSpawnerType(String name, Material associatedMaterial) {
        this.name = name;
        this.associatedMaterial = associatedMaterial;
    }

    public String getName() {
        return name;
    }

    public Material getAssociatedMaterial() {
        return associatedMaterial;
    }
}
