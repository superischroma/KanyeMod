package org.kanyecraft.kanyemod.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.kanyecraft.kanyemod.rank.Rank;

@CommandParameters(name = "spectator", description = "For people who want to move through everything. -Super", usage = "/<command> [player]", aliases = "gmsp", rank = Rank.ADMIN, source = Source.IN_GAME)
public class Command_spectator extends KanyeCommand
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
                sender.sendMessage(ChatColor.GRAY + "Use /spectator instead.");
                return true;
            }
            player.setGameMode(GameMode.SPECTATOR);
            player.sendMessage(ChatColor.GRAY + sender.getName() + " set your gamemode to spectator.");
            sender.sendMessage(ChatColor.GRAY + "Set " + player.getName() + "'s gamemode to spectator.");
            return true;
        }
        Player player = (Player) sender;
        player.setGameMode(GameMode.SPECTATOR);
        sender.sendMessage(ChatColor.GRAY + "Gamemode set to spectator.");
        return true;
    }
}
