package me.superischroma.superplus.listener;

import me.superischroma.superplus.SuperPLUS;
import me.superischroma.superplus.util.SConfig;
import me.superischroma.superplus.util.SUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class ServerPing implements Listener
{
    private SuperPLUS plugin;
    public ServerPing(SuperPLUS plugin)
    {
        this.plugin = plugin;
    }

    @EventHandler
    public void onServerPing(ServerListPingEvent e)
    {
        if (AdminMode.isAdminModeEnabled())
        {
            e.setMotd(ChatColor.RED + "AdminMode is enabled.");
            return;
        }
        e.setMotd(SUtil.colorize(SConfig.getServerMOTD()));
    }
}
