package gg.fullwin.betterclear.command.effects;

import gg.fullwin.betterclear.BetterClear;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public final class StrengthCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("fullwin.strength")) {
            sender.sendMessage("Unknown command. Type \"/help\" for help.");
            return true;
        }
        Player player = args.length == 0 ? (Player) sender : Bukkit.getPlayer(args[0]);
        if (player == null) {
            sender.sendMessage("Unknown player " + args[0] + ".");
            return true;
        }
        player.setMetadata("alllivesmatter", new FixedMetadataValue(JavaPlugin.getPlugin(BetterClear.class), "gamer"));
        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 40 * 20, 0));
        return true;
    }
}
