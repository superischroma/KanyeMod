package org.kanyecraft.kanyemod.banning;

import org.bukkit.entity.Player;
import org.kanyecraft.kanyemod.util.KUtil;

import java.util.ArrayList;
import java.util.List;

public class BanList
{
    private static Bans bans = Bans.getConfig();
    public static void addBan(Player banned, Player punisher, BanType type, String reason)
    {
        String path = banned.getName().toLowerCase();
        boolean previouslyBanned = isBanned(banned);
        bans.set(path + ".name", banned.getName());
        bans.set(path + ".punisher", punisher.getName());
        bans.set(path + ".type", type.name());
        bans.set(path + ".length", System.currentTimeMillis() + type.getLength());
        bans.set(path + ".reason", reason);
        if (previouslyBanned)
        {
            updateBannedIps(banned);
        }
        else
        {
            List<String> ips = new ArrayList<>();
            ips.add(KUtil.getIp(banned));
            bans.set(path + ".ips", ips);
        }
        bans.save();
    }

    public static void removeBan(Player banned)
    {
        bans.set(banned.getName().toLowerCase(), null);
        bans.save();
    }

    public static boolean isBanned(Player player)
    {
        return bans.contains(player.getName().toLowerCase());
    }

    private static void updateBannedIps(Player player)
    {
        List<String> ips = bans.getStringList(player.getName().toLowerCase() + ".ips");
        ips.add(KUtil.getIp(player));
        bans.set(player.getName().toLowerCase() + ".ips", ips);
        bans.save();
    }
}
