package org.kanyecraft.kanyemod.rank;

import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.kanyecraft.kanyemod.admin.Admin;
import org.kanyecraft.kanyemod.util.KConfig;
import org.kanyecraft.kanyemod.util.KUtil;

public class RankManager
{
    private static Admin admins = Admin.getConfig();

    private static boolean isConsole(CommandSender sender)
    {
        return sender instanceof ConsoleCommandSender;
    }

    public static Rank getRank(Player player)
    {
        String path = player.getName().toLowerCase();
        if (!admins.contains(path) || !admins.getBoolean(path + ".active"))
        {
            return player.isOp() ? Rank.OP : Rank.NON_OP;
        }

        if (!admins.getStringList(path + ".ips").contains(KUtil.getIp(player)) && !admins.contains(path))
        {
            return Rank.IMPOSTOR;
        }

        return Rank.findRank(admins.getString( path + ".rank"));
    }

    public static Rank getRank(CommandSender sender)
    {
        if (isConsole(sender))
        {
            return Rank.CONSOLE;
        }
        return getRank((Player) sender);
    }

    public static Display getDisplay(Player player)
    {
        if (KConfig.getOwnerList().contains(player.getName()))
        {
            return Title.OWNER;
        }
        if (KConfig.getExecutiveList().contains(player.getName()))
        {
            return Title.EXECUTIVE;
        }
        if (KUtil.DEVELOPERS.contains(player.getName()))
        {
            return Title.DEVELOPER;
        }
        return getRank(player);
    }

    public static Display getDisplay(CommandSender sender)
    {
        if (isConsole(sender))
        {
            return Rank.CONSOLE;
        }
        return getDisplay((Player) sender);
    }
}
