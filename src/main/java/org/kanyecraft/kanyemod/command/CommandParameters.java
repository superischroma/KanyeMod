package org.kanyecraft.kanyemod.command;

import org.kanyecraft.kanyemod.rank.Rank;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface CommandParameters
{
    String name();

    String description() default "";

    String usage() default "";

    String aliases() default "";

    Rank rank() default Rank.IMPOSTOR;

    boolean allowConsole() default true;
}
