package cn.origin.cube.settings;

import cn.origin.cube.module.AbstractModule;

public class BooleanSetting extends Setting<Boolean>{
    public BooleanSetting(String name, Boolean value, AbstractModule father) {
        super(name, value, father);
    }
    public BooleanSetting booleanVisible(BooleanSetting setting) {
        return (BooleanSetting) this.visible(v -> setting.getValue());
    }

    public BooleanSetting booleanDisVisible(BooleanSetting setting) {
        return (BooleanSetting) this.visible(v -> !setting.getValue());
    }

    public BooleanSetting modeVisible(ModeSetting<?> setting, Enum<?> currentValue) {
        return (BooleanSetting) this.visible(v -> setting.getValue().equals(currentValue));
    }

    public BooleanSetting modeOrVisible(ModeSetting<?> setting, Enum<?> currentValue, Enum<?> secondValue) {
        return (BooleanSetting) this.visible(v -> setting.getValue().equals(currentValue) || setting.getValue().equals(secondValue));
    }

    public BooleanSetting modeDisVisible(ModeSetting<?> setting, Enum<?> currentValue) {
        return (BooleanSetting) this.visible(v -> !setting.getValue().equals(currentValue));
    }
}
