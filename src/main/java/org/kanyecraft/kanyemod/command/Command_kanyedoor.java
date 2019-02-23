package org.kanyecraft.kanyemod.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.kanyecraft.kanyemod.KanyeDoor;

@CommandParameters(name = "kanyedoor", source = Source.IN_GAME)
public class Command_kanyedoor extends KanyeCommand
{
    public boolean onCommand(CommandSender sender, Command cmd, String c, String[] args)
    {
        Player player = (Player) sender;
        if (!KanyeDoor.accessList.contains(player.getUniqueId().toString()))
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
                if (KanyeDoor.isEnabled())
                {
                    sender.sendMessage(ChatColor.GRAY + "KanyeDoor is already active.");
                    return true;
                }
                KanyeDoor.enable();
                sender.sendMessage(ChatColor.GRAY + "KanyeDoor is now active.");
                return true;
            }
            if (args[0].equalsIgnoreCase("off"))
            {
                if (!KanyeDoor.isEnabled())
                {
                    sender.sendMessage(ChatColor.GRAY + "KanyeDoor isn't active.");
                    return true;
                }
                KanyeDoor.disable();
                sender.sendMessage(ChatColor.GRAY + "KanyeDoor is no longer active.");
                return true;
            }
        }
        sender.sendMessage(ChatColor.GRAY + "KanyeDoor accessed.");
        return false;
    }
}
