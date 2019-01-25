package org.kanyecraft.kanyemod;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.kanyecraft.kanyemod.admin.Admin;
import org.kanyecraft.kanyemod.command.*;
import org.kanyecraft.kanyemod.listener.*;
import org.kanyecraft.kanyemod.player.PlayerData;
import org.kanyecraft.kanyemod.util.KLog;

public class KanyeMod extends JavaPlugin
{
    private static KanyeMod plugin;

    public static KanyeMod getInstance()
    {
        return plugin;
    }

    public void onEnable()
    {
        plugin = this;
        registerCommands();
        KLog.info("Registered commands.");
        registerListeners();
        KLog.info("Registered listeners.");
        loadConfig();
        KLog.info("Loaded all configuration files.");
        KLog.info("Enabled.");
    }

    public void onDisable()
    {
        plugin = null;
        KLog.info("Disabled.");
    }

    private void registerCommands()
    {
        new Command_admin().register();
        new Command_adminchat().register();
        new Command_myadmin().register();
        new Command_commandspy().register();
        new Command_say().register();
        new Command_adminmode().register();
        new Command_tag().register();
    }

    private void registerListeners()
    {
        PluginManager manager = Bukkit.getServer().getPluginManager();
        manager.registerEvents(new PlayerListener(this), this);
        manager.registerEvents(new Freeze(this), this);
        manager.registerEvents(new ServerPing(this), this);
        manager.registerEvents(new AdminMode(this), this);
    }

    private void loadConfig()
    {
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();
        Admin admins = Admin.getConfig();
        admins.options().copyDefaults(true);
        admins.save();
        PlayerData players = PlayerData.getConfig();
        players.options().copyDefaults(true);
        players.save();
    }
}
