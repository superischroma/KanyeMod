package org.kanyecraft.kanyemod.listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.server.ServerCommandEvent;
import org.kanyecraft.kanyemod.KanyeMod;
import org.kanyecraft.kanyemod.admin.Admin;
import org.kanyecraft.kanyemod.admin.AdminList;
import org.kanyecraft.kanyemod.rank.Rank;
import org.kanyecraft.kanyemod.rank.RankManager;
import org.kanyecraft.kanyemod.util.KConfig;
import org.kanyecraft.kanyemod.util.KUtil;

public class PlayerListener implements Listener
{
    private KanyeMod plugin;
    public PlayerListener(KanyeMod plugin)
    {
        this.plugin = plugin;
    }

    Admin admins = Admin.getConfig();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e)
    {
        Player player = e.getPlayer();
        if (AdminList.isAdmin(player))
        {
            if (AdminList.isImpostor(player))
            {
                KUtil.broadcast(ChatColor.GREEN + player.getName() + " is " + Rank.IMPOSTOR.getLoginMessage());
                for (Player all : Bukkit.getOnlinePlayers())
                {
                    if (AdminList.isAdmin(all))
                    {
                        all.sendMessage(ChatColor.RED + "NOTICE: " + player.getName() + " has been marked as an impostor and needs to be verified.");
                    }
                }
                player.setOp(false);
                FreezeListener.freeze(player);
                return;
            }

            if (AdminList.hasCustomLoginMSG(player))
            {
                KUtil.broadcast(ChatColor.GREEN + player.getName() + " is " + admins.getString(player.getName().toLowerCase() + ".login"));
                return;
            }

            KUtil.broadcast(ChatColor.GREEN + player.getName() + " is " + RankManager.getDisplay(player).getLoginMessage());
        }
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e)
    {
        Player player = e.getPlayer();
        String chatFormat = "&r" + KConfig.getChatFormat()
                .replace("%display%", player.getDisplayName())
                .replace("%message%", e.getMessage());
        chatFormat = KUtil.colorize(chatFormat);
        if (AdminList.isAdmin(player))
        {
            e.setFormat(RankManager.getDisplay(player).getTag() + " " + chatFormat);
            return;
        }
        e.setFormat(chatFormat);
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
