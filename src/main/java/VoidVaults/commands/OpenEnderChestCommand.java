package VoidVaults.commands;

import VoidVaults.VoidVaults;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

public class OpenEnderChestCommand implements CommandExecutor {

    private final VoidVaults plugin;

    public OpenEnderChestCommand(VoidVaults plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ChatColor.RED + "Only players can use this command.");
            return true;
        }

        Player player = (Player) commandSender;

        Inventory vault = Bukkit.createInventory(player, 27, ChatColor.DARK_PURPLE + "Void Vault - Page 1");
        player.openInventory(vault);

        return true;
    }
}
