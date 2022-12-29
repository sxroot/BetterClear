package gg.fullwin.betterclear.kits.commands;

import gg.fullwin.betterclear.kits.Kit;
import gg.fullwin.betterclear.util.CC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

// Delete da Kit
public final class KitDeleteCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String kitName, String[] args) {
        if (!sender.hasPermission("fullwin.kit.delete")) {
            sender.sendMessage("Unknown command. Type \"/help\" for help.");
            return true;
        }

        if (kitName == null) {
            sender.sendMessage(CC.translate("&cYou have to provide a name."));
            return false;
        }

        final Kit kit = Kit.getByName(kitName);
        if (kit != null) {
            kit.delete();
            Kit.getKits().forEach(Kit::save);
            sender.sendMessage(CC.translate("&6Removed the kit &e" + kit.getName() + "&6."));
            return true;
        }

        return true;
    }
}
