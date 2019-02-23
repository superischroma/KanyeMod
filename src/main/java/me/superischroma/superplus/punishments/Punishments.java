package me.superischroma.superplus.punishments;

import me.superischroma.superplus.SuperPLUS;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Punishments extends YamlConfiguration
{
    private static Punishments punishments;

    public static Punishments getConfig()
    {
        if (punishments == null)
        {
            punishments = new Punishments();
        }
        return punishments;
    }

    private SuperPLUS plugin;
    private File configFile;

    public Punishments()
    {
        plugin = SuperPLUS.getPlugin(SuperPLUS.class);
        configFile = new File(plugin.getDataFolder(), "punishments.yml");
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
        plugin.saveResource("punishments.yml", false);
    }
}

