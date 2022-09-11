package cn.origin.cube.settings;

import cn.origin.cube.module.AbstractModule;

public abstract class NumberSetting<T extends Number> extends Setting<T> {

    public NumberSetting(String name, T value, AbstractModule father) {
        super(name, value, father);
    }
}
