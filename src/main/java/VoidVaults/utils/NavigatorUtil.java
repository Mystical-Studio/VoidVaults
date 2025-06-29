package VoidVaults.utils;

import VoidVaults.VoidVaults;
import VoidVaults.data.StorageHandler;
import VoidVaults.gui.VaultGUI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class NavigatorUtil {

    public static void openVault(Player player, int page) {

        int maxPages = PermissionUtil.getMaxPages(player);
        if (maxPages < 1) {
            player.sendMessage(ChatColor.RED + "You have not unlocked any vault pages.");
            return;
        }

        if (page < 1 || page > maxPages) {
            player.sendMessage(ChatColor.RED + "Invalid page.");
            return;
        }

        UUID uuid = player.getUniqueId();
        ItemStack[] contents = VoidVaults.getStorageHandler().loadVault(uuid, page);
        Inventory gui = VaultGUI.createVaultGUI(player, contents, page);
        player.openInventory(gui);
    }

    public static int getVaultNumber(Player player) {
        String title = ChatColor.stripColor(player.getOpenInventory().getTitle());

        // Expecting title format: "Void Vault - Page X"
        String[] parts = title.split(" ");
        for (int i = 0; i < parts.length; i++) {
            if (parts[i].equalsIgnoreCase("Page") && i + 1 < parts.length) {
                try {
                    return Integer.parseInt(parts[i + 1]);
                } catch (NumberFormatException ignored) {
                    return 1; // Default if parsing fails
                }
            }
        }

        return 1; // Default if format doesn't match
    }
}
