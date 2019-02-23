package org.kanyecraft.kanyemod.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.kanyecraft.kanyemod.rank.Rank;

@CommandParameters(name = "opall", description = "Op everyone on the server.", usage = "/<command>", rank = Rank.ADMIN)
public class Command_opall extends KanyeCommand
{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String c, String[] args)
    {
        if (args.length != 0)
        {
            return false;
        }
        action(sender.getName(), "Opping everyone on the server");
        for (Player player : Bukkit.getOnlinePlayers())
        {
            player.setOp(true);
            player.sendMessage(opped);
        }
        return true;
    }
}
