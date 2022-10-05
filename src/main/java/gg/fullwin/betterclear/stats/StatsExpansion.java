package gg.fullwin.betterclear.stats;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class StatsExpansion extends PlaceholderExpansion {
    private final @NotNull StatsRegistry statsRegistry;

    public StatsExpansion(@NotNull StatsRegistry statsRegistry) {
        this.statsRegistry = statsRegistry;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "stats";
    }

    @Override
    public @NotNull String getAuthor() {
        return "brawn";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0";
    }

    @Override
    public @Nullable String onRequest(@NotNull OfflinePlayer player, @NotNull String placeholder) {
        return statsRegistry.findByUid(player.getUniqueId())
                .map(stats -> switch (placeholder) {
                    case "kills" -> String.valueOf(stats.kills());
                    case "deaths" -> String.valueOf(stats.deaths());
                    case "kdr" -> String.valueOf(stats.kdr());
                    case "killstreak" -> String.valueOf(stats.killstreak());
                    case "maxstreak" -> String.valueOf(stats.maxstreak());
                    default -> null;
                }).orElse(null);
    }
}
