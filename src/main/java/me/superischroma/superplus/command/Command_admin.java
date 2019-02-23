package me.superischroma.superplus.command;

import me.superischroma.superplus.listener.Freeze;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import me.superischroma.superplus.admin.AdminList;
import me.superischroma.superplus.rank.Rank;
import me.superischroma.superplus.util.SUtil;

@CommandParameters(name = "admin", description = "Manage the admins.", usage = "/<command> [add <player> | remove <player> | setrank <player> <rank>]", aliases = "saconfig", rank = Rank.SWING_ADMIN)
public class Command_admin extends SuperCommand
{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String c, String[] args)
    {
        switch (args.length)
        {
            case 2:
            {
                if (args[0].equalsIgnoreCase("add"))
                {
                    if (!isConsole(sender))
                    {
                        sender.sendMessage(onlyConsole);
                        return true;
                    }
                    Player player = Bukkit.getPlayer(args[1]);
                    if (player == null)
                    {
                        sender.sendMessage(playerNotFound);
                        return true;
                    }
                    if (AdminList.isImpostor(player))
                    {
                        action(sender.getName(), "Re-adding " + player.getName() + " to the admin list", ChatColor.RED);
                        AdminList.addIp(player, SUtil.getIp(player));
                        Freeze.setFrozen(player, false);
                        player.setOp(true);
                        player.sendMessage(opped);
                        return true;
                    }
                    if (AdminList.isAdmin(player))
                    {
                        sender.sendMessage(ChatColor.GRAY + "That player is already an admin.");
                        return true;
                    }
                    action(sender.getName(), "Adding " + player.getName() + " to the admin list", ChatColor.RED);
                    AdminList.addAdmin(player);
                    return true;
                }
                if (args[0].equalsIgnoreCase("remove"))
                {
                    if (!isConsole(sender))
                    {
                        sender.sendMessage(onlyConsole);
                        return true;
                    }
                    Player player = Bukkit.getPlayer(args[1]);
                    if (player == null)
                    {
                        sender.sendMessage(playerNotFound);
                        return true;
                    }
                    if (!AdminList.isAdmin(player))
                    {
                        sender.sendMessage(ChatColor.GRAY + "That player is not an admin.");
                        return true;
                    }
                    action(sender.getName(), "Removing " + player.getName() + " from the admin list", ChatColor.RED);
                    AdminList.removeAdmin(player);
                    return true;
                }
            }
            case 3:
            {
                if (args[0].equalsIgnoreCase("setrank"))
                {
                    if (!isConsole(sender))
                    {
                        sender.sendMessage(onlyConsole);
                        return true;
                    }
                    Player player = Bukkit.getPlayer(args[1]);
                    if (player == null)
                    {
                        sender.sendMessage(playerNotFound);
                        return true;
                    }
                    if (!AdminList.isAdmin(player))
                    {
                        sender.sendMessage(ChatColor.GRAY + "That player is not an admin.");
                        return true;
                    }
                    Rank rank = Rank.findRank(args[2]);
                    if (rank == null)
                    {
                        sender.sendMessage(ChatColor.GRAY + "Cannot find that rank.");
                        return true;
                    }
                    if (rank.equals(Rank.CONSOLE))
                    {
                        sender.sendMessage(ChatColor.GRAY + "You cannot set a user to the console rank!");
                        return true;
                    }
                    action(sender.getName(), "Setting " + player.getName() + "'s rank to " + rank.getName(), ChatColor.RED);
                    AdminList.updateRank(player, rank);
                    return true;
                }
            }
        }
        return false;
    }
}
