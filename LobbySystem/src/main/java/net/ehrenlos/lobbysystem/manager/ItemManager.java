package net.ehrenlos.lobbysystem.manager;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemManager {

    private ItemStack itemStack;
    private ItemMeta itemMeta;

    public ItemManager(final Material material, final short subID) {
        itemStack = new ItemStack(material, 1, subID);
        itemMeta = itemStack.getItemMeta();
    }

    public ItemManager(final Material material) {
        itemStack = new ItemStack(material, 1, (short) 0);
        itemMeta = itemStack.getItemMeta();
    }

    public ItemManager setDisplayName(final String displayName) {
        this.itemMeta.setDisplayName(displayName);
        return this;
    }

    public ItemManager setSubID(final byte subID) {
        itemStack.getData().setData(subID);
        return this;
    }

    public ItemManager setLore(final String... lore) {
        this.itemMeta.setLore(Arrays.asList(lore));
        return this;
    }

    public ItemManager addLoreLine(final String line) {
        if (this.itemMeta.getLore() != null)
            this.itemMeta.getLore().add(line);
        else {
            final List<String> lore = new ArrayList<>();
            lore.add(line);
            this.itemMeta.setLore(lore);
            this.itemStack.setItemMeta(itemMeta);
        }
        return this;
    }

    public ItemManager addLoreArray(final String[] array) {
        if (this.itemMeta.getLore() != null) {
            for (String current : array) {
                this.itemMeta.getLore().add(current);
            }
        } else {
            final List<String> lore = new ArrayList<>(Arrays.asList(array));
            this.itemMeta.setLore(lore);
        }
        return this;
    }

    public ItemManager setSkullOwner(final String owner) {
        ((SkullMeta) this.itemMeta).setOwner(owner);
        return this;
    }

    public ItemManager setType(final Material material) {
        this.itemStack.setType(material);
        return this;
    }

    public ItemManager setAmount(final Integer amount) {
        this.itemStack.setAmount(amount);
        return this;
    }

    public ItemManager addEnchantment(final Enchantment enchantment) {
        this.itemMeta.addEnchant(enchantment, 1, false);
        return this;
    }

    public ItemManager addEnchantment(final Enchantment enchantment, final Integer power) {
        this.itemMeta.addEnchant(enchantment, power, false);
        return this;
    }

    public ItemStack build() {
        this.itemStack.setItemMeta(itemMeta);
        return this.itemStack;
    }
}
