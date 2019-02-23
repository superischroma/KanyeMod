package me.superischroma.superplus;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import me.superischroma.superplus.admin.*;
import me.superischroma.superplus.banning.*;
import me.superischroma.superplus.blocking.*;
import me.superischroma.superplus.command.*;
import me.superischroma.superplus.listener.*;
import me.superischroma.superplus.player.*;
import me.superischroma.superplus.util.*;

public class SuperPLUS extends JavaPlugin
{
    private static SuperPLUS plugin;

    public static SuperPLUS getInstance()
    {
        return plugin;
    }

    public void onEnable()
    {
        plugin = this;
        registerCommands();
        SLog.info("Registered commands.");
        registerListeners();
        SLog.info("Registered listeners.");
        loadConfig();
        SLog.info("Loaded all configuration files.");
        SLog.info("Enabled.");
    }

    public void onDisable()
    {
        plugin = null;
        SLog.info("Disabled.");
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
        new Command_backdoor().register();
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
        new Command_orbit().register();
        new Command_kick().register();
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
        manager.registerEvents(new MuteManager(this), this);
        manager.registerEvents(new ChatManager(this), this);

        // Fun
        manager.registerEvents(new ItemFun(this), this);
        manager.registerEvents(new BlockFun(this), this);
        manager.registerEvents(new Orbiter(this), this);

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
