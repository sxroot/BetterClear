package gg.fullwin.betterclear;

import gg.fullwin.betterclear.misc.*;
import gg.fullwin.betterclear.kits.Kit;
import gg.fullwin.betterclear.kits.commands.*;
import gg.fullwin.betterclear.listener.LotsOfListeners;
import gg.fullwin.betterclear.mongo.MongoDatabase;
import gg.fullwin.betterclear.stats.*;
import gg.fullwin.betterclear.util.config.BasicConfigurationFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.util.Objects;

public final class BetterClear extends JavaPlugin {

    private static BetterClear betterClear;
    private BasicConfigurationFile kitsConfig;

    public static final @NotNull DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.##");

    public static BetterClear getInstance() {
        return betterClear;
    }

    @Override
    public void onEnable() {
        betterClear = this;
        kitsConfig = new BasicConfigurationFile(this, "kits");

        Kit.load();

        StatsRegistry statsRegistry = new StatsRegistry();
        CombatLog combatLog = new CombatLog();

        // regitster commands
        Objects.requireNonNull(getCommand("clear")).setExecutor(new ClearCommand());
        Objects.requireNonNull(getCommand("speed")).setExecutor(new SpeedCommand());
        Objects.requireNonNull(getCommand("strength")).setExecutor(new StrengthCommand());
        Objects.requireNonNull(getCommand("tpkits")).setExecutor(new TPKitSelectCommand());
        Objects.requireNonNull(getCommand("build")).setExecutor(new BuildCommand());
        Objects.requireNonNull(getCommand("spawn")).setExecutor(new SpawnCommand(combatLog));

        // kit commnds
        Objects.requireNonNull(getCommand("kit")).setExecutor(new KitCommand());

        // register listeners
        Objects.requireNonNull(getCommand("stats")).setExecutor(new StatsCommand(statsRegistry));
        getServer().getPluginManager().registerEvents(new LotsOfListeners(statsRegistry), this);
        getServer().getPluginManager().registerEvents(new StatsListener(statsRegistry), this);

        new StatsExpansion(statsRegistry).register();
    }

    @Override
    public void onDisable() {
        Kit.getKits().forEach(Kit::save);
    }

    public BasicConfigurationFile getKitsConfig() {
        return kitsConfig;
    }

    public void setKitsConfig(BasicConfigurationFile kitsConfig) {
        this.kitsConfig = kitsConfig;
    }
}
