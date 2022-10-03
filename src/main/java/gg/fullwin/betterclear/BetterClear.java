package gg.fullwin.betterclear;

import gg.fullwin.betterclear.command.ClearCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class BetterClear extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Objects.requireNonNull(getCommand("clear")).setExecutor(new ClearCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
