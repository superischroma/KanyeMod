package org.kanyecraft.kanyemod.blocking;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.kanyecraft.kanyemod.KanyeMod;
import org.kanyecraft.kanyemod.util.KConfig;

public class BlockBlocker implements Listener
{
    private KanyeMod plugin;
    public BlockBlocker(KanyeMod plugin)
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
                if (KConfig.isLavaEnabled())
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
                if (KConfig.isWaterEnabled())
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
