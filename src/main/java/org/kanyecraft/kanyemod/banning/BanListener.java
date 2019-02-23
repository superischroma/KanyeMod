package org.kanyecraft.kanyemod.banning;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.kanyecraft.kanyemod.KanyeMod;
import org.kanyecraft.kanyemod.admin.AdminList;
import org.kanyecraft.kanyemod.util.KUtil;

import java.text.SimpleDateFormat;

public class BanListener implements Listener
{
    private KanyeMod plugin;
    public BanListener(KanyeMod plugin)
    {
        this.plugin = plugin;
    }

    private static Bans bans = Bans.getConfig();

    private static PermBans permbans = PermBans.getConfig();

    private static SimpleDateFormat releaseTime = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent e)
    {
        Player player = e.getPlayer();
        if (permbans.getStringList("names").contains(player.getName()) || permbans.getStringList("ips").contains(e.getAddress().getHostAddress().trim()))
        {
            e.disallow(PlayerLoginEvent.Result.KICK_OTHER, ChatColor.RED + "You currently permanently banned from this server.");
            return;
        }
        if (BanList.isBanned(player))
        {
            if (System.currentTimeMillis() >= bans.getLong(player.getName().toLowerCase() + ".length"))
            {
                BanList.removeBan(player);
                return;
            }
            StringBuilder banMsg = new StringBuilder()
                    .append(ChatColor.RED)
                    .append("You are currently banned from this server.")
                    .append("\nBanned by: ")
                    .append(bans.getString(player.getName().toLowerCase() + ".punisher"));
            if (bans.contains(player.getName().toLowerCase() + ".reason"))
            {
                banMsg.append("\nReason: ")
                        .append(bans.getString(player.getName().toLowerCase() + ".reason"));
            }
            banMsg.append("\nRelease: ")
                    .append(releaseTime.format(bans.getLong(player.getName().toLowerCase() + ".length")));
            e.disallow(PlayerLoginEvent.Result.KICK_OTHER, banMsg.toString());
        }
    }
}
