package org.kanyecraft.kanyemod.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.kanyecraft.kanyemod.rank.Rank;

@CommandParameters(name = "survival", description = "For the normies -Video", usage = "/<command> [player]", aliases = "gms", rank = Rank.OP, source = Source.IN_GAME)
public class Command_survival extends KanyeCommand
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
                sender.sendMessage(ChatColor.GRAY + "Use /survival instead.");
                return true;
            }
            player.setGameMode(GameMode.SURVIVAL);
            player.sendMessage(ChatColor.GRAY + sender.getName() + " set your gamemode to survival.");
            sender.sendMessage(ChatColor.GRAY + "Set " + player.getName() + "'s gamemode to survival.");
            return true;
        }
        Player player = (Player) sender;
        player.setGameMode(GameMode.SURVIVAL);
        sender.sendMessage(ChatColor.GRAY + "Gamemode set to survival.");
        return true;
    }
}
