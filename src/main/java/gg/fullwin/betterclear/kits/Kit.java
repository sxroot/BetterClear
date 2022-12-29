package gg.fullwin.betterclear.kits;

import gg.fullwin.betterclear.BetterClear;
import gg.fullwin.betterclear.util.InventoryUtil;
import gg.fullwin.betterclear.util.config.BasicConfigurationFile;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Main kit shiz
public class Kit {

    // kit shizzle
    private static final List<Kit> kits = new ArrayList<>();
    private final String name;
    private final KitInventory kitInventory= new KitInventory();
    private final KitEffects kitEffects = new KitEffects();
    private final String displayName;
    private final ItemStack displayIcon;

    public Kit(String name) {
        this.name = name;
        this.displayName = ChatColor.GOLD + name;
        this.displayIcon = new ItemStack(Material.NETHERITE_INGOT);
    }

    public static List<Kit> getKits() {
        return kits;
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public ItemStack getDisplayIcon() {
        return this.displayIcon.clone();
    }

    public KitEffects getKitEffects() {
        return kitEffects;
    }

    public KitInventory getKitInventory() {
        return kitInventory;
    }

    public void delete() {
        // delete kit
        kits.remove(this);
        BetterClear.getInstance().getKitsConfig().getConfiguration().set("kits." + getName(), null);
        try {
            BetterClear.getInstance().getKitsConfig().getConfiguration().save(BetterClear.getInstance().getKitsConfig().getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save() {
        // create/save kit
        String path = "kits." + name;
        BasicConfigurationFile configFile = BetterClear.getInstance().getKitsConfig();

        configFile.getConfiguration().set(path + ".icon.material", displayIcon.getType().name());
        configFile.getConfiguration().set(path + ".icon.durability", displayIcon.getDurability());
        configFile.getConfiguration().set(path + ".loadout.armor", InventoryUtil.serializeInventory(kitInventory.getArmor()));
        configFile.getConfiguration().set(path + ".loadout.contents", InventoryUtil.serializeInventory(kitInventory.getContents()));
        configFile.getConfiguration().set(path + ".loadout.effects", InventoryUtil.serializeEffects(kitInventory.getEffects()));

        try {
            configFile.getConfiguration().save(configFile.getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
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
