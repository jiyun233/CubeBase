package cn.origin.cube.module.modules.movement;

import cn.origin.cube.module.Category;
import cn.origin.cube.module.Module;
import cn.origin.cube.module.ModuleInfo;
import cn.origin.cube.settings.BooleanSetting;

@ModuleInfo(name = "Sprint", descriptions = "Always run fast", category = Category.MOVEMENT)
public class Sprint extends Module {

    public BooleanSetting Legit = registerSetting("Legit", false);

    @Override
    public void onUpdate() {
        if (fullNullCheck() || mc.player.isElytraFlying() || mc.player.capabilities.isFlying) {
            return;
        }
        if (Legit.getValue()) {
            try {
                mc.player.setSprinting(!mc.player.collidedHorizontally && mc.player.moveForward > 0);
            } catch (Exception ignored) {
            }
        } else {
            if (mc.player.moveForward != 0.0 || mc.player.moveStrafing != 0.0) {
                mc.player.setSprinting(true);
            }
        }
    }

    @Override
    public String getHudInfo() {
        return Legit.getValue() ? "Legit" : "Normal";
    }
}
