package gg.fullwin.betterclear.command.misc;

import gg.fullwin.betterclear.BetterClear;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class ClearCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("fullwin.clear")) {
            sender.sendMessage("Unknown command. Type \"/help\" for help.");
            return true;
        }
        Player player = args.length == 0 ? (Player) sender : Bukkit.getPlayer(args[0]);
        if (player == null) {
            sender.sendMessage("Unknown player " + args[0] + ".");
            return true;
        }
        player.getInventory().clear();
        player.getActivePotionEffects().forEach(potionEffect -> player.removePotionEffect(potionEffect.getType()));
        player.removeMetadata("blacklivesmatter", JavaPlugin.getPlugin(BetterClear.class));
        player.removeMetadata("alllivesmatter", JavaPlugin.getPlugin(BetterClear.class));
        return true;
    }
}
