package me.superischroma.superplus.listener;

import me.superischroma.superplus.SuperPLUS;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
import java.util.List;

public class MuteManager implements Listener
{
    private SuperPLUS plugin;
    public MuteManager(SuperPLUS plugin)
    {
        this.plugin = plugin;
    }

    private static List<Player> muted = new ArrayList<>();

    public static int getMutedAmount()
    {
        return muted.size();
    }

    public static void setMuted(Player player, boolean mute)
    {
        if (mute)
        {
            muted.add(player);
            return;
        }
        muted.remove(player);
    }

    public static void wipeMutes()
    {
        muted.clear();
    }

    public static boolean isMuted(Player player)
    {
        return muted.contains(player);
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e)
    {
        Player player = e.getPlayer();
        if (isMuted(player))
        {
            e.setCancelled(true);
            player.sendMessage(ChatColor.GRAY + "You are currently muted and cannot chat.");
        }
    }
}
