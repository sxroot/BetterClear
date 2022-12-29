package gg.fullwin.betterclear.command.kits;

import gg.fullwin.betterclear.util.CC;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

// Delete da Kit
public final class KitSetInvCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String kitName, @NotNull String[] args) {
        if (!sender.hasPermission("fullwin.kit.setinv")) {
            sender.sendMessage("Unknown command. Type \"/help\" for help.");
            return true;
        }

        final Kit kit = Kit.getByName(kitName);
        Player player = (Player) sender;

        if (kit == null) {
            player.sendMessage(CC.translate("&cThat kit doesn't exist."));
            return false;
        }

        kit.getKitInventory().setArmor(player.getInventory().getArmorContents());
        kit.getKitInventory().setContents(player.getInventory().getContents());
        List<PotionEffect> potionEffects = new ArrayList<>(player.getActivePotionEffects());
        kit.getKitInventory().setEffects(potionEffects);
        kit.save();
        player.sendMessage((CC.translate("&6Updated kit's loadout.")));
        return true;
    }
}
