package cn.origin.cube.module.modules.world;

import cn.origin.cube.event.events.client.DisplayGuiScreenEvent;
import cn.origin.cube.module.Category;
import cn.origin.cube.module.Module;
import cn.origin.cube.module.ModuleInfo;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@ModuleInfo(name = "AutoRespawn", descriptions = "Anti Death Screen", category = Category.WORLD)
public class AutoRespawn extends Module {

    @SubscribeEvent
    public void onDisplayGui(DisplayGuiScreenEvent event){
        if (event.getScreen() instanceof GuiGameOver){
            mc.displayGuiScreen(null);
            mc.player.respawnPlayer();
        }
    }
}
