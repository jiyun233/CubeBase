package cn.origin.cube.settings;

import cn.origin.cube.module.AbstractModule;

public class StringSetting extends Setting<String>{
    public StringSetting(String name, String value, AbstractModule father) {
        super(name, value, father);
    }
}
