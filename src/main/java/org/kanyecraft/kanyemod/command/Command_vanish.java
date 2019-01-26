package org.kanyecraft.kanyemod.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.kanyecraft.kanyemod.admin.AdminList;
import org.kanyecraft.kanyemod.player.PlayerData;
import org.kanyecraft.kanyemod.rank.Rank;
import org.kanyecraft.kanyemod.rank.RankManager;
import org.kanyecraft.kanyemod.util.KLog;
import org.kanyecraft.kanyemod.util.KUtil;

@CommandParameters(name = "vanish", description = "Disappear from the game or come back.", usage = "/<command>", aliases = "v,kv", rank = Rank.ADMIN, source = Source.IN_GAME)
public class Command_vanish extends KanyeCommand
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
                KUtil.broadcast(ChatColor.GREEN + player.getName() + " is " + admins.getString(player.getName().toLowerCase() + ".login"));
            }
            else
            {
                KUtil.broadcast(ChatColor.GREEN + sender.getName() + " is " + RankManager.getDisplay(sender).getLoginMessage());
            }
        }
        KUtil.broadcast(ChatColor.YELLOW + sender.getName() + " " + (PlayerData.isVanished(player) ? "left" : "joined") + " the game");
        KLog.info(sender.getName() + " has " + (PlayerData.isVanished(player) ? "vanished." : "unvanished."));
        sender.sendMessage(ChatColor.GRAY + "You have " + (PlayerData.isVanished(player) ? "vanished." : "unvanished."));
        return true;
    }
}
