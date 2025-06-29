package VoidVaults.commands;

import VoidVaults.VoidVaults;
import VoidVaults.data.StorageHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
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
        Inventory vault = createVaultGUI(player, contents, page);

        player.openInventory(vault);
        return true;
    }

    private Inventory createVaultGUI(Player player, ItemStack[] storedContents, int page) {
        Inventory vault = Bukkit.createInventory(player, 54, ChatColor.DARK_PURPLE + "Void Vault - Page " + page);

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
