package me.superischroma.superplus.rank;

import me.superischroma.superplus.admin.Admin;
import me.superischroma.superplus.util.SConfig;
import me.superischroma.superplus.util.SUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

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

        if (!admins.getStringList(path + ".ips").contains(SUtil.getIp(player)) && admins.contains(path))
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
        if (SConfig.getManagerList().contains(player.getName()))
        {
            return Title.MANAGER;
        }
        if (SUtil.DEVELOPERS.contains(player.getName()))
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
