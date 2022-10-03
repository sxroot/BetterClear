package gg.fullwin.betterclear;

import gg.fullwin.betterclear.command.ClearCommand;
import gg.fullwin.betterclear.command.SpeedCommand;
import gg.fullwin.betterclear.listener.LotsOfListeners;
import gg.fullwin.betterclear.stats.Stats;
import gg.fullwin.betterclear.stats.StatsCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class BetterClear extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Objects.requireNonNull(getCommand("clear")).setExecutor(new ClearCommand());
        Objects.requireNonNull(getCommand("speed")).setExecutor(new SpeedCommand());
        Objects.requireNonNull(getCommand("stats")).setExecutor(new StatsCommand());
        getServer().getPluginManager().registerEvents(new LotsOfListeners(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
