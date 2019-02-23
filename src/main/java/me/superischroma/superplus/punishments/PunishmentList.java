package me.superischroma.superplus.punishments;

import me.superischroma.superplus.util.SUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PunishmentList
{
    private static Punishments punishments = Punishments.getConfig();

    private static SimpleDateFormat ISSUED = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");

    public static void logPunishment(Player punished, PunishmentType type, CommandSender punisher, String reason)
    {
        long id = System.currentTimeMillis();
        punishments.set(id + ".name", punished.getName());
        punishments.set(id + ".type", type.name());
        punishments.set(id + ".ip", SUtil.getIp(punished));
        punishments.set(id + ".punisher", punisher.getName());
        punishments.set(id + ".reason", reason);
        punishments.set(id + ".issued", ISSUED.format(new Date()));
        punishments.save();
    }
}
