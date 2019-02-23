package me.superischroma.superplus.blocking;

import me.superischroma.superplus.util.SConfig;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import me.superischroma.superplus.SuperPLUS;

public class InteractBlocker implements Listener
{
    private SuperPLUS plugin;
    public InteractBlocker(SuperPLUS plugin)
    {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e)
    {
        switch (e.getAction())
        {
            case LEFT_CLICK_AIR:
            case LEFT_CLICK_BLOCK:
            {
                break;
            }
            case RIGHT_CLICK_AIR:
            case RIGHT_CLICK_BLOCK:
            {
                handleRMB(e);
                break;
            }
        }
    }

    private void handleRMB(PlayerInteractEvent e)
    {
        Player player = e.getPlayer();
        switch (e.getMaterial())
        {
            case LAVA_BUCKET:
            {
                if (SConfig.isLavaEnabled())
                {
                    return;
                }
                e.setCancelled(true);
                player.sendMessage(ChatColor.GRAY + "Lava placement is currently disabled.");
                break;
            }
            case WATER_BUCKET:
            {
                if (SConfig.isWaterEnabled())
                {
                    return;
                }
                e.setCancelled(true);
                player.sendMessage(ChatColor.GRAY + "Water placement is currently disabled.");
                break;
            }
        }
    }
}
