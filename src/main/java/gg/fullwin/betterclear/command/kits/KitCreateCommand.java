package gg.fullwin.betterclear.command.kits;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

// Create da Kit
public final class KitCreateCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String kitName, String[] args) {
        if (!sender.hasPermission("fullwin.create")) {
            sender.sendMessage("Unknown command. Type \"/help\" for help.");
            return true;
        }

        if (Kit.getByName(kitName) != null) {
            sender.sendMessage(ChatColor.RED + "This kit already exists.");
            return true;
        }

        // create kit etc
        Kit kit = new Kit(kitName);

        return true;
    }
}
