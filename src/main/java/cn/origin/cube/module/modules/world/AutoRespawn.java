package cn.origin.cube.module.modules.world;

import cn.origin.cube.module.Category;
import cn.origin.cube.module.Module;
import cn.origin.cube.module.ModuleInfo;
import net.minecraft.client.gui.GuiGameOver;

@ModuleInfo(name = "AutoRespawn", descriptions = "Anti Death Screen", category = Category.WORLD)
public class AutoRespawn extends Module {
    @Override
    public void onUpdate() {
        if (AutoRespawn.mc.currentScreen instanceof GuiGameOver && AutoRespawn.mc.player.getHealth() >= 0.0f) {
            AutoRespawn.mc.player.respawnPlayer();
            AutoRespawn.mc.displayGuiScreen(null);
        }
    }
}
