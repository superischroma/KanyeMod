package org.kanyecraft.kanyemod.listener;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import org.kanyecraft.kanyemod.KanyeMod;
import org.kanyecraft.kanyemod.util.KConfig;
import org.kanyecraft.kanyemod.util.KUtil;

public class ServerPing implements Listener
{
    private KanyeMod plugin;
    public ServerPing(KanyeMod plugin)
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
        e.setMotd(KUtil.colorize(KConfig.getServerMOTD()));
    }
}
