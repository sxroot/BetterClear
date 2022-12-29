package gg.fullwin.betterclear.misc;

import gg.fullwin.betterclear.BetterClear;
import gg.fullwin.betterclear.util.CC;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

public final class BuildCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("fullwin.build")) {
            sender.sendMessage("Unknown command. Type \"/help\" for help.");
            return true;
        }
        Player player = args.length == 0 ? (Player) sender : Bukkit.getPlayer(args[0]);
        if (player == null) {
            sender.sendMessage("Unknown player " + args[0] + ".");
            return true;
        }

        if (player.hasMetadata("ibebuildinghere")) {
            player.sendMessage(CC.translate("&cYou are no longer in build mode."));
            player.removeMetadata("ibebuildinghere", BetterClear.getInstance());
            return true;
        }

        if (!player.hasMetadata("ibebuildinghere")) {
            player.sendMessage(CC.translate("&aYou are now in build mode."));
            player.setMetadata("ibebuildinghere", new FixedMetadataValue(BetterClear.getInstance(), "true"));
            return true;
        }

        return true;
    }
}
