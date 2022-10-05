package gg.fullwin.betterclear.stats;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public record StatsCommand(@NotNull StatsRegistry statsRegistry) implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = args.length == 0 ? (Player) sender : Bukkit.getPlayer(args[0]);
        if (player == null) {
            sender.sendMessage("Unknown player " + args[0] + ".");
            return true;
        }

        statsRegistry.findByUid(player.getUniqueId()).ifPresentOrElse(stats -> sender.sendMessage("",
                        ChatColor.GOLD.toString() + ChatColor.BOLD + player.getName() + "'s stats",
                        "Kills: " + ChatColor.YELLOW + stats.kills(),
                        "Deaths: " + ChatColor.YELLOW + stats.deaths(),
                        "KDR: " + ChatColor.YELLOW + stats.kdr(),
                        "Killstreak: " + ChatColor.YELLOW + stats.killstreak(),
                        "Maxstreak: " + ChatColor.YELLOW + stats.maxstreak(),
                        ""),
                () -> sender.sendMessage("No stats found for " + player.getName() + "."));
        return true;
    }
}
