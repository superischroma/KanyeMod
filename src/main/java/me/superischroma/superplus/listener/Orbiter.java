package me.superischroma.superplus.listener;

import me.superischroma.superplus.SuperPLUS;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;
import java.util.ArrayList;
import java.util.List;

public class Orbiter implements Listener
{
    private SuperPLUS plugin;
    public Orbiter(SuperPLUS plugin)
    {
        this.plugin = plugin;
    }

    public static List<Player> ORBITING = new ArrayList<>();

    public static boolean isOrbiting(Player player)
    {
        return ORBITING.contains(player);
    }

    public static void setOrbiting(Player player, boolean orbiting)
    {
        if (orbiting)
        {
            ORBITING.add(player);
            return;
        }
        ORBITING.remove(player);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e)
    {
        Player player = e.getPlayer();
        if (isOrbiting(player))
        {
            player.setVelocity(player.getVelocity().clone().add(new Vector(0, 10, 0)));
        }
    }
}
