package org.kanyecraft.kanyemod.command;

import org.apache.commons.lang.StringUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.kanyecraft.kanyemod.rank.Rank;
import org.kanyecraft.kanyemod.util.KConfig;
import org.kanyecraft.kanyemod.util.KUtil;

@CommandParameters(name = "say", description = "Broadcast a message to the entire server.", usage = "/<command> <message>", rank = Rank.ADMIN)
public class Command_say extends KanyeCommand
{
    public boolean onCommand(CommandSender sender, Command cmd, String c, String[] args)
    {
        if (args.length < 1)
        {
            return false;
        }
        String rawMessage = StringUtils.join(args, " ", 0, args.length);
        String message = KConfig.getSayFormat()
                .replace("%name%", sender.getName())
                .replace("%message%", rawMessage);
        message = KUtil.colorize(message);
        KUtil.broadcast(message);
        return true;
    }
}
