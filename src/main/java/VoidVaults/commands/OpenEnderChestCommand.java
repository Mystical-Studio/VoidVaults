package VoidVaults.commands;

import VoidVaults.VoidVaults;
import VoidVaults.data.StorageHandler;

import VoidVaults.utils.NavigatorUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class OpenEnderChestCommand implements CommandExecutor {

    private final VoidVaults plugin;
    private final StorageHandler storage;

    public OpenEnderChestCommand(VoidVaults plugin, StorageHandler storage) {
        this.plugin = plugin;
        this.storage = storage;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) return true;

        NavigatorUtil.openVault(player, 1);
        return true;
    }
}
