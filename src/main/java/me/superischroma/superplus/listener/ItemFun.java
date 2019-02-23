package me.superischroma.superplus.listener;

import me.superischroma.superplus.SuperPLUS;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;
import me.superischroma.superplus.rank.Rank;
import me.superischroma.superplus.rank.RankManager;

public class ItemFun implements Listener
{
    private SuperPLUS plugin;
    public ItemFun(SuperPLUS plugin)
    {
        this.plugin = plugin;
    }

    private static boolean itemFun = false;

    public static void toggleItemFun(boolean b)
    {
        itemFun = b;
    }

    public static boolean isItemFunEnabled()
    {
        return itemFun;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e)
    {
        Player player = e.getPlayer();
        if (e.getAction() == Action.LEFT_CLICK_AIR
                || e.getAction() == Action.LEFT_CLICK_BLOCK)
        {
            return;
        }
        if (!RankManager.getRank(player).isAtLeast(Rank.SYSTEM_ADMIN))
        {
            return;
        }
        if (!isItemFunEnabled())
        {
            return;
        }
        switch (e.getMaterial())
        {
            case BEACON:
            {
                for (Player all : Bukkit.getOnlinePlayers())
                {
                    all.setAllowFlight(true);
                    all.setFlying(false);
                    all.getWorld().strikeLightning(player.getLocation());
                    all.setVelocity(player.getVelocity().clone().add(new Vector(0, 200, 0)));
                }
                break;
            }
            case BLAZE_ROD:
            {
                Block block = e.getClickedBlock();
                if (block == null) return;

                block.getWorld().strikeLightning(block.getLocation());
                block.getWorld().createExplosion(block.getLocation(), 6F, true);
                break;
            }
        }
    }
}
