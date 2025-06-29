package VoidVaults.utils;

import org.bukkit.entity.Player;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PermissionUtil {

    private static final Pattern PERMISSION_PATTERN = Pattern.compile("voidvaults\\.vault\\.(\\d+)");

    public static int getMaxPages(Player player) {
        int maxPages = 0;

        for (var perm : player.getEffectivePermissions()) {
            Matcher matcher = PERMISSION_PATTERN.matcher(perm.getPermission());
            if (matcher.matches()) {
                int pages = Integer.parseInt(matcher.group(1));
                if (pages > maxPages) {
                    maxPages = pages;
                }
            }
        }

        return maxPages;
    }
}
