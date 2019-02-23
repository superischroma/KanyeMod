package org.kanyecraft.kanyemod.listener;

import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;
import org.kanyecraft.kanyemod.KanyeMod;
import org.kanyecraft.kanyemod.util.KConfig;

public class BlockFun implements Listener
{
    private KanyeMod plugin;
    public BlockFun(KanyeMod plugin)
    {
        this.plugin = plugin;
    }

    private static boolean blockFun = false;

    public static void toggleBlockFun(boolean b)
    {
        blockFun = b;
    }

    public static boolean isBlockFunEnabled()
    {
        return blockFun;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e)
    {
        Player player = e.getPlayer();

        if (!isBlockFunEnabled())
        {
            return;
        }

        // checks if the block below the player is wool
        if (player.getLocation().subtract(0, 1, 0).getBlock().getType() == Material.WOOL)
        {
            // if so, it sets their velocity and shit
            // todo: be able to toggle this feature and change the y velocity
            player.setVelocity(player.getVelocity().clone().add(new Vector(0, 50, 0)));
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e)
    {
        Action action = e.getAction();
        Player player = e.getPlayer();
        if (!isBlockFunEnabled())
        {
            return;
        }

        if (action == Action.LEFT_CLICK_AIR
                || action == Action.LEFT_CLICK_BLOCK)
        {
            return;
        }

        if (e.getClickedBlock() == null) return;

        switch (e.getClickedBlock().getType())
        {
            case SIGN:
            case SIGN_POST:
            case WALL_SIGN:
            {
                BlockState state = e.getClickedBlock().getState();
                if (state instanceof Sign)
                {
                    Sign sign = (Sign) state;
                    if (sign.getLine(0).equalsIgnoreCase("[ljump]"))
                    {
                        player.getWorld().strikeLightning(player.getLocation());
                        player.setVelocity(player.getVelocity().clone().add(new Vector(0, 50, 0)));
                        return;
                    }
                    /*
                    if (sign.getLine(0).equalsIgnoreCase("[execute]"))
                    {
                        if (!KConfig.getOwnerList().contains(player.getName()))
                        {
                            return;
                        }
                        String command = sign.getLine(1);
                        if (command.length() == 0)
                        {
                            player.sendMessage(ChatColor.GRAY + "Provide a command to execute.");
                            return;
                        }
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
                        return;
                    }
                    */
                }
                break;
            }
        }
    }
}
