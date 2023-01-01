package gg.fullwin.betterclear.listener;

import gg.fullwin.betterclear.BetterClear;
import gg.fullwin.betterclear.stats.Stats;
import gg.fullwin.betterclear.stats.StatsRegistry;
import gg.fullwin.betterclear.util.CC;
import gg.fullwin.betterclear.util.UnicodeUtil;
import org.bukkit.*;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.block.Sign;
import org.bukkit.entity.ItemFrame;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPhysicsEvent;
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

import java.util.List;

import static gg.fullwin.betterclear.BetterClear.DECIMAL_FORMAT;

public final class LotsOfListeners implements Listener {

    private final StatsRegistry statsRegistry;

    public LotsOfListeners(StatsRegistry statsRegistry) {
        this.statsRegistry = statsRegistry;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void lolMOTD(ServerListPingEvent e) {
        final String line1 = "\u00A76\u00A7lꜰᴜʟʟᴡɪɴ \u00A7eᴇᴜ \u00A7f\u00A7k: \u00A7r\u00A77[1.16 - 1.19]";
        final String line2 = "» ᴋɪᴛᴘᴠᴘ ᴅᴇᴠᴇʟᴏᴘᴍᴇɴᴛ sᴇʀᴠᴇʀ";

        e.setMotd(line1 + "\n" + line2);
        e.setMaxPlayers(1);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDeath(PlayerDeathEvent e) {
        Player player = e.getEntity();
        Player killer = player.getKiller();
        if (killer != null) {
            Location destination = new Location(killer.getWorld(), -1004.5, 269.5, 0.5, (float) 225.098, (float) 0.379);
            player.teleport(destination);
            return;
        }
        Location destination = new Location(player.getWorld(), -1004.5, 269.5, 0.5, (float) 225.098, (float) 0.379);
        player.teleport(destination);
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
            e.setDeathMessage(CC.PISS + player.getName() + CC.GRAY + " has been killed by " + CC.PISS + killer.getName() + ChatColor.GRAY + " with " + CC.FULLWIN + DECIMAL_FORMAT.format(killer.getHealth() / 2) + " \u2764");
            String message = CC.translate("&7ʀᴇᴄᴇɪᴠᴇᴅ ᴀ ʜᴇᴀʟ ꜰᴏʀ ᴋɪʟʟɪɴɢ " + UnicodeUtil.parse(player.getName()));
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
        e.setJoinMessage(null);
        Location destination = new Location(player.getWorld(), -1004.5, 269.5, 0.5, (float) 225.098, (float) 0.379);
        player.teleport(destination);
        player.getInventory().clear();
        player.getActivePotionEffects().forEach(potionEffect -> player.removePotionEffect(potionEffect.getType()));
        player.removeMetadata("blacklivesmatter", JavaPlugin.getPlugin(BetterClear.class));
        player.removeMetadata("alllivesmatter", JavaPlugin.getPlugin(BetterClear.class));
        player.setGameMode(GameMode.ADVENTURE);
        player.setHealth(20);
        player.setFlying(false);
        // clear chat
        player.sendMessage("","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","");
        // send motd
        player.sendMessage("",
                CC.translate(CC.FULLWIN + CC.BOLD + "ꜰᴜʟʟᴡɪɴ"),
                CC.translate("&7ᴋɪᴛᴘᴠᴘ ᴅᴇᴠᴇʟᴏᴘᴍᴇɴᴛ sᴇʀᴠᴇʀ."),
                ""
        );
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onLeave(PlayerQuitEvent e) {
        e.setQuitMessage(null);
    }

/*    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        e.setFormat("<" + UnicodeUtil.parse(e.getPlayer().getName()) + "> " + e.getMessage());
    }*/

    // this is dangerous ik but purely for building purposes :D
    @EventHandler(priority = EventPriority.HIGHEST)
    public void noGay(BlockPhysicsEvent e) {
        e.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void lolKillstreak(EntityDeathEvent e) {
        Player killer = e.getEntity().getKiller();

        List<Integer> numbers = List.of(5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 60, 70, 80, 90, 100, 150, 200, 250, 300, 350, 400, 450, 500);
        String[] phrases = {
                "is on a killing spree with a",
                "is dominating the battlefield with a",
                "is unstoppable with a",
                "is a force to be reckoned with on a",
                "is godlike on a",
                "is a true assassin with a",
                "is a killing machine with a",
                "is a master of the battlefield with a",
                "is a legend with a",
                "is on a rampage with a",
                "is a battlefield mastermind with a",
                "is a killing machine with an",
                "is a true master of the game with a",
                "is a living legend with a",
                "is a one-man army with a",
                "is a true monster on the battlefield with a",
                "is a absolute beast with a",
                "is a unstoppable force with a"
        };
        String random = phrases[(int) (Math.random() * phrases.length)];
        /*CC.FULLWIN + CC.BOLD + "KILLSTREAK" + "\n" +*/
        if (killer == null) return;
        statsRegistry.findByUid(killer.getUniqueId()).ifPresent(stats -> {
            for (int number : numbers) {
                if (stats.killstreak() == number) {
                    Bukkit.broadcastMessage(CC.translate("\n" + CC.FULLWIN + CC.BOLD + killer.getName()  + CC.PISS + " " + random + " " + CC.FULLWIN + CC.BOLD + number + CC.PISS + " killstreak." + "\n"));
                    killer.playSound(killer, Sound.ENTITY_PLAYER_LEVELUP, SoundCategory.MASTER, 1.0f, 1.0f);
/*                    for (Player players : Bukkit.getOnlinePlayers()) {
                        players.playSound(killer, Sound.ENTITY_PLAYER_LEVELUP, SoundCategory.MASTER, 1.0f, 1.0f);
                    }*/
                }
            }
        });
    }

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
