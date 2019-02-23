package me.superischroma.superplus.command;

import org.apache.commons.lang.StringUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import me.superischroma.superplus.rank.Rank;
import me.superischroma.superplus.util.SConfig;
import me.superischroma.superplus.util.SUtil;

@CommandParameters(name = "say", description = "Broadcast a message to the entire server.", usage = "/<command> <message>", rank = Rank.SWING_ADMIN)
public class Command_say extends SuperCommand
{
    public boolean onCommand(CommandSender sender, Command cmd, String c, String[] args)
    {
        if (args.length < 1)
        {
            return false;
        }
        String rawMessage = StringUtils.join(args, " ", 0, args.length);
        String message = SConfig.getSayFormat()
                .replace("%name%", sender.getName())
                .replace("%message%", rawMessage);
        message = SUtil.colorize(message);
        SUtil.broadcast(message);
        return true;
    }
}
