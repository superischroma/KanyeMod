package me.superischroma.superplus.blocking;

import me.superischroma.superplus.SuperPLUS;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import me.superischroma.superplus.util.SConfig;

public class BlockBlocker implements Listener
{
    private SuperPLUS plugin;
    public BlockBlocker(SuperPLUS plugin)
    {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e)
    {
        Player player = e.getPlayer();
        switch (e.getBlockPlaced().getType())
        {
            case LAVA:
            case STATIONARY_LAVA:
            {
                if (SConfig.isLavaEnabled())
                {
                    return;
                }
                e.setCancelled(true);
                player.sendMessage(ChatColor.GRAY + "Lava placement is currently disabled.");
                break;
            }
            case WATER:
            case STATIONARY_WATER:
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
