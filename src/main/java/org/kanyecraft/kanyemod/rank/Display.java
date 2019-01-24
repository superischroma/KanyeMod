package org.kanyecraft.kanyemod.rank;

import org.bukkit.ChatColor;

public interface Display
{
    String getModifier();

    String getName();

    String getTag();

    ChatColor getColor();

    String getLoginMessage();
}
