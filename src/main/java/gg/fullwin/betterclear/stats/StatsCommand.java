package gg.fullwin.betterclear.stats;

import gg.fullwin.betterclear.util.CC;
import gg.fullwin.betterclear.util.UnicodeUtil;
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

        statsRegistry.findByUid(player.getUniqueId()).ifPresentOrElse(stats -> sender.sendMessage(
                        "",
                        CC.FULLWIN + CC.BOLD + UnicodeUtil.parse(player.getName() + "'s stats:"),
                        UnicodeUtil.parse(" Kills: ") + CC.PISS + stats.kills(),
                        UnicodeUtil.parse(" Deaths: ") + CC.PISS + stats.deaths(),
                        UnicodeUtil.parse(" KDR: ") + CC.PISS + stats.kdr(),
                        UnicodeUtil.parse(" Killstreak: ") + CC.PISS + stats.killstreak(),
                        UnicodeUtil.parse(" Maxstreak: ") + CC.PISS + stats.maxstreak(),
                        ""),
                () -> sender.sendMessage("No stats found for " + player.getName() + "."));
        return true;
    }
}
