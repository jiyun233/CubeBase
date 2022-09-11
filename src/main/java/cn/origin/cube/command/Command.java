package cn.origin.cube.command;

import cn.origin.cube.module.ModuleInfo;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class Command {
    public final String name;
    public final String[] aliases;

    public final String descriptions;

    public final String usage;

    public Command() {
        this.name = getAnnotation().name();
        this.aliases = getAnnotation().aliases();
        this.descriptions = getAnnotation().descriptions();
        this.usage = getAnnotation().usage();
    }

    public abstract void execute(String[] args);

    private CommandInfo getAnnotation() {
        if (getClass().isAnnotationPresent(CommandInfo.class)) {
            return getClass().getAnnotation(CommandInfo.class);
        }
        throw new IllegalStateException("No Annotation on class " + this.getClass().getCanonicalName() + "!");
    }
}
