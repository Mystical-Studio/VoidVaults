package VoidVaults.listeners;

import VoidVaults.VoidVaults;
import VoidVaults.data.StorageHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

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

        String title = event.getView().getTitle();
        if (!title.startsWith("Void Vault - Page ")) return;

        int page = Integer.parseInt(title.replace("Void Vault - Page ", ""));
        storage.saveVault(player.getUniqueId(), event.getInventory().getContents(), page);
    }

}
