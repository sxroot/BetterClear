package gg.fullwin.betterclear;

import gg.fullwin.betterclear.command.misc.ClearCommand;
import gg.fullwin.betterclear.command.effects.SpeedCommand;
import gg.fullwin.betterclear.command.effects.StrengthCommand;
import gg.fullwin.betterclear.command.misc.TPKitSelectCommand;
import gg.fullwin.betterclear.command.kits.KitCommand;
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
        StatsRegistry statsRegistry = new StatsRegistry();

        // regitster commands
        Objects.requireNonNull(getCommand("clear")).setExecutor(new ClearCommand());
        Objects.requireNonNull(getCommand("speed")).setExecutor(new SpeedCommand());
        Objects.requireNonNull(getCommand("strength")).setExecutor(new StrengthCommand());
        Objects.requireNonNull(getCommand("kit")).setExecutor(new KitCommand());
        Objects.requireNonNull(getCommand("tpkits")).setExecutor(new TPKitSelectCommand());

        // register listeners
        Objects.requireNonNull(getCommand("stats")).setExecutor(new StatsCommand(statsRegistry));
        getServer().getPluginManager().registerEvents(new LotsOfListeners(), this);
        getServer().getPluginManager().registerEvents(new StatsListener(statsRegistry), this);

        new StatsExpansion(statsRegistry).register();
    }

    @Override
    public void onDisable() {
    }
}
