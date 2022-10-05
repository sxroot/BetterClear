package gg.fullwin.betterclear.stats;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public record StatsListener(@NotNull StatsRegistry statsRegistry) implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        System.out.println(statsRegistry.findByUid(event.getPlayer().getUniqueId())
                .orElseGet(() -> statsRegistry.register(event.getPlayer())));
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        statsRegistry.findByUid(event.getPlayer().getUniqueId()).ifPresent(Stats::save);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        statsRegistry.findByUid(event.getEntity().getUniqueId())
                .ifPresent(stats -> {
                    stats.handleDeath();
                    System.out.println(stats);
                });

        Optional.ofNullable(event.getEntity().getKiller())
                .flatMap(killer -> statsRegistry.findByUid(killer.getUniqueId()))
                .ifPresent(stats -> {
                    stats.handleKill();
                    System.out.println(stats);
                });
    }
}
