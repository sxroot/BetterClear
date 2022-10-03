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
        statsRegistry.findByUid(event.getPlayer().getUniqueId())
                .ifPresentOrElse(stats -> {}, () -> statsRegistry.register(event.getPlayer()));
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        statsRegistry.findByUid(event.getPlayer().getUniqueId()).ifPresent(Stats::save);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        statsRegistry.findByUid(event.getEntity().getUniqueId())
                .ifPresent(Stats::handleDeath);
        Optional.ofNullable(event.getEntity().getKiller())
                .flatMap(killer -> statsRegistry.findByUid(killer.getUniqueId()))
                .ifPresent(Stats::handleKill);
    }
}
