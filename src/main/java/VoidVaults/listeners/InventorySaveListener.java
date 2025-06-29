package VoidVaults.listeners;

import VoidVaults.VoidVaults;
import VoidVaults.data.StorageHandler;
import VoidVaults.utils.NavigatorUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventorySaveListener implements Listener {

    private final VoidVaults plugin;
    private final StorageHandler storage;

    public InventorySaveListener(VoidVaults plugin, StorageHandler storage) {
        this.plugin = plugin;
        this.storage = storage;
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (!(event.getPlayer() instanceof Player player)) return;

        String title = ChatColor.stripColor(event.getView().getTitle());
        if (!title.startsWith("Void Vault")) return;

        int page = NavigatorUtil.getVaultNumber(player);

        Inventory inventory = event.getInventory();

        int[] usableSlots = {
                10, 11, 12, 13, 14, 15, 16,
                19, 20, 21, 22, 23, 24, 25,
                28, 29, 30, 31, 32, 33, 34
        };

        ItemStack[] savedContents = new ItemStack[usableSlots.length];
        for (int i = 0; i < usableSlots.length; i++) {
            savedContents[i] = inventory.getItem(usableSlots[i]);
        }

        storage.saveVault(player.getUniqueId(), savedContents, page);
    }
}

