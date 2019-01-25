package org.kanyecraft.kanyemod.command;

import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.kanyecraft.kanyemod.player.PlayerData;
import org.kanyecraft.kanyemod.rank.Rank;
import java.util.List;

@CommandParameters(name = "notes", description = "Write notes down in-game", usage = "/<command> [add <note> | clear]", rank = Rank.OP, source = Source.IN_GAME)
public class Command_notes extends KanyeCommand
{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String c, String[] args)
    {
        Player player = (Player) sender;
        List<String> notes = players.getStringList(sender.getName().toLowerCase() + ".notes");
        if (args.length > 1)
        {
            if (args[0].equalsIgnoreCase("add"))
            {
                String note = StringUtils.join(args, " ", 1, args.length);
                if (note.length() == 0)
                {
                    return false;
                }
                PlayerData.addNote(player, note);
                sender.sendMessage(ChatColor.GRAY + "New note added. Do \"/notes\" to view it.");
                return true;
            }
            if (args[0].equalsIgnoreCase("remove"))
            {
                String note = StringUtils.join(args, " ", 1, args.length);
                if (note.length() == 0)
                {
                    return false;
                }
                if (!notes.contains(note))
                {
                    player.sendMessage(ChatColor.GRAY + "Note could not be found.");
                    return true;
                }
                PlayerData.removeNote(player, note);
                sender.sendMessage(ChatColor.GRAY + "Note removed.");
                return true;
            }
        }
        if (args.length == 1)
        {
            if (args[0].equalsIgnoreCase("clear"))
            {
                PlayerData.clearNotes(player);
                sender.sendMessage(ChatColor.GRAY + "Notes cleared.");
                return true;
            }
        }
        StringBuilder sb = new StringBuilder()
                .append(ChatColor.DARK_GREEN + "Notes: ");
        if (notes.isEmpty())
        {
            sender.sendMessage(ChatColor.GRAY + "You don't have any notes.");
            return true;
        }
        for (String note : notes)
        {
            sb.append(ChatColor.DARK_GREEN + "\n - " + ChatColor.GREEN + note);
        }
        sender.sendMessage(sb.toString());
        return true;
    }
}
