package org.kanyecraft.kanyemod.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class KUtil
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
}
