package gg.fullwin.betterclear.misc;

import com.github.sirblobman.combatlogx.api.ICombatLogX;
import com.github.sirblobman.combatlogx.api.manager.ICombatManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public final class CombatLog {

    public boolean isEnabled() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        return pluginManager.isPluginEnabled("CombatLogX");
    }

    public ICombatLogX getAPI() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        Plugin plugin = pluginManager.getPlugin("CombatLogX");
        return (ICombatLogX) plugin;
    }

    public boolean isInCombat(Player player) {
        ICombatLogX plugin = getAPI();
        ICombatManager combatManager = plugin.getCombatManager();
        return combatManager.isInCombat(player);
    }

}
