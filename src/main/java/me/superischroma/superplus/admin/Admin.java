package me.superischroma.superplus.admin;

import me.superischroma.superplus.SuperPLUS;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Admin extends YamlConfiguration
{
    private static Admin admins;

    public static Admin getConfig()
    {
        if (admins == null)
        {
            admins = new Admin();
        }
        return admins;
    }

    private SuperPLUS plugin;
    private File configFile;

    public Admin()
    {
        plugin = SuperPLUS.getPlugin(SuperPLUS.class);
        configFile = new File(plugin.getDataFolder(), "admins.yml");
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
        plugin.saveResource("admins.yml", false);
    }
}
