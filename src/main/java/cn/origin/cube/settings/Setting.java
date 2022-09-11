package cn.origin.cube.settings;

import cn.origin.cube.event.events.client.SettingChangeEvent;
import cn.origin.cube.module.AbstractModule;
import net.minecraftforge.common.MinecraftForge;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class Setting<T> {
    @NotNull
    private T value;

    private final T defaultValue;

    private final String name;
    private final AbstractModule father;

    List<Predicate<Object>> visibility = new ArrayList<>();

    public Setting(String name, T value, AbstractModule father) {
        this.value = value;
        this.defaultValue = value;
        this.name = name;
        this.father = father;
    }

    public Setting<T> visible(Predicate<Object> predicate) {
        this.visibility.add(predicate);
        return this;
    }

    public String getName() {
        return name;
    }

    public AbstractModule getFatherModule() {
        return father;
    }

    public T getDefaultValue() {
        return defaultValue;
    }

    @NotNull
    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        T oldValue = this.value;
        this.value = value;
        SettingChangeEvent<T> event = new SettingChangeEvent<>(this, oldValue, value);
        MinecraftForge.EVENT_BUS.post(event);
    }

    public boolean isVisible() {
        for (Predicate<Object> predicate : this.visibility) {
            if (predicate.test(this)) continue;
            return false;
        }
        return true;
    }


}
