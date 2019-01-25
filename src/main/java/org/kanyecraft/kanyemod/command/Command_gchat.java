package org.kanyecraft.kanyemod.command;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.kanyecraft.kanyemod.rank.Rank;

@CommandParameters(name = "gchat", description = "Talk as someone else.", usage = "/<command> <player> <message>", rank = Rank.ADMIN)
public class Command_gchat extends KanyeCommand
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
        String msg = StringUtils.join(args, " ", 1, args.length);
        if (msg.length() == 0)
        {
            return false;
        }
        player.chat(msg);
        return true;
    }
}
