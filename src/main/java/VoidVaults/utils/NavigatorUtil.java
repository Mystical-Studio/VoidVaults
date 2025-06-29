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
}
