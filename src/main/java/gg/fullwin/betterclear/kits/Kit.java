package gg.fullwin.betterclear.kits;

import gg.fullwin.betterclear.BetterClear;
import gg.fullwin.betterclear.util.InventoryUtil;
import gg.fullwin.betterclear.util.config.BasicConfigurationFile;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.PlayerInventory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class Kit {
    private static final List<Kit> kits = new ArrayList<>();
    private final String name;
    private PlayerInventory inventory;
    private final KitEffects kitEffects = new KitEffects();

    public Kit(String name, PlayerInventory inventory) {
        this.name = name;
        this.inventory = inventory;
    }

    public static List<Kit> getKits() {
        return kits;
    }

    public String getName() {
        return name;
    }

    public PlayerInventory getInventory() {
        return inventory;
    }

    public void setInventory(PlayerInventory inventory) {
        this.inventory = inventory;
    }

    public KitEffects getKitEffects() {
        return kitEffects;
    }

    public void delete() {
        kits.remove(this);
        BetterClear.getInstance().getKitsConfig().getConfiguration().set("kits." + getName(), null);
        try {
            BetterClear.getInstance().getKitsConfig().getConfiguration().save(BetterClear.getInstance().getKitsConfig().getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save() {
        String path = "kits." + name;
        BasicConfigurationFile configFile = BetterClear.getInstance().getKitsConfig();

        configFile.getConfiguration().set(path + ".loadout.armor", InventoryUtil.itemStackArrayToBase64(inventory.getArmorContents()));
        configFile.getConfiguration().set(path + ".loadout.contents", InventoryUtil.itemStackArrayToBase64(inventory.getContents()));
        configFile.getConfiguration().set(path + ".effects", KitEffects.serialize(kitEffects));

        try {
            configFile.getConfiguration().save(configFile.getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // get a kit by name
    public static Kit getByName(String name) {
        for (Kit kit : kits) if (kit.getName().equalsIgnoreCase(name)) return kit;
        return null;
    }
}
