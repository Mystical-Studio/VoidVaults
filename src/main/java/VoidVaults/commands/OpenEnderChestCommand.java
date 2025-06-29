package VoidVaults.commands;

import VoidVaults.VoidVaults;
import VoidVaults.data.StorageHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class OpenEnderChestCommand implements CommandExecutor {

    private final VoidVaults plugin;
    private final StorageHandler storage;

    public OpenEnderChestCommand(VoidVaults plugin, StorageHandler storage) {
        this.plugin = plugin;
        this.storage = storage;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        Player player = (Player) sender;

        int page = 1;
        ItemStack[] contents = storage.loadVault(player.getUniqueId(), page);
        Inventory vault = Bukkit.createInventory(player, 27, "Void Vault - Page " + page);
        vault.setContents(contents);

        player.openInventory(vault);
        plugin.getLogger().info("Opened vault for " + player.getName());
        return true;
    }
}
