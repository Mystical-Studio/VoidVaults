package VoidVaults.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class VaultGUI {

    public static Inventory createVaultGUI(Player player, ItemStack[] storedContents, int page) {
        Inventory vault = Bukkit.createInventory(player, 54, ChatColor.BLACK + "Void Vault - Page " + page);

        // Black glass pane item
        ItemStack glass = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta meta = glass.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(" ");
            glass.setItemMeta(meta);
        }

        // Set glass ring
        for (int i = 0; i < 54; i++) {
            int row = i / 9;
            int col = i % 9;
            boolean isEdge = row == 0 || row == 5 || col == 0 || col == 8;
            if (isEdge) {
                vault.setItem(i, glass);
            }
        }

        if (page != 1) {
            // Create Previous Page button
            ItemStack prevPage = new ItemStack(Material.ARROW);
            ItemMeta prevMeta = prevPage.getItemMeta();
            if (prevMeta != null) {
                prevMeta.setDisplayName(ChatColor.YELLOW + "Previous Page");
                prevPage.setItemMeta(prevMeta);
            }
            vault.setItem(45, prevPage); // Bottom-left
        }


        // Create Next Page button
        ItemStack nextPage = new ItemStack(Material.ARROW);
        ItemMeta nextMeta = nextPage.getItemMeta();
        if (nextMeta != null) {
            nextMeta.setDisplayName(ChatColor.YELLOW + "Next Page");
            nextPage.setItemMeta(nextMeta);
        }
        vault.setItem(53, nextPage); // Bottom-right

        // Place stored items into usable area (slots 10–16, 19–25, 28–34)
        int[] usableSlots = {
                10, 11, 12, 13, 14, 15, 16,
                19, 20, 21, 22, 23, 24, 25,
                28, 29, 30, 31, 32, 33, 34
        };

        for (int i = 0; i < Math.min(storedContents.length, usableSlots.length); i++) {
            vault.setItem(usableSlots[i], storedContents[i]);
        }

        return vault;
    }
}
