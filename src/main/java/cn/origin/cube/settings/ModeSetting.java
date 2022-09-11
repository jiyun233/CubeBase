package cn.origin.cube.settings;

import cn.origin.cube.module.AbstractModule;

import java.util.Arrays;


public class ModeSetting<T extends Enum<T>> extends Setting<T> {

    private int index;

    public ModeSetting(String name, T clazz, AbstractModule father) {
        super(name, clazz, father);
        this.index = getIndexMode(clazz);
    }

    public <E extends Enum<E>> ModeSetting<E> booleanVisible(BooleanSetting setting) {
        return (ModeSetting<E>) super.visible((Object v) -> setting.getValue());
    }

    public <E extends Enum<E>> ModeSetting<E> boolean2NVisible(BooleanSetting setting, BooleanSetting setting2) {
        boolean b = setting.getValue() || setting2.getValue();
        return (ModeSetting<E>) super.visible((Object v) -> b);
    }

    public String getValueAsString() {
        return getValue().toString();
    }

    public void setValueByString(String str) {
        setValue((T) Enum.valueOf(getValue().getClass(), str));
    }

    @Override
    public void setValue(T value) {
        super.setValue(value);
        index = getIndexMode(getValue());
    }

    public void forwardLoop() {
        this.index = this.index < this.getModes().length - 1 ? ++this.index : 0;
        setValue(this.getModes()[index]);
    }

    public int getIndexMode(T clazz) {
        for (int E = 0; E < getModes().length; E++) {
            if (getModes()[E] == clazz) {
                return E;
            }
        }
        return 0;
    }

    public T[] getModes() {
        return (T[]) this.getValue().getClass().getEnumConstants();
    }

    public String[] getModesAsStrings() {
        return Arrays.stream(getModes()).map(Enum::toString).toArray(String[]::new);
    }

    public <E extends Enum<E>> ModeSetting<E> booleanDisVisible(BooleanSetting setting) {
        return (ModeSetting<E>) super.visible((Object v) -> !setting.getValue());
    }

    public <E extends Enum<E>> ModeSetting<E> modeVisible(ModeSetting<?> value, Enum<?> mode) {
        this.visibility.add(v -> value.getValue() == mode);
        return (ModeSetting<E>) this;
    }
}
