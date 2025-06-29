package VoidVaults;

import VoidVaults.commands.OpenEnderChestCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class VoidVaults extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("ec").setExecutor(new OpenEnderChestCommand(this));
        Bukkit.getLogger().info("Hello world!");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info("Shutting down!");
    }
}
