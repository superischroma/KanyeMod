package me.superischroma.superplus.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class SUtil
{
    public static final List<String> DEVELOPERS = Arrays.asList();

    public static String getIp(Player player)
    {
        return player.getAddress().getAddress().getHostAddress().trim();
    }

    public static void broadcast(String s)
    {
        Bukkit.broadcastMessage(s);
    }

    public static String colorize(String s)
    {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}
