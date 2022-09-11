package cn.origin.cube.settings;

import cn.origin.cube.module.AbstractModule;

public class FloatSetting extends NumberSetting<Float>{

    public Float maxValue;
    public Float minValue;
    public FloatSetting(String name, Float value, Float minValue, Float maxValue, AbstractModule father) {
        super(name, value, father);
        this.maxValue = maxValue;
        this.minValue = minValue;
    }
    public FloatSetting booleanVisible(BooleanSetting setting) {
        return (FloatSetting) this.visible(v -> setting.getValue());
    }

    public FloatSetting booleanDisVisible(BooleanSetting setting) {
        return (FloatSetting) this.visible(v -> !setting.getValue());
    }

    public FloatSetting modeVisible(ModeSetting<?> setting, Enum<?> currentValue) {
        return (FloatSetting) this.visible(v -> setting.getValue().equals(currentValue));
    }

    public FloatSetting modeOrVisible(ModeSetting<?> setting, Enum<?> currentValue, Enum<?> secondValue) {
        return (FloatSetting) this.visible(v -> setting.getValue().equals(currentValue) || setting.getValue().equals(secondValue));
    }

    public FloatSetting modeDisVisible(ModeSetting<?> setting, Enum<?> currentValue) {
        return (FloatSetting) this.visible(v -> !setting.getValue().equals(currentValue));
    }
}
