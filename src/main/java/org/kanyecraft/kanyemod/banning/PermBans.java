package org.kanyecraft.kanyemod.banning;

import org.bukkit.configuration.file.YamlConfiguration;
import org.kanyecraft.kanyemod.KanyeMod;

import java.io.File;

public class PermBans extends YamlConfiguration
{
    private static PermBans permbans;

    public static PermBans getConfig()
    {
        if (permbans == null)
        {
            permbans = new PermBans();
        }
        return permbans;
    }

    private KanyeMod plugin;
    private File configFile;

    public PermBans()
    {
        plugin = KanyeMod.getPlugin(KanyeMod.class);
        configFile = new File(plugin.getDataFolder(), "permbans.yml");
        saveDefault();
        reload();
    }

    public void reload()
    {
        try
        {
            super.load(configFile);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void save()
    {
        try
        {
            super.save(configFile);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void saveDefault()
    {
        plugin.saveResource("permbans.yml", false);
    }
}
