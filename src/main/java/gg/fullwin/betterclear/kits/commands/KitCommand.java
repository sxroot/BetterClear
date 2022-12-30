package gg.fullwin.betterclear.kits.commands;

import gg.fullwin.betterclear.kits.Kit;
import gg.fullwin.betterclear.util.CC;
import gg.fullwin.betterclear.util.UnicodeUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public final class KitCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!sender.hasPermission("fullwin.kit")) {
            sender.sendMessage("Unknown command. Type \"/help\" for help.");
            return true;
        }

        if (args.length == 0) {

            sender.sendMessage(
                    "",
                    CC.FULLWIN + CC.BOLD + UnicodeUtil.parse("Kit Commands:"),
                    UnicodeUtil.parse(" /kits ") + CC.PISS + UnicodeUtil.parse("Display all available kits"),
                    UnicodeUtil.parse(" /kit create ") + CC.PISS + UnicodeUtil.parse("Create a kit"),
                    UnicodeUtil.parse(" /kit delete ") + CC.PISS + UnicodeUtil.parse("Delete a kit"),
                    UnicodeUtil.parse(" /kit give ") + CC.PISS + UnicodeUtil.parse("Give a kit"),
                    UnicodeUtil.parse(" /kit set ") + CC.PISS + UnicodeUtil.parse("Edit a kit"),
                    ""
            );
            return true;
        }

        switch (args[0]) {
            case "list" -> {
                for (Kit kit : Kit.getKits()) {
                    sender.sendMessage(kit.toString());
                }
                return true;
            }
            case "create" -> {
                if (!(sender instanceof Player player)) return true;
                if (args.length != 2) {
                    player.sendMessage(CC.translate("&c/kit create <name>"));
                    return true;
                }

                if (Kit.getByName(args[1]) != null) {
                    player.sendMessage(CC.translate("&cThis kit already exist."));
                    return true;
                }

                Kit kit = new Kit(args[1], player.getInventory());
                kit.save();
                Kit.getKits().add(kit);
                player.sendMessage(CC.translate("&6Created &e" + kit.getName() + "&6."));
                return true;
            }

            case "delete" -> {
                if (args.length != 2) {
                    sender.sendMessage(CC.translate("&c/kit delete <name>"));
                    return true;
                }

                Kit kit = Kit.getByName(args[1]);

                if (kit == null) {
                    sender.sendMessage(CC.translate("&cThat kit doesn't exist."));
                    return true;
                }

                kit.delete();
                Kit.getKits().forEach(Kit::save);
                sender.sendMessage(CC.translate("&6Removed &e" + kit.getName() + "&6."));
                return true;
            }
            case "give" -> {
                Player target = sender instanceof Player player ? player : null;
                if (args.length < 2) {
                    sender.sendMessage(CC.translate("&c/kit give <name> [player]"));
                    return true;
                }

                if (args.length == 3) target = Bukkit.getPlayer(args[2]);

                if (target == null) {
                    sender.sendMessage(CC.translate("&cThat player is offline."));
                    return true;
                }

                Kit kit = Kit.getByName(args[1]);

                if (kit == null) {
                    sender.sendMessage(CC.translate("&cThat kit doesn't exist."));
                    return false;
                }

                target.getInventory().setContents(kit.loadout());
                target.getInventory().setArmorContents(kit.armourLoadout());
                target.updateInventory();
                if (sender instanceof Player) {
                    sender.sendMessage(CC.translate("&6Given &e" + target.getName() + "&6 kit &e" + kit.getName() + "&6."));
                }
                return true;
            }
            case "set" -> {
                if (!(sender instanceof Player player)) return true;
                if (args.length != 2) {
                    player.sendMessage(CC.translate("&c/kit set <name>"));
                    return true;
                }

                Kit kit = Kit.getByName(args[1]);

                if (kit == null) {
                    player.sendMessage(CC.translate("&cThat kit doesn't exist."));
                    return false;
                }

                kit.loadout(player.getInventory());
                kit.save();
                sender.sendMessage((CC.translate("&6Updated &e" + kit.getName() + "'s &6loadout.")));
                return true;
            }
        }
        return true;
    }
}
