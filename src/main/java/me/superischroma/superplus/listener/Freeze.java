package me.superischroma.superplus.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import me.superischroma.superplus.SuperPLUS;

import java.util.ArrayList;
import java.util.List;

public class Freeze implements Listener
{
    private SuperPLUS plugin;
    public Freeze(SuperPLUS plugin)
    {
        this.plugin = plugin;
    }

    public static List<Player> FROZEN = new ArrayList<Player>();

    public static boolean isFrozen(Player player)
    {
        return FROZEN.contains(player);
    }

    public static void setFrozen(Player player, boolean frozen)
    {
        if (frozen)
        {
            FROZEN.add(player);
            return;
        }
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
