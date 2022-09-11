package cn.origin.cube.settings;

import cn.origin.cube.module.AbstractModule;

public class BindSetting extends Setting<BindSetting.KeyBind> {
    public BindSetting(String name, KeyBind value, AbstractModule father) {
        super(name, value, father);
    }

    public BindSetting booleanVisible(BooleanSetting setting) {
        return (BindSetting) this.visible(v -> setting.getValue());
    }

    public BindSetting booleanDisVisible(BooleanSetting setting) {
        return (BindSetting) this.visible(v -> !setting.getValue());
    }

    public BindSetting modeVisible(ModeSetting<?> setting, Enum<?> currentValue) {
        return (BindSetting) this.visible(v -> setting.getValue().equals(currentValue));
    }

    public BindSetting modeOrVisible(ModeSetting<?> setting, Enum<?> currentValue, Enum<?> secondValue) {
        return (BindSetting) this.visible(v -> setting.getValue().equals(currentValue) || setting.getValue().equals(secondValue));
    }

    public BindSetting modeDisVisible(ModeSetting<?> setting, Enum<?> currentValue) {
        return (BindSetting) this.visible(v -> !setting.getValue().equals(currentValue));
    }
    
    public static class KeyBind {
        private int keyCode;

        public KeyBind(int keyCode) {
            this.keyCode = keyCode;
        }

        public int getKeyCode() {
            return keyCode;
        }

        public void setKeyCode(int keyCode) {
            this.keyCode = keyCode;
        }
        
    }
}
