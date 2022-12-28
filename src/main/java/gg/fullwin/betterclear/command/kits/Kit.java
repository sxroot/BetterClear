package gg.fullwin.betterclear.command.kits;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

// Main kit shiz
public class Kit {

    // kit shizzle
    private static final List<Kit> kits = new ArrayList<>();
    private final String name;
    private final String displayName;
    private final ItemStack displayIcon;
    private final KitEffects kitEffects = new KitEffects();

    public Kit(String name) {
        this.name = name;
        this.displayName = ChatColor.GOLD + name;
        this.displayIcon = new ItemStack(Material.NETHERITE_INGOT);
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public ItemStack getDisplayIcon() {
        return displayIcon;
    }

    public KitEffects getKitEffects() {
        return kitEffects;
    }

    public void delete() {
        // delete kit
        kits.remove(this);
    }

    public void create() {
        // create/save kit
        kits.add(this);
    }

    // get a kit by name
    public static Kit getByName(String name) {
        for (Kit kit : kits) {
            if (kit.getName().equalsIgnoreCase(name)) {
                return kit;
            }
        }

        return null;
    }
}
