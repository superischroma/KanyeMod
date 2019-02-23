package org.kanyecraft.kanyemod.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.kanyecraft.kanyemod.rank.Rank;

@CommandParameters(name = "deopall", description = "De-op everyone on the server.", usage = "/<command>", rank = Rank.ADMIN)
public class Command_deopall extends KanyeCommand
{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String c, String[] args)
    {
        if (args.length != 0)
        {
            return false;
        }
        action(sender.getName(), "De-opping everyone on the server", ChatColor.RED);
        for (Player player : Bukkit.getOnlinePlayers())
        {
            player.setOp(false);
            player.sendMessage(deopped);
        }
        return true;
    }
}
