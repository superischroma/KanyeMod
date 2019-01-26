package org.kanyecraft.kanyemod.command;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.kanyecraft.kanyemod.admin.AdminList;
import org.kanyecraft.kanyemod.banning.BanList;
import org.kanyecraft.kanyemod.banning.BanType;
import org.kanyecraft.kanyemod.rank.Rank;

@CommandParameters(name = "doom", description = "Doom will fall on you.", usage = "/<command> <player>", rank = Rank.SENIOR_ADMIN, source = Source.CONSOLE)
public class Command_doom extends KanyeCommand
{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String c, String[] args)
    {
        if (args.length != 1)
        {
            return false;
        }

        Player player = Bukkit.getPlayer(args[0]);
        if (player == null)
        {
            sender.sendMessage(playerNotFound);
            return true;
        }

        // shoot the player up
        player.setAllowFlight(true);
        player.setFlying(false);
        player.getWorld().createExplosion(player.getLocation(), 5F);
        player.setVelocity(player.getVelocity().clone().add(new Vector(0, 25, 0)));

        new BukkitRunnable()
        {
            public void run()
            {
                // announce
                action(sender.getName(), "Dooming " + player.getName(), ChatColor.RED);

                // remove the admin
                action(sender.getName(), "Removing " + player.getName() + " from the admin list", ChatColor.RED);
                AdminList.removeAdmin(player);

                // de-op
                player.setOp(false);

                // set to survival
                player.setGameMode(GameMode.SURVIVAL);

                // clear inventory
                player.getInventory().clear();

                // strike 4 lightning bolts
                int i;
                for (i = 0; i < 50; i++)
                {
                    player.getWorld().strikeLightning(player.getLocation());
                }

                // large explosion
                player.getWorld().createExplosion(player.getLocation(), 100F);

                // kill
                player.setHealth(0.0);

                // add ban
                BanList.addBan(player, sender, BanType.BAN, "This bitch is doomed.");

                // kick player
                player.kickPlayer(ChatColor.RED + "You're fucking doomed.");
            }
        }.runTaskLater(plugin, 2L * 20L);
        return true;
    }
}
