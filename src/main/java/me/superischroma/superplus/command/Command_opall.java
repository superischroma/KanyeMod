package me.superischroma.superplus.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import me.superischroma.superplus.rank.Rank;

@CommandParameters(name = "opall", description = "Op everyone on the server.", usage = "/<command>", rank = Rank.SWING_ADMIN)
public class Command_opall extends SuperCommand
{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String c, String[] args)
    {
        if (args.length != 0)
        {
            return false;
        }
        action(sender.getName(), "Opping everyone on the server");
        for (Player player : Bukkit.getOnlinePlayers())
        {
            player.setOp(true);
            player.sendMessage(opped);
        }
        return true;
    }
}
