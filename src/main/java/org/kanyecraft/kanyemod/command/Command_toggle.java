package org.kanyecraft.kanyemod.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.kanyecraft.kanyemod.listener.ItemFun;
import org.kanyecraft.kanyemod.rank.Rank;
import org.kanyecraft.kanyemod.util.KConfig;

@CommandParameters(name = "toggle", description = "Allow or disallow toggleable features.", usage = "/<command> [feature]", rank = Rank.ADMIN)
public class Command_toggle extends KanyeCommand
{
    public boolean onCommand(CommandSender sender, Command cmd, String c, String[] args)
    {
        if (args.length > 1)
        {
            return false;
        }

        if (args.length == 1)
        {
            if (args[0].equalsIgnoreCase("lava"))
            {
                KConfig.setLavaEnabled(!KConfig.isLavaEnabled());
                sender.sendMessage(ChatColor.GRAY + "Lava placement has been " + (KConfig.isLavaEnabled() ? "enabled." : "disabled."));
                return true;
            }
            if (args[0].equalsIgnoreCase("water"))
            {
                KConfig.setWaterEnabled(!KConfig.isWaterEnabled());
                sender.sendMessage(ChatColor.GRAY + "Water placement has been " + (KConfig.isWaterEnabled() ? "enabled." : "disabled."));
                return true;
            }
            if (args[0].equalsIgnoreCase("shoot"))
            {
                ItemFun.allowShoot(!ItemFun.isShootingEnabled());
                sender.sendMessage(ChatColor.GRAY + "Senior Admin shooting has been " + (ItemFun.isShootingEnabled() ? "enabled." : "disabled."));
                return true;
            }
            return false;
        }

        sender.sendMessage(ChatColor.GRAY + "Toggleable features:");
        sender.sendMessage(ChatColor.GRAY + " - lava");
        sender.sendMessage(ChatColor.GRAY + " - water");
        return true;
    }
}
