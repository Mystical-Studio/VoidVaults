package VoidVaults.data;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class StorageHandler {

    private final File dataFolder;

    public StorageHandler(File pluginFolder) {
        this.dataFolder = new File(pluginFolder, "vaults");
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }
    }

    public void saveVault(UUID uuid, ItemStack[] contents, int page) {
        File file = new File(dataFolder, uuid + ".yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        config.set("page." + page, contents);
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public ItemStack[] loadVault(UUID uuid, int page) {
        File file = new File(dataFolder, uuid + ".yml");
        if (!file.exists()) return new ItemStack[27];

        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        List<ItemStack> itemList = (List<ItemStack>) config.getList("page." + page);

        if (itemList == null) return new ItemStack[27];

        // Convert List<ItemStack> to ItemStack[]
        ItemStack[] contents = new ItemStack[27];
        for (int i = 0; i < contents.length; i++) {
            contents[i] = (i < itemList.size()) ? itemList.get(i) : null;
        }

        return contents;
    }
}
