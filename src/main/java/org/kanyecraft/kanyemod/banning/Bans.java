package org.kanyecraft.kanyemod.banning;

import org.bukkit.configuration.file.YamlConfiguration;
import org.kanyecraft.kanyemod.KanyeMod;
import java.io.File;

public class Bans extends YamlConfiguration
{
    private static Bans bans;

    public static Bans getConfig()
    {
        if (bans == null)
        {
            bans = new Bans();
        }
        return bans;
    }

    private KanyeMod plugin;
    private File configFile;

    public Bans()
    {
        plugin = KanyeMod.getPlugin(KanyeMod.class);
        configFile = new File(plugin.getDataFolder(), "bans.yml");
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
        plugin.saveResource("bans.yml", false);
    }
}
