package me.superischroma.superplus.command;

import me.superischroma.superplus.listener.BlockFun;
import me.superischroma.superplus.listener.ItemFun;
import me.superischroma.superplus.util.SConfig;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import me.superischroma.superplus.rank.Rank;

@CommandParameters(name = "toggle", description = "Allow or disallow toggleable features.", usage = "/<command> [feature]", rank = Rank.SWING_ADMIN)
public class Command_toggle extends SuperCommand
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
                SConfig.setLavaEnabled(!SConfig.isLavaEnabled());
                sender.sendMessage(ChatColor.GRAY + "Lava placement has been " + (SConfig.isLavaEnabled() ? "enabled." : "disabled."));
                return true;
            }
            if (args[0].equalsIgnoreCase("water"))
            {
                SConfig.setWaterEnabled(!SConfig.isWaterEnabled());
                sender.sendMessage(ChatColor.GRAY + "Water placement has been " + (SConfig.isWaterEnabled() ? "enabled." : "disabled."));
                return true;
            }
            if (args[0].equalsIgnoreCase("itemfun"))
            {
                ItemFun.toggleItemFun(!ItemFun.isItemFunEnabled());
                sender.sendMessage(ChatColor.GRAY + "Senior Admin item fun has been " + (ItemFun.isItemFunEnabled() ? "enabled." : "disabled."));
                return true;
            }
            if (args[0].equalsIgnoreCase("blockfun"))
            {
                BlockFun.toggleBlockFun(!BlockFun.isBlockFunEnabled());
                sender.sendMessage(ChatColor.GRAY + "Block fun has been " + (BlockFun.isBlockFunEnabled() ? "enabled." : "disabled."));
                return true;
            }
            return false;
        }

        sender.sendMessage(ChatColor.GRAY + "Toggleable features:");
        sender.sendMessage(ChatColor.GRAY + " - lava");
        sender.sendMessage(ChatColor.GRAY + " - water");
        sender.sendMessage(ChatColor.GRAY + " - itemfun");
        sender.sendMessage(ChatColor.GRAY + " - blockfun");
        return true;
    }
}
