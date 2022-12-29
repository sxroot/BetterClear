package gg.fullwin.betterclear.command.kits;

import gg.fullwin.betterclear.util.CC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

// Delete da Kit
public final class KitGetInvCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String kitName, @NotNull String[] args) {
        if (!sender.hasPermission("fullwin.kit.getinv")) {
            sender.sendMessage("Unknown command. Type \"/help\" for help.");
            return true;
        }

        final Kit kit = Kit.getByName(kitName);
        Player player = (Player) sender;

        if (kit == null) {
            player.sendMessage(CC.translate("&cThat kit doesn't exist."));
            return false;
        }

        player.getInventory().setArmorContents(kit.getKitInventory().getArmor());
        player.getInventory().setContents(kit.getKitInventory().getContents());
        player.addPotionEffects(kit.getKitInventory().getEffects());
        player.updateInventory();
        player.sendMessage(CC.translate("&6You received the kit's inventory."));
        return true;
    }
}
