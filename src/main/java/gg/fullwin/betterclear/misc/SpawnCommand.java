package gg.fullwin.betterclear.misc;

import gg.fullwin.betterclear.util.CC;
import gg.fullwin.betterclear.util.player.Profile;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {

    private final CombatLog combatLog;
    public SpawnCommand(CombatLog combatLog) {
        this.combatLog = combatLog;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("fullwin.spawn")) {
            sender.sendMessage("Unknown command. Type \"/help\" for help.");
            return true;
        }
        Player player = args.length == 0 ? (Player) sender : Bukkit.getPlayer(args[0]);

        if (player == null) {
            sender.sendMessage("Unknown player " + args[0] + ".");
            return true;
        }

        Profile profile = Profile.getByUuid(player.getUniqueId());

        // check if player is in combat if he is deny his tp to spawn
        if (combatLog.isInCombat(player)) {
            player.sendMessage(CC.translate("&cYou can't go to spawn while in combat."));
            return true;
        }
        // otherwise if he isn't in combat continue
        else {
            player.sendMessage(CC.translate("&7Teleporting you to spawn in 5..."));
            Location destination = new Location(player.getWorld(), -1004.5, 269.5, 0.5, (float) 225.098, (float) 0.379);
            player.teleport(destination);
            profile.refreshHotbar();
            return true;
        }
    }
}
