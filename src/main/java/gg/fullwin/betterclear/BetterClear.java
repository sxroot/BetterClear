package gg.fullwin.betterclear;

import gg.fullwin.betterclear.command.kits.*;
import gg.fullwin.betterclear.command.misc.ClearCommand;
import gg.fullwin.betterclear.command.effects.SpeedCommand;
import gg.fullwin.betterclear.command.effects.StrengthCommand;
import gg.fullwin.betterclear.command.misc.TPKitSelectCommand;
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

        StatsRegistry statsRegistry = new StatsRegistry();

        // regitster commands
        Objects.requireNonNull(getCommand("clear")).setExecutor(new ClearCommand());
        Objects.requireNonNull(getCommand("speed")).setExecutor(new SpeedCommand());
        Objects.requireNonNull(getCommand("strength")).setExecutor(new StrengthCommand());
        //Objects.requireNonNull(getCommand("kit")).setExecutor(new KitCommand());
        Objects.requireNonNull(getCommand("tpkits")).setExecutor(new TPKitSelectCommand());

        // kit commnds
        Objects.requireNonNull(getCommand("kit create")).setExecutor(new KitCreateCommand());
        Objects.requireNonNull(getCommand("kit delete")).setExecutor(new KitDeleteCommand());
        Objects.requireNonNull(getCommand("kit getinv")).setExecutor(new KitGetInvCommand());
        Objects.requireNonNull(getCommand("kit setinv")).setExecutor(new KitSetInvCommand());

        // register listeners
        Objects.requireNonNull(getCommand("stats")).setExecutor(new StatsCommand(statsRegistry));
        getServer().getPluginManager().registerEvents(new LotsOfListeners(), this);
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
