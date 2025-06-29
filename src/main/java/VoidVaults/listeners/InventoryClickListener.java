package VoidVaults.listeners;

import VoidVaults.VoidVaults;
import VoidVaults.gui.VaultGUI;
import VoidVaults.utils.NavigatorUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryClickListener implements Listener {

    private final VoidVaults plugin;

    public InventoryClickListener(VoidVaults plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player player)) return;

        String title = event.getView().getTitle();
        if (!title.startsWith(ChatColor.DARK_PURPLE + "Void Vault")) return;

        int rawSlot = event.getRawSlot(); // Includes top and bottom
        int slot = event.getSlot();       // Relative to top or bottom depending on context

        ItemStack clicked = event.getCurrentItem();
        if (clicked == null || clicked.getType() == Material.AIR) return;

        // Usable slots in the top inventory
        int[] usableSlots = {
                10, 11, 12, 13, 14, 15, 16,
                19, 20, 21, 22, 23, 24, 25,
                28, 29, 30, 31, 32, 33, 34
        };

        // If they're clicking the top inventory (vault GUI)
        if (rawSlot < event.getView().getTopInventory().getSize()) {
            // Cancel by default
            event.setCancelled(true);

            Material type = clicked.getType();
            if (type == Material.BLACK_STAINED_GLASS_PANE) {
                return; // Don't allow pickup
            }

            if (type == Material.ARROW) {

                ItemMeta meta = clicked.getItemMeta();
                if (meta != null & meta.hasDisplayName()) {
                    String displayName = ChatColor.stripColor(meta.getDisplayName());

                    if (displayName.equalsIgnoreCase("Next Page")) {
                        // Handle going to the next page
                        NavigatorUtil.openVault(player, NavigatorUtil.getVaultNumber(player) + 1);
                    }

                    if (displayName.equalsIgnoreCase("Previous Page")) {
                        // Handle going to the previous page
                        NavigatorUtil.openVault(player, NavigatorUtil.getVaultNumber(player) - 1);
                    }
                }
            }

            // Allow only usable slots
            for (int usable : usableSlots) {
                if (rawSlot == usable) {
                    event.setCancelled(false);
                    return;
                }
            }
        } else {
            // They're clicking their own inventory — allow
            event.setCancelled(false);
        }
    }
}
