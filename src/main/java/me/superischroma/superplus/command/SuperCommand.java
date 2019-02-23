package me.superischroma.superplus.command;

import me.superischroma.superplus.SuperPLUS;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import me.superischroma.superplus.admin.Admin;
import me.superischroma.superplus.player.PlayerData;
import me.superischroma.superplus.rank.Display;
import me.superischroma.superplus.rank.Rank;
import me.superischroma.superplus.rank.RankManager;
import me.superischroma.superplus.util.SUtil;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public abstract class SuperCommand implements CommandExecutor, TabCompleter
{
    private static CommandMap cmap;
    private final String name;
    private final String description;
    private final String usage;
    private final String aliases;
    private final Rank rank;
    private final Source source;
    public SuperPLUS plugin = SuperPLUS.getInstance();
    public final Admin admins = Admin.getConfig();
    public final PlayerData players = PlayerData.getConfig();

    private final CommandParameters params;

    public SuperCommand()
    {
        params = getClass().getAnnotation(CommandParameters.class);
        this.name = params.name().toLowerCase();
        this.description = params.description();
        this.usage = params.usage();
        this.aliases = params.aliases();
        this.rank = params.rank();
        this.source = params.source();
    }

    public void register()
    {
        KCommand cmd = new KCommand(this.name);
        if (this.aliases != null) cmd.setAliases(Arrays.asList(StringUtils.split(this.aliases, ",")));
        if (this.description != null) cmd.setDescription(this.description);
        if (this.usage != null) cmd.setUsage(this.usage);
        getCommandMap().register("", cmd);
        cmd.setExecutor(this);
    }

    public String playerNotFound = ChatColor.GRAY + "Player not found.";
    public String onlyConsole = ChatColor.RED + "Only console senders are allowed to execute this command!";
    public String noConsole = ChatColor.RED + "Only in-game senders are allowed to execute this command!";
    public String opped = ChatColor.AQUA + "You are now opped!";
    public String deopped = ChatColor.AQUA + "You are no longer opped!";

    final CommandMap getCommandMap()
    {
        if (cmap == null)
        {
            try
            {
                final Field f = Bukkit.getServer().getClass().getDeclaredField("commandMap");
                f.setAccessible(true);
                cmap = (CommandMap) f.get(Bukkit.getServer());
                return getCommandMap();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else if (cmap != null)
        {
            return cmap;
        }
        return getCommandMap();
    }

    private final class KCommand extends Command
    {
        private SuperCommand cmd = null;
        private KCommand(String command)
        {
            super(command);
        }
        public void setExecutor(SuperCommand cmd)
        {
            this.cmd = cmd;
        }
        public boolean execute(CommandSender sender, String c, String[] args)
        {
            if (cmd != null)
            {
                if (params.source() == Source.IN_GAME && sender instanceof ConsoleCommandSender)
                {
                    sender.sendMessage(ChatColor.RED + "Console senders are not allowed to execute this command!");
                    return true;
                }

                if (params.source() == Source.CONSOLE && sender instanceof Player)
                {
                    sender.sendMessage(ChatColor.RED + "Only console senders are allowed to execute this command!");
                    return true;
                }

                if (!RankManager.getRank(sender).isAtLeast(cmd.rank))
                {
                    sender.sendMessage(ChatColor.RED + "You must be at least " + cmd.rank.getModifier() + " " + cmd.rank.getName() + " to execute this command!");
                    return true;
                }

                return cmd.onCommand(sender, this, c, args);
            }
            return false;
        }

        @Override
        public List<String> tabComplete(CommandSender sender, String alias, String[] args)
        {
            if (cmd != null)
            {
                return cmd.onTabComplete(sender, this, alias, args);
            }
            return null;
        }
    }

    public abstract boolean onCommand(CommandSender sender, Command cmd, String c, String[] args);

    public List<String> onTabComplete(CommandSender sender, Command cmd, String c, String[] args)
    {
        return null;
    }

    public static Rank getRank(CommandSender sender)
    {
        if (isConsole(sender))
        {
            return Rank.CONSOLE;
        }
        return RankManager.getRank((Player) sender);
    }

    public Display getDisplay(CommandSender sender)
    {
        if (isConsole(sender))
        {
            return Rank.CONSOLE;
        }
        return RankManager.getDisplay((Player) sender);
    }

    public static boolean isConsole(CommandSender sender)
    {
        return sender instanceof ConsoleCommandSender;
    }

    public boolean isActive(CommandSender sender)
    {
        return admins.getBoolean(sender.getName().toLowerCase() + ".active");
    }

    public void action(String name, String msg)
    {
        SUtil.broadcast(ChatColor.GREEN + name + " - " + msg);
    }

    public void action(String name, String msg, ChatColor color)
    {
        SUtil.broadcast(color + name + " - " + msg);
    }
}
