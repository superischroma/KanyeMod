package org.kanyecraft.kanyemod.listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;
import org.kanyecraft.kanyemod.KanyeMod;
import org.kanyecraft.kanyemod.rank.Rank;
import org.kanyecraft.kanyemod.rank.RankManager;

public class ItemFun implements Listener
{
    private KanyeMod plugin;
    public ItemFun(KanyeMod plugin)
    {
        this.plugin = plugin;
    }

    private static boolean shoot = false;

    public static void allowShoot(boolean b)
    {
        shoot = b;
    }

    public static boolean isShootingEnabled()
    {
        return shoot;
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
        if (!RankManager.getRank(player).isAtLeast(Rank.SENIOR_ADMIN))
        {
            return;
        }
        switch (e.getMaterial())
        {
            case FIREWORK:
            {
                if (!isShootingEnabled())
                {
                    return;
                }
                player.setAllowFlight(true);
                player.setFlying(false);
                player.getWorld().createExplosion(player.getLocation(), 0.5F);
                player.setVelocity(player.getVelocity().clone().add(new Vector(0, 20, 0)));
                break;
            }
        }
    }
}
