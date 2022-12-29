package gg.fullwin.betterclear.command.kits;

import gg.fullwin.betterclear.util.CC;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

// Create da Kit
public final class KitCreateCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String kitName, String[] args) {
        if (!sender.hasPermission("fullwin.kit.create")) {
            sender.sendMessage("Unknown command. Type \"/help\" for help.");
            return true;
        }

        if (Kit.getByName(kitName) != null) {
            sender.sendMessage(CC.translate("&cThis kit already exist."));
            return true;
        }

        // create kit etc
        Kit kit = new Kit(kitName);
        kit.save();
        Kit.getKits().add(kit);
        sender.sendMessage(CC.translate("&6Created a new kit &e" + kit.getDisplayName() + "&6."));
        return true;
    }
}
