package gg.fullwin.betterclear.kits;

import gg.fullwin.betterclear.BetterClear;
import gg.fullwin.betterclear.util.ItemStackBase64;
import gg.fullwin.betterclear.util.config.BasicConfigurationFile;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static gg.fullwin.betterclear.util.ItemStackBase64.isArmor;

public final class Kit {
    private static final @NotNull List<Kit> kits = new ArrayList<>();
    private @NotNull String name;
    private final @NotNull List<ItemStack> loadout = new ArrayList<>();
    private final @NotNull List<ItemStack> armourLoadout = new ArrayList<>();
    private final @NotNull KitEffects kitEffects = new KitEffects();

    public Kit(@NotNull String name, @NotNull PlayerInventory inventory) {
        this.name = name;
        loadout(inventory);
    }

    public Kit(@NotNull String name, @NotNull ItemStack[] loadout, @NotNull ItemStack[] armourLoadout) {
        this.name = name;
        for (ItemStack itemStack : loadout) this.loadout.add(itemStack.clone());
        for (ItemStack itemStack : armourLoadout) this.armourLoadout.add(itemStack.clone());
    }

    public @NotNull String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public @NotNull ItemStack[] loadout() {
        return loadout.toArray(new ItemStack[0]);
    }

    public @NotNull ItemStack[] armourLoadout() {
        return armourLoadout.toArray(new ItemStack[0]);
    }

    public void loadout(@NotNull PlayerInventory inventory) {
        for (ItemStack itemStack : inventory.getContents()) {
            if (itemStack != null && !isArmor(itemStack)) {
                loadout.add(itemStack.clone());
                System.out.println(itemStack.getType());
            }
        }
        for (ItemStack itemStack : inventory.getArmorContents()) {
            if (itemStack != null) {
                armourLoadout.add(itemStack.clone());
                System.out.println(itemStack.getType());
            }
        }
    }

    public void delete() {
        kits.remove(this);
        BetterClear.getInstance().getKitsConfig().getConfiguration().set("kits." + name, null);
        try {
            BetterClear.getInstance().getKitsConfig().getConfiguration().save(BetterClear.getInstance().getKitsConfig().getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save() {
        String path = "kits." + name;
        BasicConfigurationFile configFile = BetterClear.getInstance().getKitsConfig();

        configFile.getConfiguration().set(path + ".contents", ItemStackBase64.base64(loadout.toArray(new ItemStack[0])));
        configFile.getConfiguration().set(path + ".armor", ItemStackBase64.base64(armourLoadout.toArray(new ItemStack[0])));
        configFile.getConfiguration().set(path + ".effects", KitEffects.serialize(kitEffects));

        try {
            configFile.getConfiguration().save(configFile.getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void load() {

    }

    public static @NotNull List<Kit> getKits() {
        return kits;
    }

    public static @Nullable Kit getByName(String name) {
        for (Kit kit : kits) if (kit.getName().equalsIgnoreCase(name)) return kit;
        return null;
    }

    @Override
    public String toString() {
        return "Kit{" +
                "name='" + name + '\'' +
                ", loadout=" + loadout +
                ", armourLoadout=" + armourLoadout +
                ", kitEffects=" + kitEffects +
                '}';
    }
}
