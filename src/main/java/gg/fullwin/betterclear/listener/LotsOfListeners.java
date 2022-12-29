package gg.fullwin.betterclear.listener;

import gg.fullwin.betterclear.BetterClear;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public final class LotsOfListeners implements Listener {
    @EventHandler
    public void speedKill(PlayerDeathEvent e) {
        Player player = e.getEntity();
        Player killer = player.getKiller();
        e.setDeathMessage(ChatColor.WHITE + player.getName() + ChatColor.GRAY + " died.");

        if (killer != null) {
            if (killer.hasMetadata("blacklivesmatter"))
                killer.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 40 * 20, 1));
            if (killer.hasMetadata("alllivesmatter"))
                killer.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 40 * 20, 0));
            e.setDeathMessage(ChatColor.WHITE + killer.getName() +
                    ChatColor.GRAY + " killed " + ChatColor.WHITE + player.getName()
                    + ChatColor.GRAY + " with " + ChatColor.RED + Math.round(killer.getHealth() / 2) + " \u2764");
            killer.setHealth(20);
        }
        player.removeMetadata("blacklivesmatter", JavaPlugin.getPlugin(BetterClear.class));
        player.removeMetadata("alllivesmatter", JavaPlugin.getPlugin(BetterClear.class));
        e.getDrops().clear();
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getItem() == null || e.getClickedBlock() == null) {
            return;
        }

        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (e.getItem().getType() == Material.PAINTING) {
                if (e.getPlayer().getGameMode() != GameMode.CREATIVE) {
                    if (!e.getPlayer().hasMetadata("ibebuildinghere"))
                        e.setCancelled(true);
                }
            }

            if (e.getClickedBlock().getState() instanceof ItemFrame) {
                if (e.getPlayer().getGameMode() != GameMode.CREATIVE) {
                    if (!e.getPlayer().hasMetadata("ibebuildinghere")) {
                        e.setCancelled(true);
                    }
                }
            }
        }
    }

    // Anti-block Glitch apparently ¯\_(ツ)_/¯
    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockBreak(BlockBreakEvent event) {
        if (!event.isCancelled()) return;

        Player player = event.getPlayer();
        if (player.getGameMode() == GameMode.CREATIVE) return;

        Location location = player.getLocation().clone();
        if (location.getBlock().getType() == Material.AIR) return;
        if (!location.getBlock().getType().isSolid()) return;

        location.setX(location.getBlockX() + 0.5D);
        location.setZ(location.getBlockZ() + 0.5D);

        player.teleport(location);
    }

    @EventHandler
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
    public void onEntityDamageEvent(EntityDamageEvent event) {
        Player player = event.getEntity().getServer().getPlayer(event.getEntity().getUniqueId());

        if (event.getEntity() instanceof Player) {
            if (event.getCause() == EntityDamageEvent.DamageCause.VOID) {
                Location destination = new Location(player.getWorld(), 0.5, 202, 0.5, (float) 269.996, (float) 0.573);
                player.teleport(destination);
            } else {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onFoodLoss(FoodLevelChangeEvent event) {
        if (event.getEntity() instanceof Player) {
            event.setCancelled(true);
        }
    }

}
