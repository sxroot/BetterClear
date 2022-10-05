package gg.fullwin.betterclear.stats;

import gg.fullwin.betterclear.mongo.MongoDatabase;
import org.bson.Document;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public final class StatsRegistry {
    private final @NotNull Map<UUID, Stats> statsMap = new HashMap<>();

    public StatsRegistry() {
        load();
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(this::save, 5L, 5L, TimeUnit.SECONDS);
    }

    public @NotNull Stats register(@NotNull Player player) {
        Stats stats = new Stats(player.getUniqueId());
        statsMap.put(player.getUniqueId(), stats);
        return stats;
    }

    public @NotNull Optional<Stats> findByUid(@NotNull UUID uid) {
        return Optional.ofNullable(statsMap.get(uid));
    }

    private void load() {
        for (Document document : MongoDatabase.STATS.collection().find()) {
            Stats stats = Stats.from(document);
            statsMap.put(stats.uid(), stats);
        }
    }

    private void save() {
        statsMap.forEach((uuid, stats) -> stats.save());
    }
}
