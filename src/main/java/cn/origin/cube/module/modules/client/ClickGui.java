package cn.origin.cube.module.modules.client;

import cn.origin.cube.Cube;
import cn.origin.cube.guis.ClickGuiScreen;
import cn.origin.cube.managers.ConfigManager;
import cn.origin.cube.module.Category;
import cn.origin.cube.module.Module;
import cn.origin.cube.module.ModuleInfo;
import cn.origin.cube.settings.BooleanSetting;
import cn.origin.cube.settings.FloatSetting;
import cn.origin.cube.settings.IntegerSetting;
import cn.origin.cube.settings.StringSetting;
import org.lwjgl.input.Keyboard;

import java.awt.*;

@ModuleInfo(name = "ClickGui", descriptions = "open click gui screen", category = Category.CLIENT, defaultKeyBind = Keyboard.KEY_RSHIFT)
public final class ClickGui extends Module {
    public BooleanSetting rainbow = registerSetting("Rainbow", false);
    public IntegerSetting red = registerSetting("Red", 25, 0, 255).booleanDisVisible(rainbow);
    public IntegerSetting green = registerSetting("Green", 115, 0, 255).booleanDisVisible(rainbow);
    public IntegerSetting blue = registerSetting("Blue", 255, 0, 255).booleanDisVisible(rainbow);
    public FloatSetting speed = registerSetting("RainbowSpeed", 1.0f, 0.1f, 10.0f).booleanVisible(rainbow);
    public FloatSetting saturation = registerSetting("Saturation", 0.65f, 0.0f, 1.0f).booleanVisible(rainbow);
    public FloatSetting brightness = registerSetting("Brightness", 1.0f, 0.0f, 1.0f).booleanVisible(rainbow);

    public static ClickGui INSTANCE;

    public ClickGui() {
        INSTANCE = this;
    }

    public void onEnable() {
        if (!this.fullNullCheck() && !(Module.mc.currentScreen instanceof ClickGuiScreen)) {
            Module.mc.displayGuiScreen(Cube.clickGui);
        }

    }

    public void onDisable() {
        if (!this.fullNullCheck() && Module.mc.currentScreen instanceof ClickGuiScreen) {
            Module.mc.displayGuiScreen(null);
            ConfigManager configManager = Cube.configManager;
            configManager.saveAll();
        }
    }

    public static Color getRainbow() {
        float hue = (float) (System.currentTimeMillis() % 11520L) / 11520.0f * INSTANCE.speed.getValue();
        return new Color(Color.HSBtoRGB(hue, INSTANCE.saturation.getValue(), INSTANCE.brightness.getValue()));
    }

    public static Color getCurrentColor() {
        return INSTANCE.rainbow.getValue() ? getRainbow() : new Color(INSTANCE.red.getValue(), INSTANCE.green.getValue(), INSTANCE.blue.getValue());
    }
}
