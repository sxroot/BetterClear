package gg.fullwin.betterclear.command.kits;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

// List of all da Kits
public final class KitsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("fullwin.kit")) {
            sender.sendMessage("Unknown command. Type \"/help\" for help.");
            return true;
        }

        Player player = (Player) sender;
        player.sendMessage(
                "" +
                "&6&lKit Commands:" +
                " &f/kits &7- &eDisplay all avaliable kits" +
                " &f/kit create &7- &eCreate a kit" +
                " &f/kit delete &7- &eDelete a kit" +
                " &f/kit edit &7- &eEdit a kit" +
                " &f/kit effects &7- &eAdd effects for the kit" +
                ""
        );
        return true;
    }
}
