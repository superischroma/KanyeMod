package org.kanyecraft.kanyemod.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.kanyecraft.kanyemod.banning.BanList;
import org.kanyecraft.kanyemod.rank.Rank;

@CommandParameters(name = "unban", description = "Revoke a ban.", usage = "/<command> <player>", rank = Rank.ADMIN)
public class Command_unban extends KanyeCommand
{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String c, String[] args)
    {
        if (args.length == 0)
        {
            return false;
        }

        if (args.length > 1)
        {
            return false;
        }
        OfflinePlayer player = Bukkit.getOfflinePlayer(args[0]);
        if (!BanList.isBanned(player))
        {
            sender.sendMessage(ChatColor.GRAY + "That player is not banned.");
            return true;
        }
        action(sender.getName(), "Unbanning " + player.getName(), ChatColor.RED);
        BanList.removeBan(player);
        return true;
    }
}
