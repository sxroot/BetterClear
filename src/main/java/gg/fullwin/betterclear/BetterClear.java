package gg.fullwin.betterclear;

import gg.fullwin.betterclear.command.ClearCommand;
import gg.fullwin.betterclear.command.SpeedCommand;
import gg.fullwin.betterclear.listener.LotsOfListeners;
import gg.fullwin.betterclear.mongo.MongoDatabase;
import gg.fullwin.betterclear.stats.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.util.Objects;

public final class BetterClear extends JavaPlugin {
    public static final @NotNull DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.##");
    @Override
    public void onEnable() {
        // Plugin startup logic

        StatsRegistry statsRegistry = new StatsRegistry();

        Objects.requireNonNull(getCommand("clear")).setExecutor(new ClearCommand());
        Objects.requireNonNull(getCommand("speed")).setExecutor(new SpeedCommand());
        Objects.requireNonNull(getCommand("stats")).setExecutor(new StatsCommand(statsRegistry));
        getServer().getPluginManager().registerEvents(new LotsOfListeners(), this);
        getServer().getPluginManager().registerEvents(new StatsListener(statsRegistry), this);

        new StatsExpansion(statsRegistry).register();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
