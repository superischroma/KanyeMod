package org.kanyecraft.kanyemod.util;

import org.bukkit.configuration.file.FileConfiguration;
import org.kanyecraft.kanyemod.KanyeMod;

import java.util.List;

public class KConfig
{
    private static FileConfiguration config = KanyeMod.getInstance().getConfig();

    public static List<String> getExecutiveList()
    {
        return config.getStringList("server.executives");
    }

    public static List<String> getOwnerList()
    {
        return config.getStringList("server.owners");
    }
}
