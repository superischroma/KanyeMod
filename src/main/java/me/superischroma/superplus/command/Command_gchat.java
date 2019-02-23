package me.superischroma.superplus.command;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import me.superischroma.superplus.rank.Rank;

@CommandParameters(name = "gchat", description = "Talk as someone else.", usage = "/<command> <player> <message>", rank = Rank.SWING_ADMIN)
public class Command_gchat extends SuperCommand
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
        sender.sendMessage(ChatColor.GRAY + "Sent the following message as " + player.getName() + ": " + msg);
        return true;
    }
}
