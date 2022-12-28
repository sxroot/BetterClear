package gg.fullwin.betterclear.command.kits;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

// List Kit Commands
public final class KitCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("fullwin.kit")) {
            sender.sendMessage("Unknown command. Type \"/help\" for help.");
            return true;
        }

        sender.sendMessage(ChatColor.GOLD.toString() + ChatColor.BOLD + "Kit Commands:",
                " /kits " + ChatColor.YELLOW + "Display all avaliable kits",
                " /kit create " + ChatColor.YELLOW + "Create a kit",
                " /kit delete " + ChatColor.YELLOW + "Delete a kit",
                " /kit edit " + ChatColor.YELLOW + "Edit a kit",
                " /kit effects " + ChatColor.YELLOW + "Add effects for the kit",
                ""
        );
        return true;
    }
}
