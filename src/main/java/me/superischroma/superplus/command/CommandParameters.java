package me.superischroma.superplus.command;

import me.superischroma.superplus.rank.Rank;
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

    Source source() default Source.ALL;
}
