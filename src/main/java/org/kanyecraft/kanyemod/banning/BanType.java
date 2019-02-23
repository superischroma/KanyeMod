package org.kanyecraft.kanyemod.banning;

import lombok.Getter;

public enum BanType
{
    QUICKBAN(300000),
    BAN(86400000);

    @Getter
    public int length;

    BanType(int length)
    {
        this.length = length;
    }
}
