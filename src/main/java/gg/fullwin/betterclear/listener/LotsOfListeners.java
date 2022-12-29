package gg.fullwin.betterclear.listener;

import gg.fullwin.betterclear.BetterClear;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
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
            if (killer.hasMetadata("blacklivesmatter")) killer.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 40 * 20, 1));
            if (killer.hasMetadata("alllivesmatter")) killer.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 40 * 20, 0));
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
    public void onBlockClick(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Player player = (Player) event.getPlayer();
            Material clicked = event.getClickedBlock().getType();
            ItemStack clickedi = new ItemStack(event.getClickedBlock().getType());
            if(player.getItemInHand().getType() == Material.AIR) {
                player.setItemInHand(clickedi);
                player.sendMessage("debug");
            }
            player.getItemInHand().setType(clicked);
            event.setCancelled(true);
        }
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
