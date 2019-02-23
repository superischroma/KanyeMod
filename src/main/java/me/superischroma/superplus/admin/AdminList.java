package me.superischroma.superplus.admin;

import me.superischroma.superplus.util.SConfig;
import me.superischroma.superplus.util.SUtil;
import org.bukkit.entity.Player;
import me.superischroma.superplus.rank.Rank;
import me.superischroma.superplus.rank.RankManager;

import java.util.ArrayList;
import java.util.List;

public class AdminList
{
    private static Admin admins = Admin.getConfig();
    public static void addAdmin(Player player)
    {
        String path = player.getName().toLowerCase();
        List<String> ips = new ArrayList<String>();
        ips.add(SUtil.getIp(player));
        admins.set(path + ".name", player.getName());
        if (!admins.contains(path + ".rank"))
        {
            admins.set(path + ".rank", Rank.SWING_ADMIN.name());
        }
        admins.set(path + ".active", true);
        admins.set(path + ".ips", ips);
        admins.save();
    }
    public static void removeAdmin(Player player)
    {
        admins.set(player.getName().toLowerCase() + ".active", false);
        admins.save();
    }
    public static void deleteAdmin(Player player)
    {
        admins.set(player.getName().toLowerCase(), null);
        admins.save();
    }
    public static boolean isAdmin(Player player)
    {
        return RankManager.getRank(player).isAdmin;
    }
    public static boolean isImpostor(Player player)
    {
        return !admins.getStringList(player.getName().toLowerCase() + ".ips").contains(SUtil.getIp(player)) && admins.contains(player.getName().toLowerCase());
    }
    public static void updateRank(Player player, Rank rank)
    {
        admins.set(player.getName().toLowerCase() + ".rank", rank.name());
        admins.save();
    }
    public static void addIp(Player player, String ip)
    {
        List<String> ips = admins.getStringList(player.getName().toLowerCase() + ".ips");
        ips.add(ip);
        admins.set(player.getName().toLowerCase() + ".ips", ips);
        admins.save();
    }
    public static boolean hasCustomACFormat(Player player)
    {
        return admins.contains(player.getName().toLowerCase() + ".acformat");
    }

    public static boolean hasCustomLoginMSG(Player player)
    {
        return admins.contains(player.getName().toLowerCase() + ".login");
    }

    public static boolean hasManagerPrefix(Player player)
    {
        return admins.contains(player.getName().toLowerCase() + ".manager")
                && SConfig.getManagerList().contains(player.getName());
    }

    public static boolean isCommandSpyEnabled(Player player)
    {
        return admins.getBoolean(player.getName().toLowerCase() + ".commandspy");
    }
}
