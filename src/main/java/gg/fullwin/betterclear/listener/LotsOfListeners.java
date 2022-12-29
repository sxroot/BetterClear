package gg.fullwin.betterclear.listener;

import gg.fullwin.betterclear.BetterClear;
import gg.fullwin.betterclear.util.CC;
import gg.fullwin.betterclear.util.UnicodeUtil;
import org.bukkit.*;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.block.Sign;
import org.bukkit.entity.ItemFrame;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

public final class LotsOfListeners implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void lolMOTD(ServerListPingEvent e) {
        final String line1 = "\u00A76\u00A7lꜰᴜʟʟᴡɪɴ \u00A7eᴇᴜ \u00A7f\u00A7k: \u00A7r\u00A77[1.16 - 1.19]";
        final String line2 = "» ᴋɪᴛᴘᴠᴘ ᴅᴇᴠᴇʟᴏᴘᴍᴇɴᴛ sᴇʀᴠᴇʀ";

        e.setMotd(line1 + "\n" + line2);
        e.setMaxPlayers(100);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void speedKill(PlayerDeathEvent e) {
        Player player = e.getEntity();
        Player killer = player.getKiller();
        e.setDeathMessage(ChatColor.WHITE + player.getName() + ChatColor.GRAY + " died.");

        if (killer != null) {
            if (killer.hasMetadata("blacklivesmatter"))
                killer.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 40 * 20, 1));
            if (killer.hasMetadata("alllivesmatter"))
                killer.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 40 * 20, 0));
            e.setDeathMessage(CC.PISS + killer.getName() + CC.GRAY + " killed " + CC.PISS + player.getName() + ChatColor.GRAY + " with " + CC.FULLWIN + Math.round(killer.getHealth() / 2) + " \u2764");
            String message = CC.translate("&cʀᴇᴄᴇɪᴠᴇᴅ ᴀ ʜᴇᴀʟ ꜰᴏʀ ᴋɪʟʟɪɴɢ " + UnicodeUtil.parse(player.getName()));
            killer.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
            killer.setHealth(20);
        }
        player.removeMetadata("blacklivesmatter", JavaPlugin.getPlugin(BetterClear.class));
        player.removeMetadata("alllivesmatter", JavaPlugin.getPlugin(BetterClear.class));
        e.getDrops().clear();
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        player.getInventory().clear();
        player.getActivePotionEffects().forEach(potionEffect -> player.removePotionEffect(potionEffect.getType()));
        player.removeMetadata("blacklivesmatter", JavaPlugin.getPlugin(BetterClear.class));
        player.removeMetadata("alllivesmatter", JavaPlugin.getPlugin(BetterClear.class));
        e.getPlayer().setGameMode(GameMode.ADVENTURE);
        e.setJoinMessage(null);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerQuitEvent e) {
        e.setQuitMessage(null);
    }

/*    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        e.setFormat("<" + UnicodeUtil.parse(e.getPlayer().getName()) + "> " + e.getMessage());
    }*/

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEpicStuff(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            //System.out.println("RIGHT CLICKED A BLOCK!");
            if (e.getClickedBlock() != null && e.getClickedBlock().getType().name().endsWith("DOOR")) {
                //System.out.println("OMG A DOOR?!");
                if (!e.getPlayer().hasMetadata("ibebuildinghere")) {
                    e.setCancelled(true);
                }
            }
        }

        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (e.getClickedBlock() != null && e.getClickedBlock().getType().name().endsWith("SIGN")) {
                Inventory inventory = Bukkit.createInventory(null, 27, "Refill");
                ItemStack potion = new ItemStack(Material.SPLASH_POTION);
                PotionMeta potionMeta = ((PotionMeta) potion.getItemMeta());
                assert potionMeta != null;
                potionMeta.setBasePotionData(new PotionData(PotionType.INSTANT_HEAL, false, true));
                potion.setItemMeta(potionMeta);
                for (int i = 0; i < 27; i++) inventory.addItem(potion);
                e.getPlayer().openInventory(inventory);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInteract(PlayerInteractEvent e) {
        if (e.getItem() == null || e.getClickedBlock() == null) {
            return;
        }

        if (e.getItem().getType() == Material.PAINTING) {
            if (e.getPlayer().getGameMode() != GameMode.CREATIVE) {
                if (!e.getPlayer().hasMetadata("ibebuildinghere"))
                    e.setCancelled(true);
            }
        }

        if (e.getClickedBlock().getState() instanceof ItemFrame) {
            if (e.getPlayer().getGameMode() != GameMode.CREATIVE) {
                if (!e.getPlayer().hasMetadata("ibebuildinghere"))
                    e.setCancelled(true);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerPickupItemEvent(PlayerPickupItemEvent event) {
        if (event.getPlayer().getGameMode() == GameMode.CREATIVE) {
            if (!event.getPlayer().hasMetadata("ibebuildinghere")) {
                event.setCancelled(true);
            }
        } else {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerDropItemEvent(PlayerDropItemEvent event) {
        if (event.getPlayer().getGameMode() == GameMode.CREATIVE) {
            if (!event.getPlayer().hasMetadata("ibebuildinghere")) {
                event.setCancelled(true);
            }
        } else {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBreak(BlockBreakEvent event) {
        if (event.getPlayer().getGameMode() == GameMode.CREATIVE) {
            if (!event.getPlayer().hasMetadata("ibebuildinghere")) {
                event.setCancelled(true);
            }
        } else {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlace(BlockPlaceEvent event) {
        if (event.getPlayer().getGameMode() == GameMode.CREATIVE) {
            if (!event.getPlayer().hasMetadata("ibebuildinghere")) {
                event.setCancelled(true);
            }
        } else {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void itemFrameItemRemoval(EntityDamageEvent e) {
        if (e.getEntity() instanceof ItemFrame) {
            e.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBucket(PlayerBucketEmptyEvent event) {
        if (event.getPlayer().getGameMode() == GameMode.CREATIVE) {
            if (!event.getPlayer().hasMetadata("ibebuildinghere")) {
                event.setCancelled(true);
            }
        } else {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerItemDamageEvent(PlayerItemDamageEvent event) {
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onFoodLoss(FoodLevelChangeEvent event) {
        if (event.getEntity() instanceof Player) {
            event.setCancelled(true);
        }
    }
}
