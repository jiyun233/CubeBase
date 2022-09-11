package cn.origin.cube.module;

import cn.origin.cube.event.events.world.Render3DEvent;
import cn.origin.cube.settings.*;
import cn.origin.cube.utils.client.ChatUtil;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;

import java.util.ArrayList;

public abstract class AbstractModule {
    public static final Minecraft mc = Minecraft.getMinecraft();
    public String name;
    public String descriptions;
    public boolean toggle;
    public Category category;
    public boolean isHud;
    public float x;
    public float y;
    public float width;
    public float height;

    public final BindSetting keyBind = new BindSetting("KeyBind", new BindSetting.KeyBind(0), this);

    public final ArrayList<Setting<?>> settingList = new ArrayList<>();

    public final ArrayList<Setting<?>> commonSettings = new ArrayList<>();

    /**
     * Check world or player is null to avoid NullPointerException
     *
     * @return nullable as boolean
     */
    public boolean fullNullCheck() {
        return mc.world == null || mc.player == null;
    }

    /**
     * when module enable runs
     */
    public void onEnable() {
    }

    /**
     * when module disable runs
     */
    public void onDisable() {
    }

    /**
     * when player login world runs
     */
    public void onLogin() {
    }

    /**
     * when player logout world runs
     */
    public void onLogout() {
    }

    /**
     * on Client Update
     */
    public void onUpdate() {
    }

    public void onRender3D(Render3DEvent event) {
    }

    public void onRender2D() {
    }

    public void enable() {
        this.toggle = true;
        MinecraftForge.EVENT_BUS.register(this);
        this.onEnable();
    }

    public void disable() {
        this.toggle = false;
        MinecraftForge.EVENT_BUS.unregister(this);
        this.onDisable();
    }

    public void toggle() {
        if (this.toggle) {
            this.disable();
        } else {
            this.enable();
        }
    }


    public BooleanSetting registerSetting(String name, boolean value) {
        BooleanSetting setting = new BooleanSetting(name, value, this);
        this.settingList.add(setting);
        return setting;
    }

    public IntegerSetting registerSetting(String name, int currentValue, int minValue, int maxValue) {
        IntegerSetting setting = new IntegerSetting(name, currentValue, minValue, maxValue, this);
        this.settingList.add(setting);
        return setting;
    }

    public FloatSetting registerSetting(String name, float currentValue, float minValue, float maxValue) {
        FloatSetting setting = new FloatSetting(name, currentValue, minValue, maxValue, this);
        this.settingList.add(setting);
        return setting;
    }

    public DoubleSetting registerSetting(String name, double currentValue, double minValue, double maxValue) {
        DoubleSetting setting = new DoubleSetting(name, currentValue, minValue, maxValue, this);
        this.settingList.add(setting);
        return setting;
    }

    public LongSetting registerSetting(String name, long currentValue, long minValue, long maxValue) {
        LongSetting setting = new LongSetting(name, currentValue, minValue, maxValue, this);
        this.settingList.add(setting);
        return setting;
    }

    public BindSetting registerSetting(String name, BindSetting.KeyBind key) {
        BindSetting setting = new BindSetting(name, key, this);
        this.settingList.add(setting);
        return setting;
    }

    public StringSetting registerSetting(String name, String key) {
        StringSetting setting = new StringSetting(name, key, this);
        this.settingList.add(setting);
        return setting;
    }

    public <T extends Enum<T>> ModeSetting<T> registerSetting(String name, Enum<?> mode){
        ModeSetting<T> value = new ModeSetting(name,mode,this);
        settingList.add(value);
        return value;
    }


    public boolean isEnabled() {
        return toggle;
    }

    public String getHudInfo() {
        return null;
    }

    public String getFullHud() {
        return this.name + (getHudInfo() == null ? "" : ChatUtil.translateAlternateColorCodes(" &8[&r&l" + getHudInfo() + "&8]"));
    }
}
