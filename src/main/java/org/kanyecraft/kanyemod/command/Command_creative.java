package org.kanyecraft.kanyemod.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.kanyecraft.kanyemod.rank.Rank;

@CommandParameters(name = "creative", description = "For people who want to build. -Video", usage = "/<command> [player]", aliases = "gmc", rank = Rank.OP, source = Source.IN_GAME)
public class Command_creative extends KanyeCommand
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
                sender.sendMessage(ChatColor.GRAY + "Use /creative instead.");
                return true;
            }
            player.setGameMode(GameMode.CREATIVE);
            player.sendMessage(ChatColor.GRAY + sender.getName() + " set your gamemode to creative.");
            sender.sendMessage(ChatColor.GRAY + "Set " + player.getName() + "'s gamemode to creative.");
            return true;
        }
        Player player = (Player) sender;
        player.setGameMode(GameMode.CREATIVE);
        sender.sendMessage(ChatColor.GRAY + "Gamemode set to creative.");
        return true;
    }
}
