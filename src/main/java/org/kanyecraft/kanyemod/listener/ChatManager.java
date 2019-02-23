package org.kanyecraft.kanyemod.listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.kanyecraft.kanyemod.KanyeMod;
import org.kanyecraft.kanyemod.admin.Admin;
import org.kanyecraft.kanyemod.admin.AdminList;
import org.kanyecraft.kanyemod.player.PlayerData;
import org.kanyecraft.kanyemod.rank.RankManager;
import org.kanyecraft.kanyemod.util.KConfig;
import org.kanyecraft.kanyemod.util.KUtil;

public class ChatManager implements Listener
{
    private KanyeMod plugin;
    public ChatManager(KanyeMod plugin)
    {
        this.plugin = plugin;
    }

    private static Admin admins = Admin.getConfig();

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e)
    {
        Player player = e.getPlayer();
        String chatFormat = "&r" + KConfig.getChatFormat()
                .replace("%display%", player.getDisplayName())
                .replace("%message%", e.getMessage());
        chatFormat = KUtil.colorize(chatFormat);
        if (PlayerData.hasTag(player))
        {
            e.setFormat(PlayerData.getTag(player) + " " + chatFormat);
            return;
        }
        if (AdminList.isAdmin(player))
        {
            e.setFormat(RankManager.getDisplay(player).getTag() + " " + chatFormat);
            return;
        }
        e.setFormat(chatFormat);

        String message = e.getMessage();

        if (message.length() >= 256)
        {
            e.setCancelled(true);
            player.sendMessage(ChatColor.GRAY + "That message was too long to send!");
        }

        e.setMessage(message);
    }
}
