package me.reinatix.MoGolemsV3.commands;

import me.reinatix.MoGolemsV3.spawners.MoSpawner;
import me.reinatix.MoGolemsV3.spawners.MoSpawnerManager;
import me.reinatix.MoGolemsV3.spawners.MoSpawnerType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnerCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player player = (Player) sender;

        // ***** DATA TEST BECAUSE UNIT TESTING REQUIRES TOO MUCH EFFORT *****
        MoSpawner moSpawner = new MoSpawner(MoSpawnerType.GOLD, player.getLocation());
        MoSpawnerManager.addMoSpawner(moSpawner);
        sender.sendMessage("Assert success? " + MoSpawnerManager.moSpawnerExistsAt(player.getLocation()));
        MoSpawnerManager.saveToDiskAsync();
        for (MoSpawner ms : MoSpawnerManager.getMoSpawners()) {
            sender.sendMessage("MoSpawner exists at: " + ms.getLocation().toString() + " of type " + ms.getMoSpawnerType().getName());
        }
        // ***** DATA TEST BECAUSE UNIT TESTING REQUIRES TOO MUCH EFFORT *****

        return false;
    }
}
