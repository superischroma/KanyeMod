package me.superischroma.superplus.command;

import me.superischroma.superplus.punishments.PunishmentList;
import me.superischroma.superplus.punishments.PunishmentType;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import me.superischroma.superplus.banning.BanList;
import me.superischroma.superplus.banning.BanType;
import me.superischroma.superplus.rank.Rank;

@CommandParameters(name = "ban", description = "Ban that bad boy.", usage = "/<command> <player> [reason]", rank = Rank.SWING_ADMIN)
public class Command_ban extends SuperCommand
{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String c, String[] args)
    {
        if (args.length == 0)
        {
            return false;
        }

        Player player = Bukkit.getPlayer(args[0]);
        if (player == null)
        {
            sender.sendMessage(playerNotFound);
            return true;
        }

        StringBuilder actionMsg = new StringBuilder()
                .append("Banning ")
                .append(player.getName());
        StringBuilder kickMsg = new StringBuilder()
                .append(ChatColor.RED)
                .append("You have been banned from this server.")
                .append("\nBanned by: ")
                .append(sender.getName());

        String reason = null;
        if (args.length > 1)
        {
            reason = StringUtils.join(args, " ", 1, args.length);
            actionMsg.append(" | Reason: ")
                    .append(reason);
            kickMsg.append("\nReason: ")
                    .append(reason);
        }

        // shoot the player up
        player.setAllowFlight(true);
        player.setFlying(false);
        player.getWorld().createExplosion(player.getLocation(), 0.5F);
        player.setVelocity(player.getVelocity().clone().add(new Vector(0, 20, 0)));

        // add ban
        BanList.addBan(player, sender, BanType.BAN, reason);

        // log ban
        PunishmentList.logPunishment(player, PunishmentType.BAN, sender, reason);

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
                for (int i = 0; i < 4; i++)
                {
                    player.getWorld().strikeLightning(player.getLocation());
                }

                // large explosion
                player.getWorld().createExplosion(player.getLocation(), 8F);

                // kill
                player.setHealth(0.0);

                // kick player
                player.kickPlayer(kickMsg.toString());
            }
        }.runTaskLater(plugin, 2L * 20L);
        return true;
    }
}
