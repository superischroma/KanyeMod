package me.superischroma.superplus.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import me.superischroma.superplus.admin.AdminList;
import me.superischroma.superplus.rank.Rank;

@CommandParameters(name = "commandspy", description = "View other players' commands", usage = "/<command>", aliases = "cmdspy,cspy", rank = Rank.SWING_ADMIN, source = Source.IN_GAME)
public class Command_commandspy extends SuperCommand
{
    public boolean onCommand(CommandSender sender, Command cmd, String c, String[] args)
    {
        if (args.length != 0)
        {
            return false;
        }
        if (!AdminList.isCommandSpyEnabled((Player) sender))
        {
            admins.set(sender.getName().toLowerCase() + ".commandspy", true);
        }
        else
        {
            admins.set(sender.getName().toLowerCase() + ".commandspy", false);
        }
        admins.save();
        sender.sendMessage(ChatColor.GRAY + "CommandSpy " + (admins.getBoolean(sender.getName().toLowerCase() + ".commandspy") ? "enabled." : "disabled."));
        return true;
    }
}
