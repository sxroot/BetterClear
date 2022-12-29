package gg.fullwin.betterclear.kits.commands;

import gg.fullwin.betterclear.kits.Kit;
import gg.fullwin.betterclear.util.CC;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public final class KitCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String kitName, String[] args) {

        Player player = (Player) sender;

        if (args.length == 0) {

            if (!sender.hasPermission("fullwin.kit")) {
                sender.sendMessage("Unknown command. Type \"/help\" for help.");
                return true;
            }

            sender.sendMessage(ChatColor.GOLD.toString() + ChatColor.BOLD + "Kit Commands:",
                    " /kits " + ChatColor.YELLOW + "Display all avaliable kits",
                    " /kit create " + ChatColor.YELLOW + "Create a kit",
                    " /kit delete " + ChatColor.YELLOW + "Delete a kit",
                    " /kit edit " + ChatColor.YELLOW + "Edit a kit",
                    " /kit effects " + ChatColor.YELLOW + "Add effects for the kit",
                    ""
            );
        }

        if (args.length > 1) {
            switch (args[0]) {
                case "create":
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

                case "delete":
                    if (!sender.hasPermission("fullwin.kit.delete")) {
                        sender.sendMessage("Unknown command. Type \"/help\" for help.");
                        return true;
                    }

                    if (kitName == null) {
                        sender.sendMessage(CC.translate("&cYou have to provide a name."));
                        return false;
                    }

                    final Kit delKit = Kit.getByName(kitName);
                    if (delKit != null) {
                        delKit.delete();
                        Kit.getKits().forEach(Kit::save);
                        sender.sendMessage(CC.translate("&6Removed the kit &e" + delKit.getName() + "&6."));
                        return true;
                    }

                case "effects":
                    //effects shitter

                case "getinv":
                    if (!sender.hasPermission("fullwin.kit.getinv")) {
                        sender.sendMessage("Unknown command. Type \"/help\" for help.");
                        return true;
                    }

                    System.out.println("u have perms");

                    final Kit getInvKit = Kit.getByName(kitName);

                    if (getInvKit == null) {
                        player.sendMessage(CC.translate("&cThat kit doesn't exist."));
                        System.out.println("racism is bad!");
                        return false;
                    }

                    System.out.println("GREAT SUCCESS?");
                    player.getInventory().setArmorContents(getInvKit.getKitInventory().getArmor());
                    player.getInventory().setContents(getInvKit.getKitInventory().getContents());
                    player.addPotionEffects(getInvKit.getKitInventory().getEffects());
                    player.updateInventory();
                    player.sendMessage(CC.translate("&6You received the kit's inventory."));
                    return true;

                case "setinv":
                    if (!sender.hasPermission("fullwin.kit.setinv")) {
                        sender.sendMessage("Unknown command. Type \"/help\" for help.");
                        return true;
                    }

                    final Kit setInvKit = Kit.getByName(kitName);

                    if (setInvKit == null) {
                        player.sendMessage(CC.translate("&cThat kit doesn't exist."));
                        return false;
                    }

                    setInvKit.getKitInventory().setArmor(player.getInventory().getArmorContents());
                    setInvKit.getKitInventory().setContents(player.getInventory().getContents());
                    List<PotionEffect> potionEffects = new ArrayList<>(player.getActivePotionEffects());
                    setInvKit.getKitInventory().setEffects(potionEffects);
                    setInvKit.save();
                    player.sendMessage((CC.translate("&6Updated kit's loadout.")));
                    return true;
            }
        }
        return true;
    }
}
