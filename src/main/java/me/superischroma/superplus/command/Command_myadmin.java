package me.superischroma.superplus.command;

import me.superischroma.superplus.util.SConfig;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import me.superischroma.superplus.admin.AdminList;
import me.superischroma.superplus.rank.Rank;
import me.superischroma.superplus.util.SUtil;

@CommandParameters(name = "myadmin", description = "Manage your admin profile.", usage = "/<command> [setlogin <login> | clearlogin | setacformat <format> | clearacformat | setmanagerprefix <prefix> | clearmanagerprefix]", rank = Rank.SWING_ADMIN, source = Source.IN_GAME)
public class Command_myadmin extends SuperCommand
{
    public boolean onCommand(CommandSender sender, Command cmd, String c, String[] args)
    {
        if (args.length < 1)
        {
            return false;
        }
        if (args.length > 1)
        {
            if (args[0].equalsIgnoreCase("setlogin"))
            {
                String login = StringUtils.join(args, " ", 1, args.length);
                login = SUtil.colorize(login);
                if (login.length() == 0)
                {
                    return false;
                }
                admins.set(sender.getName().toLowerCase() + ".login", login);
                admins.save();
                sender.sendMessage(ChatColor.GRAY + "Login message set.");
                sender.sendMessage(ChatColor.GRAY + "Preview: " + ChatColor.GREEN + sender.getName() + " is " + login);
                return true;
            }
            if (args[0].equalsIgnoreCase("setacformat"))
            {
                String acformat = StringUtils.join(args, " ", 1, args.length);
                acformat = SUtil.colorize(acformat);
                if (acformat.length() == 0)
                {
                    return false;
                }
                admins.set(sender.getName().toLowerCase() + ".acformat", acformat);
                admins.save();
                sender.sendMessage(ChatColor.GRAY + "Custom AdminChat format set.");
                String preview = acformat
                        .replace("%name%", "Admin")
                        .replace("%tagcolor%", Rank.SWING_ADMIN.getColor() + "")
                        .replace("%tag%", ChatColor.DARK_AQUA + "Admin")
                        .replace("%message%", "The quick brown fox jumps over the lazy dog.");
                sender.sendMessage(ChatColor.GRAY + "Preview: " + preview);
                return true;
            }
            if (args[0].equalsIgnoreCase("setmanagerprefix"))
            {
                if (!SConfig.getManagerList().contains(sender.getName()))
                {
                    sender.sendMessage(ChatColor.GRAY + "You are not a manager.");
                    return true;
                }
                if (AdminList.hasCustomLoginMSG((Player) sender))
                {
                    sender.sendMessage(ChatColor.GRAY + "You have a custom login message and therefore cannot edit your manager prefix.");
                    return true;
                }
                String managerPrefix = StringUtils.join(args, " ", 1, args.length);
                if (managerPrefix.length() == 0)
                {
                    return false;
                }
                admins.set(sender.getName().toLowerCase() + ".manager", managerPrefix);
                admins.save();
                sender.sendMessage(ChatColor.GRAY + "Manager prefix set.");
                sender.sendMessage(ChatColor.GRAY + "Preview: " + ChatColor.GREEN + sender.getName() + " is " + managerPrefix + " Manager");
                return true;
            }
        }
        if (args.length == 1)
        {
            if (args[0].equalsIgnoreCase("clearacformat"))
            {
                if (!AdminList.hasCustomACFormat((Player) sender))
                {
                    sender.sendMessage(ChatColor.GRAY + "You don't have a custom AdminChat format.");
                    return true;
                }
                admins.set(sender.getName().toLowerCase() + ".acformat", null);
                admins.save();
                sender.sendMessage(ChatColor.GRAY + "Custom AdminChat format cleared.");
                return true;
            }
            if (args[0].equalsIgnoreCase("clearlogin"))
            {
                if (!AdminList.hasCustomLoginMSG((Player) sender))
                {
                    sender.sendMessage(ChatColor.GRAY + "You don't have a custom login message.");
                    return true;
                }
                admins.set(sender.getName().toLowerCase() + ".login", null);
                admins.save();
                sender.sendMessage(ChatColor.GRAY + "Login message cleared.");
                return true;
            }
            if (args[0].equalsIgnoreCase("clearmanagerprefix"))
            {
                if (!SConfig.getManagerList().contains(sender.getName()))
                {
                    sender.sendMessage(ChatColor.GRAY + "You are not a manager.");
                    return true;
                }
                if (AdminList.hasCustomLoginMSG((Player) sender))
                {
                    sender.sendMessage(ChatColor.GRAY + "You have a custom login message and therefore cannot edit your manager prefix.");
                    return true;
                }
                if (!AdminList.hasManagerPrefix((Player) sender))
                {
                    sender.sendMessage(ChatColor.GRAY + "You don't have a manager prefix.");
                    return true;
                }
                admins.set(sender.getName().toLowerCase() + ".manager", null);
                admins.save();
                sender.sendMessage(ChatColor.GRAY + "Manager prefix cleared.");
                return true;
            }
        }
        return false;
    }
}
