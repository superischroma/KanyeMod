package org.kanyecraft.kanyemod.command;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.kanyecraft.kanyemod.rank.Rank;

@CommandParameters(name = "gcmd", description = "Send a command as someone else.", usage = "/<command> <player> <command>", rank = Rank.ADMIN)
public class Command_gcmd extends KanyeCommand
{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String c, String[] args)
    {
        if (args.length < 2)
        {
            return false;
        }
        Player player = Bukkit.getPlayer(args[0]);
        if (player == null)
        {
            sender.sendMessage(playerNotFound);
            return true;
        }
        String command = StringUtils.join(args, " ", 1, args.length);
        if (command.length() == 0)
        {
            return false;
        }
        Bukkit.getServer().dispatchCommand(player, command);
        sender.sendMessage(ChatColor.GRAY + "Ran the following command as " + player.getName() + ": /" + command);
        return true;
    }
}
