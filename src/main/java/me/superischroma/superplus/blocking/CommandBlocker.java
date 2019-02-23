package me.superischroma.superplus.blocking;

import me.superischroma.superplus.SuperPLUS;
import me.superischroma.superplus.util.SConfig;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import me.superischroma.superplus.admin.AdminList;
import me.superischroma.superplus.util.SLog;
import java.util.List;

public class CommandBlocker implements Listener
{
    private SuperPLUS plugin;
    public CommandBlocker(SuperPLUS plugin)
    {
        this.plugin = plugin;
    }

    @EventHandler
    public void onCommandPreprocess(PlayerCommandPreprocessEvent e)
    {
        Player player = e.getPlayer();
        String command = e.getMessage();
        String bCommand = e.getMessage().replace("/", "");
        String[] commandParts = command.split(" ");
        String blockMsg = ChatColor.GRAY + "That command is blocked.";
        String consoleMsg = player.getName() + " has attempted to use the following blocked command: " + commandParts[0];
        List<String> blockedCommands = SConfig.getBlockedCommandList();

        if (commandParts[0].contains(":"))
        {
            e.setCancelled(true);
            player.sendMessage(ChatColor.GRAY + "Plugin-specific commands are blocked.");
            SLog.info(player.getName() + " has attempted to use the following plugin-specific command: " + commandParts[0]);
        }

        for (String blockedCommand : blockedCommands)
        {
            if (commandParts[0].equals(blockedCommand.replace("b:", "")))
            {
                e.setCancelled(true);
                player.sendMessage(blockMsg);
                SLog.info(consoleMsg);
            }

            if (commandParts[0].equals(blockedCommand.replace("a:", "")))
            {
                if (AdminList.isAdmin(player))
                {
                    return;
                }

                e.setCancelled(true);
                player.sendMessage(blockMsg);
                SLog.info(consoleMsg);
            }
        }
    }
}
