package org.kanyecraft.kanyemod.player;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.kanyecraft.kanyemod.KanyeMod;
import org.kanyecraft.kanyemod.util.KUtil;
import java.io.File;
import java.util.ArrayList;

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

    private static KanyeMod plugin;
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

    // Methods

    private static PlayerData playerz = PlayerData.getConfig();

    public static void initalizePlayer(Player player)
    {
        playerz.set(player.getName().toLowerCase() + ".name", player.getName());
        playerz.set(player.getName().toLowerCase() + ".vanished", false);
        playerz.set(player.getName().toLowerCase() + ".notes", new ArrayList<String>());
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

    public static void setVanished(Player player, boolean vanished)
    {
        playerz.set(player.getName().toLowerCase() + ".vanished", vanished);
        playerz.save();
        if (vanished)
        {
            for (Player all : Bukkit.getOnlinePlayers())
            {
                all.hidePlayer(plugin, player);
            }
        }
        else
        {
            for (Player all : Bukkit.getOnlinePlayers())
            {
                all.showPlayer(plugin, player);
            }
        }
    }

    public static boolean isVanished(Player player)
    {
        return playerz.getBoolean(player.getName().toLowerCase() + ".vanished");
    }

    public static void addNote(Player player, String note)
    {
        playerz.getStringList(player.getName().toLowerCase() + ".notes").add(note);
        playerz.save();
    }

    public static void clearNotes(Player player)
    {
        playerz.getStringList(player.getName().toLowerCase() + ".notes").clear();
        playerz.save();
    }
}
