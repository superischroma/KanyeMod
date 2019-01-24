package org.kanyecraft.kanyemod;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.kanyecraft.kanyemod.admin.Admin;
import org.kanyecraft.kanyemod.command.*;
import org.kanyecraft.kanyemod.listener.*;
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
    }

    private void registerListeners()
    {
        PluginManager manager = Bukkit.getServer().getPluginManager();
        manager.registerEvents(new PlayerListener(this), this);
        manager.registerEvents(new FreezeListener(this), this);
    }

    private void loadConfig()
    {
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();
        Admin admins = Admin.getConfig();
        admins.options().copyDefaults(true);
        admins.save();
    }
}
