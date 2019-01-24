package org.kanyecraft.kanyemod.rank;

import lombok.Getter;
import org.bukkit.ChatColor;

public enum Rank implements Display
{
    IMPOSTOR("an", "Impostor", "IMP", false, ChatColor.YELLOW),
    NON_OP("a", "Non-Op", "Non-Op", false, ChatColor.WHITE),
    OP("an", "Operator", "Op", false, ChatColor.WHITE),
    ADMIN("an", "Administrator", "Admin", true, ChatColor.DARK_AQUA),
    SENIOR_ADMIN("a", "Senior Admin", "SrA", true, ChatColor.LIGHT_PURPLE),
    CONSOLE("from", "Console", "Console", true, ChatColor.BLUE);

    @Getter
    public String modifier;
    @Getter
    public String name;
    @Getter
    public String tag;
    public boolean isAdmin;
    @Getter
    public ChatColor color;

    Rank(String modifier, String name, String tag, boolean isAdmin, ChatColor color)
    {
        this.modifier = modifier;
        this.name = name;
        this.tag = color + "[" + tag + "]";
        this.isAdmin = isAdmin;
        this.color = color;
    }

    public String getLoginMessage()
    {
        return modifier + " " + color + name;
    }

    public static Rank findRank(String r)
    {
        return valueOf(r);
    }

    public int getLevel()
    {
        return ordinal();
    }

    public boolean isAtLeast(Rank rank)
    {
        return getLevel() >= rank.getLevel();
    }
}
