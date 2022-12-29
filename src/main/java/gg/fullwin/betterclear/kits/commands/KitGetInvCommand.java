package gg.fullwin.betterclear.kits.commands;

import gg.fullwin.betterclear.kits.Kit;
import gg.fullwin.betterclear.util.CC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

// Delete da Kit
public final class KitGetInvCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String kitName, @NotNull String[] args) {
        if (!sender.hasPermission("fullwin.kit.getinv")) {
            sender.sendMessage("Unknown command. Type \"/help\" for help.");
            return true;
        }

        System.out.println("u have perms");

        final Kit kit = Kit.getByName(kitName);
        Player player = (Player) sender;

        if (kit == null) {
            player.sendMessage(CC.translate("&cThat kit doesn't exist."));
            System.out.println("racism is bad!");
            return false;
        }

        System.out.println("GREAT SUCCESS?");
        player.getInventory().setArmorContents(kit.getKitInventory().getArmor());
        player.getInventory().setContents(kit.getKitInventory().getContents());
        player.addPotionEffects(kit.getKitInventory().getEffects());
        player.updateInventory();
        player.sendMessage(CC.translate("&6You received the kit's inventory."));
        return true;
    }
}
