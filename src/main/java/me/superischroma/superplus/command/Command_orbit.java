package me.superischroma.superplus.command;

import me.superischroma.superplus.listener.Orbiter;
import me.superischroma.superplus.rank.Rank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandParameters(name = "orbit", description = "Up and up and up...", usage = "/<command> <player> [stop]", rank = Rank.SWING_ADMIN)
public class Command_orbit extends SuperCommand
{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String c, String[] args)
    {
        Player player = Bukkit.getPlayer(args[0]);
        if (player == null)
        {
            sender.sendMessage(playerNotFound);
            return true;
        }
        if (args.length == 2)
        {
            if (args[1].equalsIgnoreCase("stop"))
            {
                if (!Orbiter.isOrbiting(player))
                {
                    sender.sendMessage(ChatColor.GRAY + "That player is not being orbited.");
                    return true;
                }
                Orbiter.setOrbiting(player, false);
                action(sender.getName(), "Stopped orbiting " + player.getName());
                return true;
            }
        }
        if (Orbiter.isOrbiting(player))
        {
            sender.sendMessage(ChatColor.GRAY + "That player is already being orbited.");
            return true;
        }
        Orbiter.setOrbiting(player, true);
        action(sender.getName(), "Orbiting " + player.getName());
        return true;
    }
}
