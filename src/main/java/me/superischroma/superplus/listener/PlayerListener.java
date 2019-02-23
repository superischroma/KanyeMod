package me.superischroma.superplus.listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import me.superischroma.superplus.SuperPLUS;
import me.superischroma.superplus.admin.Admin;
import me.superischroma.superplus.admin.AdminList;
import me.superischroma.superplus.player.PlayerData;
import me.superischroma.superplus.rank.Rank;
import me.superischroma.superplus.rank.RankManager;
import me.superischroma.superplus.util.SLog;
import me.superischroma.superplus.util.SUtil;

public class PlayerListener implements Listener
{
    private SuperPLUS plugin;
    public PlayerListener(SuperPLUS plugin)
    {
        this.plugin = plugin;
    }

    Admin admins = Admin.getConfig();
    PlayerData players = PlayerData.getConfig();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e)
    {
        Player player = e.getPlayer();
        if (!players.contains(player.getName().toLowerCase()))
        {
            SLog.info("Creating new player data entry for " + player.getName());
            PlayerData.initalizePlayer(player);
        }

        if (PlayerData.isVanished(player))
        {
            e.setJoinMessage(null);
            player.sendMessage(ChatColor.GRAY + "You logged in silently since you were vanished when you were last online.");
            return;
        }

        if (AdminList.isImpostor(player))
        {
            SUtil.broadcast(ChatColor.GREEN + player.getName() + " is " + Rank.IMPOSTOR.getLoginMessage());
            for (Player all : Bukkit.getOnlinePlayers())
            {
                if (AdminList.isAdmin(all))
                {
                    all.sendMessage(ChatColor.RED + "NOTICE: " + player.getName() + " has been marked as an impostor and needs to be verified.");
                }
            }
            player.setOp(false);
            Freeze.setFrozen(player, true);
            return;
        }

        if (AdminList.isAdmin(player))
        {
            if (AdminList.hasCustomLoginMSG(player))
            {
                SUtil.broadcast(ChatColor.GREEN + player.getName() + " is " + admins.getString(player.getName().toLowerCase() + ".login"));
                return;
            }

            if (AdminList.hasManagerPrefix(player))
            {
                SUtil.broadcast(ChatColor.GREEN + player.getName() + " is " + admins.getString(player.getName().toLowerCase() + ".manager") + " Manager");
                return;
            }

            SUtil.broadcast(ChatColor.GREEN + player.getName() + " is " + RankManager.getDisplay(player).getLoginMessage());
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e)
    {
        Player player = e.getPlayer();
        if (PlayerData.isVanished(player))
        {
            e.setQuitMessage(null);
        }
    }

    @EventHandler
    public void onPlayerPreprocess(PlayerCommandPreprocessEvent e)
    {
        Player player = e.getPlayer();
        for (Player all : Bukkit.getOnlinePlayers())
        {
            if (AdminList.isAdmin(player))
            {
                return;
            }

            if (AdminList.isAdmin(all) && AdminList.isCommandSpyEnabled(all))
            {
                all.sendMessage(ChatColor.GRAY + player.getName() + ": " + e.getMessage());
            }
        }
    }
}
