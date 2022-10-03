package gg.fullwin.betterclear.stats;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public final class StatsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = args.length == 0 ? (Player) sender : Bukkit.getPlayer(args[0]);
        if (player == null) {
            sender.sendMessage("Unknown player " + args[0] + ".");
            return true;
        }

        String playername = String.valueOf(player = args.length == 0 ? (Player) sender : Bukkit.getPlayer(args[0]));

        player.sendMessage(" ");
        player.sendMessage(ChatColor.GOLD.toString() + ChatColor.BOLD + playername);
        player.sendMessage("Kills: ");
        player.sendMessage("Deaths: ");
        player.sendMessage("KDR: ");
        player.sendMessage("Killstreak: ");
        player.sendMessage("Max Streak: ");
        player.sendMessage(" ");
        return true;
    }
}
