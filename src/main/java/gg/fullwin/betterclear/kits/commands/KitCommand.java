package gg.fullwin.betterclear.kits.commands;

import gg.fullwin.betterclear.kits.Kit;
import gg.fullwin.betterclear.util.CC;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public final class KitCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player player)) return true;

        if (!player.hasPermission("fullwin.kit")) {
            player.sendMessage("Unknown command. Type \"/help\" for help.");
            return true;
        }

        if (args.length == 0) {
            player.sendMessage(ChatColor.GOLD.toString() + ChatColor.BOLD + "Kit Commands:",
                    " /kits " + ChatColor.YELLOW + "Display all available kits",
                    " /kit create " + ChatColor.YELLOW + "Create a kit",
                    " /kit delete " + ChatColor.YELLOW + "Delete a kit",
                    " /kit edit " + ChatColor.YELLOW + "Edit a kit",
                    " /kit effects " + ChatColor.YELLOW + "Add effects for the kit",
                    ""
            );
            return true;
        }

        switch (args[0]) {
            case "create" -> {
                if (args.length != 2) {
                    player.sendMessage(CC.translate("&c/kit create <name>"));
                    return true;
                }

                if (Kit.getByName(args[1]) != null) {
                    player.sendMessage(CC.translate("&cThis kit already exist."));
                    return true;
                }

                // create kit etc
                Kit kit = new Kit(args[1], player.getInventory());
                kit.save();
                Kit.getKits().add(kit);
                player.sendMessage(CC.translate("&6Created a new kit &e" + kit.getName() + "&6."));
                return true;
            }

            case "delete" -> {
                if (args.length != 2) {
                    player.sendMessage(CC.translate("&c/kit delete <name>"));
                    return true;
                }

                Kit kit = Kit.getByName(args[1]);

                if (kit == null) {
                    player.sendMessage(CC.translate("&cThat kit doesn't exist."));
                    return true;
                }

                kit.delete();
                Kit.getKits().forEach(Kit::save);
                player.sendMessage(CC.translate("&6Removed the kit &e" + kit.getName() + "&6."));
                return true;
            }
            case "getinv" -> {
                if (args.length != 2) {
                    player.sendMessage(CC.translate("&c/kit getinv <name>"));
                    return true;
                }

                Kit kit = Kit.getByName(args[1]);

                if (kit == null) {
                    player.sendMessage(CC.translate("&cThat kit doesn't exist."));
                    return false;
                }

                player.getInventory().setArmorContents(kit.armourLoadout());
                player.getInventory().setContents(kit.loadout());
                player.updateInventory();
                player.sendMessage(CC.translate("&6You received the kit's inventory."));
                return true;
            }
            case "setinv" -> {
                if (args.length != 2) {
                    player.sendMessage(CC.translate("&c/kit setinv <name>"));
                    return true;
                }

                Kit kit = Kit.getByName(args[1]);

                if (kit == null) {
                    player.sendMessage(CC.translate("&cThat kit doesn't exist."));
                    return false;
                }

                kit.loadout(player.getInventory());
                kit.save();
                player.sendMessage((CC.translate("&6Updated kit's loadout.")));
                return true;
            }
        }
        return true;
    }
}
