package me.superischroma.superplus.listener;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import me.superischroma.superplus.SuperPLUS;
import me.superischroma.superplus.admin.Admin;
import me.superischroma.superplus.admin.AdminList;
import me.superischroma.superplus.player.PlayerData;
import me.superischroma.superplus.rank.RankManager;
import me.superischroma.superplus.util.SConfig;
import me.superischroma.superplus.util.SUtil;

public class ChatManager implements Listener
{
    private SuperPLUS plugin;
    public ChatManager(SuperPLUS plugin)
    {
        this.plugin = plugin;
    }

    private static Admin admins = Admin.getConfig();

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e)
    {
        Player player = e.getPlayer();
        String chatFormat = "&r" + SConfig.getChatFormat()
                .replace("%display%", player.getDisplayName())
                .replace("%message%", e.getMessage());
        chatFormat = SUtil.colorize(chatFormat);
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
