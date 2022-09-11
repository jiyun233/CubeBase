package cn.origin.cube.event.events.client;

import cn.origin.cube.event.events.EventStage;
import cn.origin.cube.settings.Setting;

public class SettingChangeEvent<T> extends EventStage {
    public final Setting<?> setting;

    public final T oldValue;

    public final T newValue;

    public SettingChangeEvent(Setting<?> setting, T oldValue, T newValue) {
        this.setting = setting;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }
}
