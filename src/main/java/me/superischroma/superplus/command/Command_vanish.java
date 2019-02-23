package me.superischroma.superplus.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import me.superischroma.superplus.admin.AdminList;
import me.superischroma.superplus.player.PlayerData;
import me.superischroma.superplus.rank.Rank;
import me.superischroma.superplus.rank.RankManager;
import me.superischroma.superplus.util.SLog;
import me.superischroma.superplus.util.SUtil;

@CommandParameters(name = "vanish", description = "Disappear from the game or come back.", usage = "/<command>", aliases = "v,sv", rank = Rank.SWING_ADMIN, source = Source.IN_GAME)
public class Command_vanish extends SuperCommand
{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String c, String[] args)
    {
        if (args.length != 0)
        {
            return false;
        }
        Player player = (Player) sender;
        PlayerData.setVanished(player, !PlayerData.isVanished(player));
        if (!PlayerData.isVanished(player))
        {
            if (AdminList.hasCustomLoginMSG(player))
            {
                SUtil.broadcast(ChatColor.GREEN + player.getName() + " is " + admins.getString(player.getName().toLowerCase() + ".login"));
            }
            else if (AdminList.hasManagerPrefix(player))
            {
                SUtil.broadcast(ChatColor.GREEN + player.getName() + " is " + admins.getString(player.getName().toLowerCase() + ".manager") + " Manager");
            }
            else
            {
                SUtil.broadcast(ChatColor.GREEN + sender.getName() + " is " + RankManager.getDisplay(sender).getLoginMessage());
            }
        }
        SUtil.broadcast(ChatColor.YELLOW + sender.getName() + " " + (PlayerData.isVanished(player) ? "left" : "joined") + " the game");
        SLog.info(sender.getName() + " has " + (PlayerData.isVanished(player) ? "vanished." : "unvanished."));
        sender.sendMessage(ChatColor.GRAY + "You have " + (PlayerData.isVanished(player) ? "vanished." : "unvanished."));
        return true;
    }
}
