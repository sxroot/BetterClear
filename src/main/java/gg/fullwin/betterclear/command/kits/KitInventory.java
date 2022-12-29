package gg.fullwin.betterclear.command.kits;

import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;
import java.util.List;

public class KitInventory {

    private String customName = "Default";
    private ItemStack[] armor;
    private ItemStack[] contents;
    private List<PotionEffect> effects;

    public KitInventory() {
        this.armor = new ItemStack[4];
        this.contents = new ItemStack[36];
        this.effects = new ArrayList<>();
    }

    public KitInventory(String customName) {
        this.customName = customName;
        this.armor = new ItemStack[4];
        this.contents = new ItemStack[36];
        this.effects = new ArrayList<>();
    }

    public KitInventory(ItemStack[] armor, ItemStack[] contents) {
        this.armor = armor;
        this.contents = contents;
        this.effects = new ArrayList<>();
    }

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public ItemStack[] getArmor() {
        return armor;
    }

    public void setArmor(ItemStack[] armor) {
        this.armor = armor;
    }

    public ItemStack[] getContents() {
        return contents;
    }

    public void setContents(ItemStack[] contents) {
        this.contents = contents;
    }

    public List<PotionEffect> getEffects() {
        return effects;
    }

    public void setEffects(List<PotionEffect> effects) {
        this.effects = effects;
    }
}
