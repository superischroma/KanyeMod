package me.superischroma.superplus.command;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import me.superischroma.superplus.rank.Rank;

@CommandParameters(name = "op", description = "Op a user on the server.", usage = "/<command> <player>", rank = Rank.OP)
public class Command_op extends SuperCommand
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
        action(sender.getName(), "Opping " + player.getName());
        player.setOp(true);
        player.sendMessage(opped);
        return true;
    }
}
