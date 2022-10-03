package gg.fullwin.betterclear.listener;

import gg.fullwin.betterclear.BetterClear;
import gg.fullwin.betterclear.stats.StatsRegistry;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public final class LotsOfListeners implements Listener {
    @EventHandler
    public void speedKill(PlayerDeathEvent e) {
        Player player = e.getEntity();
        Player killer = player.getKiller();
        assert killer != null;
        if (killer.hasMetadata("blacklivesmatter")) {
            killer.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 40*20, 1));
        }
        player.removeMetadata("blacklivesmatter", JavaPlugin.getPlugin(BetterClear.class));
        e.getDrops().clear();
        e.setDeathMessage(ChatColor.WHITE + killer.getName() +
                ChatColor.GRAY + " killed " + ChatColor.WHITE + player.getName()
                + ChatColor.GRAY + " with " + ChatColor.RED + Math.round(killer.getHealth() / 2) + " \u2764");
        killer.setHealth(20);
    }

    @EventHandler
    public void a(EntityDropItemEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void b(PlayerDropItemEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void c(EntityPickupItemEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void d(FoodLevelChangeEvent e) {
        e.setCancelled(true);
    }
}
