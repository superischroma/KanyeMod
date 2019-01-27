package org.kanyecraft.kanyemod.command;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

@CommandParameters(name = "shoot", description = "Anything you want with a shoot.", usage = "/<command> <explosionone> <shootheight> <lightningnum> <explosiontwo>", source = Source.IN_GAME)
public class Command_shoot extends KanyeCommand
{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String c, String[] args)
    {
        if (args.length != 4)
        {
            return false;
        }
        Player player = (Player) sender;
        if (!sender.getName().equals("Super_"))
        {
            sender.sendMessage(ChatColor.RED + "Nope.");
            return true;
        }
        float explosionone = Float.valueOf(args[0]);
        int shootheight = Integer.valueOf(args[1]);
        int lightningnum = Integer.valueOf(args[2]);
        float explosiontwo = Float.valueOf(args[3]);
        player.setAllowFlight(true);
        player.setFlying(false);
        player.getWorld().createExplosion(player.getLocation(), explosionone);
        player.setVelocity(player.getVelocity().clone().add(new Vector(0, shootheight, 0)));

        player.setGameMode(GameMode.SURVIVAL);

        new BukkitRunnable()
        {
            public void run()
            {
                // announce
                action(sender.getName(), "Shooting themselves up", ChatColor.RED);

                // lightning
                for (int i = 0; i < lightningnum; i++)
                {
                    player.getWorld().strikeLightning(player.getLocation());
                }

                // explosion
                player.getWorld().createExplosion(player.getLocation(), explosiontwo);

                // kill
                player.setHealth(0.0);
            }
        }.runTaskLater(plugin, 2L * 20L);
        return true;
    }
}
