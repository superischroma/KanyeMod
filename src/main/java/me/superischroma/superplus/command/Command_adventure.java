package me.superischroma.superplus.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import me.superischroma.superplus.rank.Rank;

@CommandParameters(name = "adventure", description = "For those feeling like they don't want to touch anything -Video", usage = "/<command> [player]", aliases = "gma", rank = Rank.OP, source = Source.IN_GAME)
public class Command_adventure extends SuperCommand
{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String c, String[] args)
    {
        if (args.length > 1)
        {
            return false;
        }
        if (args.length == 1)
        {
            Player player = Bukkit.getPlayer(args[0]);
            if (player == null)
            {
                sender.sendMessage(playerNotFound);
                return true;
            }
            if (player == sender)
            {
                sender.sendMessage(ChatColor.GRAY + "Use /adventure instead.");
                return true;
            }
            player.setGameMode(GameMode.ADVENTURE);
            player.sendMessage(ChatColor.GRAY + sender.getName() + " set your gamemode to adventure.");
            sender.sendMessage(ChatColor.GRAY + "Set " + player.getName() + "'s gamemode to adventure.");
            return true;
        }
        Player player = (Player) sender;
        player.setGameMode(GameMode.ADVENTURE);
        sender.sendMessage(ChatColor.GRAY + "Gamemode set to adventure.");
        return true;
    }
}
