package gg.fullwin.betterclear.misc;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public final class TPKitSelectCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = args.length == 0 ? (Player) sender : Bukkit.getPlayer(args[0]);
        if (player == null) {
            sender.sendMessage("Unknown player " + args[0] + ".");
            return true;
        }

        Location destination = new Location(player.getWorld(), -21.5, 202, 0.5, (float) 89.891, (float) 2.297);
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 40, 255, true, false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS, 40, 255, true, false));
        player.teleport(destination);
        return true;
    }
}