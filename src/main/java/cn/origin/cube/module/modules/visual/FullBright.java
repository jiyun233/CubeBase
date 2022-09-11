package cn.origin.cube.module.modules.visual;

import cn.origin.cube.module.Category;
import cn.origin.cube.module.Module;
import cn.origin.cube.module.ModuleInfo;
import cn.origin.cube.settings.FloatSetting;
import cn.origin.cube.settings.IntegerSetting;
import cn.origin.cube.settings.ModeSetting;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;

@ModuleInfo(name = "FullBright", descriptions = "Always light", category = Category.VISUAL)
public class FullBright extends Module {

    ModeSetting<?> modeSetting = registerSetting("Mode", mode.GAMMA);
    FloatSetting gamma = registerSetting("Gamma", 800f, -10f, 1000f).modeOrVisible(modeSetting, mode.GAMMA, mode.BOTH);

    @Override
    public void onEnable() {
        if (modeSetting.getValue().equals(mode.GAMMA) || modeSetting.getValue().equals(mode.BOTH)) {
            mc.gameSettings.gammaSetting = gamma.getValue();
        }
        if (modeSetting.getValue().equals(mode.POTION) || modeSetting.getValue().equals(mode.BOTH)) {
            mc.player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 100));
        }
    }

    @Override
    public void onDisable() {
        mc.gameSettings.gammaSetting = 1f;
        mc.player.removePotionEffect(MobEffects.NIGHT_VISION);
    }

    private enum mode {
        GAMMA,
        POTION,
        BOTH
    }
}
