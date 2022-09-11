package cn.origin.cube.settings;

import cn.origin.cube.module.AbstractModule;

public class IntegerSetting extends NumberSetting<Integer>{

    public Integer maxValue;
    public Integer minValue;
    public IntegerSetting(String name, Integer value, Integer minValue, Integer maxValue, AbstractModule father) {
        super(name, value, father);
        this.maxValue = maxValue;
        this.minValue = minValue;
    }

    public IntegerSetting booleanVisible(BooleanSetting setting) {
        return (IntegerSetting) this.visible(v -> setting.getValue());
    }

    public IntegerSetting booleanDisVisible(BooleanSetting setting) {
        return (IntegerSetting) this.visible(v -> !setting.getValue());
    }

    public IntegerSetting modeVisible(ModeSetting<?> setting, Enum<?> currentValue) {
        return (IntegerSetting) this.visible(v -> setting.getValue().equals(currentValue));
    }

    public IntegerSetting modeOrVisible(ModeSetting<?> setting, Enum<?> currentValue, Enum<?> secondValue) {
        return (IntegerSetting) this.visible(v -> setting.getValue().equals(currentValue) || setting.getValue().equals(secondValue));
    }

    public IntegerSetting modeDisVisible(ModeSetting<?> setting, Enum<?> currentValue) {
        return (IntegerSetting) this.visible(v -> !setting.getValue().equals(currentValue));
    }
}
