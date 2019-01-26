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
import org.kanyecraft.kanyemod.rank.Rank;

@CommandParameters(name = "smite", description = "Smite that bitch down.", usage = "/<command> <player> [reason]", rank = Rank.ADMIN)
public class Command_smite extends KanyeCommand
{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String c, String[] args)
    {
        Player player = Bukkit.getPlayer(args[0]);
        if (player == null)
        {
            sender.sendMessage(playerNotFound);
            return true;
        }

        StringBuilder actionMsg = new StringBuilder()
                .append("Smiting ")
                .append(player.getName());

        String reason = null;
        if (args.length > 1)
        {
            reason = StringUtils.join(args, " ", 1, args.length);
            actionMsg.append(" | Reason: ")
                    .append(reason);
        }

        // shoot the player up
        player.setAllowFlight(true);
        player.setFlying(false);
        player.getWorld().createExplosion(player.getLocation(), 0.5F);
        player.setVelocity(player.getVelocity().clone().add(new Vector(0, 20, 0)));

        new BukkitRunnable()
        {
            public void run()
            {
                // announce
                action(sender.getName(), actionMsg.toString(), ChatColor.RED);

                // de-op
                player.setOp(false);

                // set to survival
                player.setGameMode(GameMode.SURVIVAL);

                // clear inventory
                player.getInventory().clear();

                // strike 4 lightning bolts
                int i;
                for (i = 0; i < 4; i++)
                {
                    player.getWorld().strikeLightning(player.getLocation());
                }

                // large explosion
                player.getWorld().createExplosion(player.getLocation(), 8F);

                // kill just in case
                player.setHealth(0.0);
            }
        }.runTaskLater(plugin, 2L * 20L);
        return true;
    }
}
