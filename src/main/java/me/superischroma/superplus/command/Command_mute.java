package me.superischroma.superplus.command;

import me.superischroma.superplus.listener.MuteManager;
import me.superischroma.superplus.punishments.PunishmentList;
import me.superischroma.superplus.punishments.PunishmentType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import me.superischroma.superplus.rank.Rank;

@CommandParameters(name = "mute", description = "Mute, unmute, or purge all mutes.", usage = "/<command> [", aliases = "v,kv", rank = Rank.SWING_ADMIN, source = Source.IN_GAME)
public class Command_mute extends SuperCommand
{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String c, String[] args)
    {
        if (args.length != 1)
        {
            return false;
        }
        if (args[0].equalsIgnoreCase("purge"))
        {
            int muted = MuteManager.getMutedAmount();
            MuteManager.wipeMutes();
            action(sender.getName(), "Unmuting all players", ChatColor.RED);
            sender.sendMessage(ChatColor.GRAY + "Unmuted " + muted + " players.");
            return true;
        }
        Player player = Bukkit.getPlayer(args[0]);
        MuteManager.setMuted(player, !MuteManager.isMuted(player));
        if (MuteManager.isMuted(player))
        {
            // log mute
            PunishmentList.logPunishment(player, PunishmentType.MUTE, sender, null);
        }
        action(sender.getName(), (MuteManager.isMuted(player) ? "Muting " : "Unmuting ") + player.getName(), ChatColor.RED);
        return true;
    }
}
