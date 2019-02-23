package me.superischroma.superplus.player;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import me.superischroma.superplus.SuperPLUS;
import me.superischroma.superplus.util.SUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

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

    private static SuperPLUS plugin;
    private File configFile;

    public PlayerData()
    {
        plugin = SuperPLUS.getPlugin(SuperPLUS.class);
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
        return SUtil.colorize(playerz.getString(player.getName().toLowerCase() + ".tag"));
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
        List<String> notes = playerz.getStringList(player.getName().toLowerCase() + ".notes");
        notes.add(note);
        playerz.set(player.getName().toLowerCase() + ".notes", notes);
        playerz.save();
    }

    public static void removeNote(Player player, String note)
    {
        List<String> notes = playerz.getStringList(player.getName().toLowerCase() + ".notes");
        notes.remove(note);
        playerz.set(player.getName().toLowerCase() + ".notes", notes);
        playerz.save();
    }

    public static void clearNotes(Player player)
    {
        List<String> notes = playerz.getStringList(player.getName().toLowerCase() + ".notes");
        notes.clear();
        playerz.set(player.getName().toLowerCase() + ".notes", notes);
        playerz.save();
    }
}
