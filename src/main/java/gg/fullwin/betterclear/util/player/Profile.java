package gg.fullwin.betterclear.util.player;

import gg.fullwin.betterclear.BetterClear;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class Profile {

    private static final Map<UUID, Profile> profiles = new HashMap<>();
    static final List<Player> playerList = new ArrayList<>();
    private PlayerState state;
    private final UUID uuid;

    public UUID getUuid() {
        return uuid;
    }

    public Profile(UUID uuid) {
        this.uuid = uuid;
    }

    public PlayerState getState() {
        return state;
    }

    public void setState(PlayerState state) {
        this.state = state;
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }

    public static Profile getByUuid(UUID uuid) {
        Profile profile = profiles.get(uuid);

        if (profile == null) {
            profile = new Profile(uuid);
        }

        return profile;
    }

    public void refreshHotbar() {
        Player player = getPlayer();
        Profile profile = Profile.getByUuid(player.getUniqueId());

        if (player != null) {
            player.getInventory().clear();
            player.getActivePotionEffects().forEach(potionEffect -> player.removePotionEffect(potionEffect.getType()));
            player.removeMetadata("blacklivesmatter", JavaPlugin.getPlugin(BetterClear.class));
            player.removeMetadata("alllivesmatter", JavaPlugin.getPlugin(BetterClear.class));
            player.setGameMode(GameMode.ADVENTURE);
            player.setHealth(20);
            player.setFlying(false);
            player.updateInventory();
            profile.setState(PlayerState.Lobby);
        }
    }

}
