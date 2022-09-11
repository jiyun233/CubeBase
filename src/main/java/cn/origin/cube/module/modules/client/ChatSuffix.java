package cn.origin.cube.module.modules.client;

import cn.origin.cube.Cube;
import cn.origin.cube.event.events.client.PacketEvent;
import cn.origin.cube.module.Category;
import cn.origin.cube.module.Module;
import cn.origin.cube.module.ModuleInfo;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@ModuleInfo(name = "ChatSuffix", descriptions = "Chat suffix", category = Category.CLIENT)
public class ChatSuffix extends Module {

    @SubscribeEvent
    public void onPacketSend(PacketEvent.Send event) {
        if (event.getStage() == 0) {
            if (event.getPacket() instanceof CPacketChatMessage) {
                String s = ((CPacketChatMessage) event.getPacket()).getMessage();
                if (s.startsWith("/")) return;
                s += " | " + Cube.MOD_NAME + " " + Cube.MOD_VERSION;
                if (s.length() >= 256) s = s.substring(0, 256);
                ((CPacketChatMessage) event.getPacket()).message = s;
            }
        }
    }
}