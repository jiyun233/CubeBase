package cn.origin.cube.command;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface CommandInfo {
    String name();
    String[] aliases() default {};

    String descriptions();

    String usage();

}
