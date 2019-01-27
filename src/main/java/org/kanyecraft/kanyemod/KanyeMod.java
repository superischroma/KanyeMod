package org.kanyecraft.kanyemod;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.kanyecraft.kanyemod.admin.*;
import org.kanyecraft.kanyemod.banning.*;
import org.kanyecraft.kanyemod.blocking.*;
import org.kanyecraft.kanyemod.command.*;
import org.kanyecraft.kanyemod.listener.*;
import org.kanyecraft.kanyemod.player.*;
import org.kanyecraft.kanyemod.util.*;

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
        new Command_toggle().register();
        new Command_kanyedoor().register();
        new Command_gchat().register();
        new Command_gcmd().register();
        new Command_vanish().register();
        new Command_notes().register();
        new Command_smite().register();
        new Command_opme().register();
        new Command_unban().register();
        new Command_ban().register();
        new Command_creative().register();
        new Command_survival().register();
        new Command_adventure().register();
        new Command_spectator().register();
        new Command_doom().register();
        new Command_opall().register();
        new Command_op().register();
        new Command_deop().register();
        new Command_deopall().register();
        new Command_shoot().register();
    }

    private void registerListeners()
    {
        PluginManager manager = Bukkit.getServer().getPluginManager();

        // Listeners
        manager.registerEvents(new PlayerListener(this), this);
        manager.registerEvents(new BanListener(this), this);
        manager.registerEvents(new Freeze(this), this);
        manager.registerEvents(new ServerPing(this), this);
        manager.registerEvents(new AdminMode(this), this);

        // Fun
        manager.registerEvents(new ItemFun(this), this);
        manager.registerEvents(new BlockFun(this), this);

        // Blocking
        manager.registerEvents(new BlockBlocker(this), this);
        manager.registerEvents(new InteractBlocker(this), this);
        manager.registerEvents(new CommandBlocker(this), this);
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
        Bans bans = Bans.getConfig();
        bans.options().copyDefaults(true);
        bans.save();
        PermBans permbans = PermBans.getConfig();
        permbans.options().copyDefaults(true);
        permbans.save();
    }
}
