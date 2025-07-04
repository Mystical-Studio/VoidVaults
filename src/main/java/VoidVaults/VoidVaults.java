package VoidVaults;

import VoidVaults.commands.OpenVaultCommand;
import VoidVaults.data.StorageHandler;
import VoidVaults.listeners.InventoryClickListener;
import VoidVaults.listeners.InventorySaveListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class VoidVaults extends JavaPlugin {

    private static StorageHandler storageHandler;

    @Override
    public void onEnable() {

        this.storageHandler = new StorageHandler(getDataFolder());
        getCommand("vv").setExecutor(new OpenVaultCommand(this, storageHandler));
        getServer().getPluginManager().registerEvents(new InventorySaveListener(this, storageHandler), this);
        getServer().getPluginManager().registerEvents(new InventoryClickListener(this), this);

    }

    public static StorageHandler getStorageHandler() {
        return storageHandler;
    }

    @Override
    public void onDisable() {

    }
}
