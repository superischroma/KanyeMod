package org.kanyecraft.kanyemod.command;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.kanyecraft.kanyemod.admin.AdminList;
import org.kanyecraft.kanyemod.rank.Rank;
import org.kanyecraft.kanyemod.rank.RankManager;
import org.kanyecraft.kanyemod.util.KConfig;
import org.kanyecraft.kanyemod.util.KLog;
import org.kanyecraft.kanyemod.util.KUtil;

@CommandParameters(name = "adminchat", description = "Talk in AdminChat.", usage = "/<command> <message>", aliases = "ac,o", rank = Rank.ADMIN)
public class Command_adminchat extends KanyeCommand
{
    public boolean onCommand(CommandSender sender, Command cmd, String c, String[] args)
    {
        if (args.length < 1)
        {
            return false;
        }

        String msg = StringUtils.join(args, " ", 0, args.length);

        String adminChatFormat = KConfig.getAdminChatFormat()
                .replace("%name%", sender.getName())
                .replace("%tagcolor%", RankManager.getDisplay(sender).getColor() + "")
                .replace("%tag%", ChatColor.stripColor(RankManager.getDisplay(sender).getTag())
                        .replace("[", "")
                        .replace("]", ""))
                .replace("%message%", msg);
        adminChatFormat = KUtil.colorize(adminChatFormat);

        for (Player player : Bukkit.getOnlinePlayers())
        {
            if (AdminList.isAdmin(player))
            {
                if (AdminList.hasCustomACFormat(player))
                {
                    String customACFormat = admins.getString(player.getName().toLowerCase() + ".acformat")
                            .replace("%name%", sender.getName())
                            .replace("%tagcolor%", RankManager.getDisplay(sender).getColor() + "")
                            .replace("%tag%", ChatColor.stripColor(RankManager.getDisplay(sender).getTag())
                                    .replace("[", "")
                                    .replace("]", ""))
                            .replace("%message%", msg);
                    player.sendMessage(customACFormat);
                }
                else
                {
                    player.sendMessage(adminChatFormat);
                }
            }
        }
        KLog.info(ChatColor.stripColor(adminChatFormat));
        return true;
    }
}
