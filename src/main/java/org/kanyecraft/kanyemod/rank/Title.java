package org.kanyecraft.kanyemod.rank;

import lombok.Getter;
import org.bukkit.ChatColor;

public enum Title implements Display
{
    DEVELOPER("a", "Developer", "Dev", ChatColor.DARK_PURPLE),
    EXECUTIVE("an", "Executive", "Exec", ChatColor.RED),
    OWNER("the", "Owner", "Owner", ChatColor.GREEN);

    @Getter
    public String modifier;
    @Getter
    public String name;
    @Getter
    public String tag;
    @Getter
    public ChatColor color;

    Title(String modifier, String name, String tag, ChatColor color)
    {
        this.modifier = modifier;
        this.name = name;
        this.tag = color + "[" + tag + "]";
        this.color = color;
    }

    public String getLoginMessage()
    {
        return modifier + " " + color + name;
    }
}
