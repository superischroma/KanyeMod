package me.superischroma.superplus.util;

import org.bukkit.configuration.file.FileConfiguration;
import me.superischroma.superplus.SuperPLUS;

import java.util.List;

public class SConfig
{
    private static FileConfiguration config = SuperPLUS.getInstance().getConfig();

    private static void save()
    {
        SuperPLUS.getInstance().saveConfig();
    }

    public static List<String> getManagerList()
    {
        return config.getStringList("server.managers");
    }

    public static String getChatFormat()
    {
        return config.getString("formats.chat");
    }

    public static String getAdminChatFormat()
    {
        return config.getString("formats.admin");
    }

    public static String getSayFormat()
    {
        return config.getString("formats.say");
    }

    public static String getServerMOTD()
    {
        return config.getString("server.motd");
    }

    public static String getServerName()
    {
        return config.getString("server.name");
    }

    public static boolean isLavaEnabled()
    {
        return config.getBoolean("allow.lava");
    }

    public static void setLavaEnabled(boolean b)
    {
        config.set("allow.lava", b);
        save();
    }

    public static boolean isWaterEnabled()
    {
        return config.getBoolean("allow.water");
    }

    public static void setWaterEnabled(boolean b)
    {
        config.set("allow.water", b);
        save();
    }

    public static List<String> getBlockedCommandList()
    {
        return config.getStringList("blocked_commands");
    }
}
