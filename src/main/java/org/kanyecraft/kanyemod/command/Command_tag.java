package org.kanyecraft.kanyemod.command;

import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.kanyecraft.kanyemod.player.PlayerData;
import org.kanyecraft.kanyemod.rank.Rank;
import org.kanyecraft.kanyemod.util.KUtil;

@CommandParameters(name = "tag", description = "Append a prefix to yourself when you chat.", usage = "/<command> [set <tag> | off]", rank = Rank.OP, source = Source.IN_GAME)
public class Command_tag extends KanyeCommand
{
    public boolean onCommand(CommandSender sender, Command cmd, String c, String[] args)
    {
        Player player = (Player) sender;
        if (args.length > 1)
        {
            if (args[0].equalsIgnoreCase("set"))
            {
                String tag = StringUtils.join(args, " ", 1, args.length);
                tag = KUtil.colorize(tag);
                if (tag.length() == 0)
                {
                    return false;
                }
                PlayerData.setTag(player, tag);
                sender.sendMessage(ChatColor.GRAY + "Tag set to \"" + tag + ChatColor.GRAY + "\"");
                return true;
            }
        }
        if (args.length == 1)
        {
            if (args[0].equalsIgnoreCase("off"))
            {
                if (!PlayerData.hasTag(player))
                {
                    sender.sendMessage(ChatColor.GRAY + "You do not have a tag.");
                    return true;
                }
                PlayerData.clearTag(player);
                sender.sendMessage(ChatColor.GRAY + "Tag cleared.");
                return true;
            }
        }
        if (!PlayerData.hasTag(player))
        {
            sender.sendMessage(ChatColor.GRAY + "You do not have a tag.");
            return true;
        }
        sender.sendMessage(ChatColor.GRAY + "Your current tag: " + KUtil.colorize(players.getString(sender.getName().toLowerCase() + ".tag")));
        return true;
    }
}
