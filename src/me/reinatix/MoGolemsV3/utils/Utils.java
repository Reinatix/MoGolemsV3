package me.reinatix.MoGolemsV3.utils;

import me.reinatix.MoGolemsV3.MoGolems;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static String colorize(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public static void send(Player player, String msg) {
        player.sendMessage(colorize(msg));
    }

    public void giveSpawner(Player player, String tier, int amount) {
        FileConfiguration config = MoGolems.getInstance().getConfig();
        ItemStack spawner = new ItemStack(Material.matchMaterial(config.getString("spawners." + tier + ".type")));
        ItemMeta meta = spawner.getItemMeta();
        meta.setDisplayName(config.getString("spawners." + tier + ".name"));
        List<String> oldLore = config.getStringList("spawners." + tier + ".lore");
        List<String> newLore = new ArrayList<>();
        for (String s : oldLore) {
            Utils.colorize(s);
            newLore.add(s);
        }
        meta.setLore(newLore);
        spawner.setAmount(amount);
        spawner.setItemMeta(meta);

        player.getInventory().addItem(spawner);
    }

}
