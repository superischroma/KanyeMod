package org.kanyecraft.kanyemod.player;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.kanyecraft.kanyemod.KanyeMod;
import org.kanyecraft.kanyemod.util.KUtil;

import java.io.File;

public class PlayerData extends YamlConfiguration
{
    private static PlayerData players;

    public static PlayerData getConfig()
    {
        if (players == null)
        {
            players = new PlayerData();
        }
        return players;
    }

    private KanyeMod plugin;
    private File configFile;

    public PlayerData()
    {
        plugin = KanyeMod.getPlugin(KanyeMod.class);
        configFile = new File(plugin.getDataFolder(), "playerdata.yml");
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
        plugin.saveResource("playerdata.yml", false);
    }

    // Methods and related

    private static PlayerData playerz = PlayerData.getConfig();

    public static void initalizePlayer(Player player)
    {
        playerz.set(player.getName().toLowerCase() + ".name", player.getName());
        playerz.save();
    }

    public static void setTag(Player player, String tag)
    {
        playerz.set(player.getName().toLowerCase() + ".tag", tag);
        playerz.save();
    }

    public static String getTag(Player player)
    {
        return KUtil.colorize(playerz.getString(player.getName().toLowerCase() + ".tag"));
    }

    public static void clearTag(Player player)
    {
        playerz.set(player.getName().toLowerCase() + ".tag", null);
        playerz.save();
    }

    public static boolean hasTag(Player player)
    {
        return playerz.contains(player.getName().toLowerCase() + ".tag");
    }
}
