package me.superischroma.superplus.command;

import me.superischroma.superplus.util.SConfig;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import me.superischroma.superplus.admin.AdminList;
import me.superischroma.superplus.rank.Rank;
import me.superischroma.superplus.rank.RankManager;
import me.superischroma.superplus.util.SLog;
import me.superischroma.superplus.util.SUtil;

@CommandParameters(name = "adminchat", description = "Talk in AdminChat.", usage = "/<command> <message>", aliases = "ac,o", rank = Rank.SWING_ADMIN)
public class Command_adminchat extends SuperCommand
{
    public boolean onCommand(CommandSender sender, Command cmd, String c, String[] args)
    {
        if (args.length < 1)
        {
            return false;
        }

        String msg = StringUtils.join(args, " ", 0, args.length);

        String adminChatFormat = SConfig.getAdminChatFormat()
                .replace("%name%", sender.getName())
                .replace("%tagcolor%", RankManager.getDisplay(sender).getColor() + "")
                .replace("%tag%", ChatColor.stripColor(RankManager.getDisplay(sender).getTag())
                        .replace("[", "")
                        .replace("]", ""))
                .replace("%message%", msg);
        adminChatFormat = SUtil.colorize(adminChatFormat);

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
        SLog.info(ChatColor.stripColor(adminChatFormat));
        return true;
    }
}
