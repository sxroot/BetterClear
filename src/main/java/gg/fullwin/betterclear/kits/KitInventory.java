package gg.fullwin.betterclear.kits;

import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;
import java.util.List;

public class KitInventory {
    private ItemStack[] armor;
    private ItemStack[] contents;

    public KitInventory() {
        this.armor = new ItemStack[4];
        this.contents = new ItemStack[36];
    }

    public KitInventory(ItemStack[] armor, ItemStack[] contents) {
        this.armor = armor;
        this.contents = contents;
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
}
