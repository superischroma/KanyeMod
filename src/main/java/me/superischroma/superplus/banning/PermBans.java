package me.superischroma.superplus.banning;

import me.superischroma.superplus.SuperPLUS;
import org.bukkit.configuration.file.YamlConfiguration;

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

    private SuperPLUS plugin;
    private File configFile;

    public PermBans()
    {
        plugin = SuperPLUS.getPlugin(SuperPLUS.class);
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
