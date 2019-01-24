package org.kanyecraft.kanyemod.admin;

import org.bukkit.entity.Player;
import org.kanyecraft.kanyemod.rank.Rank;
import org.kanyecraft.kanyemod.rank.RankManager;
import org.kanyecraft.kanyemod.util.KUtil;
import java.util.ArrayList;
import java.util.List;

public class AdminList
{
    private static Admin admins = Admin.getConfig();
    public static void addAdmin(Player player)
    {
        String path = player.getName().toLowerCase();
        List<String> ips = new ArrayList<String>();
        ips.add(KUtil.getIp(player));
        admins.set(path + ".name", player.getName());
        if (!admins.contains(path))
        {
            admins.set(path + ".rank", Rank.ADMIN.name());
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
    public static void updateRank(Player player, Rank rank)
    {
        admins.set(player.getName().toLowerCase() + ".rank", rank.name());
        admins.save();
    }
}
