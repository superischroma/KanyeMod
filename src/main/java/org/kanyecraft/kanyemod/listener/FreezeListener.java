package org.kanyecraft.kanyemod.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.kanyecraft.kanyemod.KanyeMod;

import java.util.ArrayList;
import java.util.List;

public class FreezeListener implements Listener
{
    private KanyeMod plugin;
    public FreezeListener(KanyeMod plugin)
    {
        this.plugin = plugin;
    }

    public static List<Player> FROZEN = new ArrayList<Player>();

    public static boolean isFrozen(Player player)
    {
        return FROZEN.contains(player);
    }

    public static void freeze(Player player)
    {
        FROZEN.add(player);
    }

    public static void unfreeze(Player player)
    {
        FROZEN.remove(player);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e)
    {
        Player player = e.getPlayer();
        if (isFrozen(player))
        {
            player.teleport(player);
        }
    }
}
