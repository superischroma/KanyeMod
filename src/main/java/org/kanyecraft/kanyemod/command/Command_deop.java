package org.kanyecraft.kanyemod.command;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.kanyecraft.kanyemod.rank.Rank;

@CommandParameters(name = "deop", description = "De-op a user on the server.", usage = "/<command> <player>", rank = Rank.ADMIN)
public class Command_deop extends KanyeCommand
{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String c, String[] args)
    {
        if (args.length != 1)
        {
            return false;
        }
        Player player = Bukkit.getPlayer(args[0]);
        if (player == null)
        {
            sender.sendMessage(playerNotFound);
            return true;
        }
        action(sender.getName(), "De-opping " + player.getName(), ChatColor.RED);
        player.setOp(false);
        player.sendMessage(deopped);
        return true;
    }
}
