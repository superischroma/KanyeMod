package org.kanyecraft.kanyemod.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.kanyecraft.kanyemod.listener.AdminMode;
import org.kanyecraft.kanyemod.rank.Rank;

@CommandParameters(name = "adminmode", description = "Toggle AdminMode on and off.", usage = "/<command> [on | off]", rank = Rank.SENIOR_ADMIN)
public class Command_adminmode extends KanyeCommand
{
    public boolean onCommand(CommandSender sender, Command cmd, String c, String[] args)
    {
        if (args.length > 1)
        {
            return false;
        }
        if (args.length == 1)
        {
            if (args[0].equalsIgnoreCase("on"))
            {
                action(sender.getName(), "Enabling AdminMode", ChatColor.RED);
                AdminMode.enable();
                return true;
            }
            if (args[0].equalsIgnoreCase("off"))
            {
                action(sender.getName(), "Disabling AdminMode", ChatColor.RED);
                AdminMode.disable();
                return true;
            }
            return false;
        }
        sender.sendMessage(ChatColor.GRAY + "AdminMode is currently " + (AdminMode.isAdminModeEnabled() ? "enabled." : "disabled."));
        return true;
    }
}
