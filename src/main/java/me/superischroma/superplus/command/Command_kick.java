package me.superischroma.superplus.command;

import me.superischroma.superplus.punishments.PunishmentList;
import me.superischroma.superplus.punishments.PunishmentType;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_kick extends SuperCommand
{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String c, String[] args)
    {
        if (args.length == 0)
        {
            return false;
        }

        Player player = Bukkit.getPlayer(args[0]);
        if (player == null)
        {
            sender.sendMessage(playerNotFound);
            return true;
        }

        StringBuilder actionMsg = new StringBuilder()
                .append("Kicking ")
                .append(player.getName());
        StringBuilder kickMsg = new StringBuilder()
                .append(ChatColor.RED)
                .append("You have been kicked from this server.")
                .append("\nKicked by: ")
                .append(sender.getName());

        String reason = null;
        if (args.length > 1)
        {
            reason = StringUtils.join(args, " ", 1, args.length);
            actionMsg.append(" | Reason: ")
                    .append(reason);
            kickMsg.append("\nReason: ")
                    .append(reason);
        }

        player.kickPlayer(kickMsg.toString());

        // log kick
        PunishmentList.logPunishment(player, PunishmentType.KICK, sender, reason);
        return true;
    }
}
