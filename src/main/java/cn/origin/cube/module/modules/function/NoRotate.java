package cn.origin.cube.module.modules.function;

import cn.origin.cube.event.events.client.PacketEvent;
import cn.origin.cube.module.Category;
import cn.origin.cube.module.Module;
import cn.origin.cube.module.ModuleInfo;
import net.minecraft.network.play.server.SPacketPlayerPosLook;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@ModuleInfo(name = "NoRotate", descriptions = "Dangerous to use might desync you.", category = Category.FUNCTION)
public class NoRotate extends Module {
    private boolean cancelPackets = true;
    private boolean timerReset = false;

    @Override
    public void onUpdate() {
        if (this.timerReset && !this.cancelPackets) {
            this.cancelPackets = true;
            this.timerReset = false;
        }
    }

    @SubscribeEvent
    public void onPacketReceive(PacketEvent.Receive event) {
        if (fullNullCheck()) return;
        if (event.getStage() == 0 && this.cancelPackets && event.getPacket() instanceof SPacketPlayerPosLook) {
            SPacketPlayerPosLook packet = event.getPacket();
            packet.yaw = NoRotate.mc.player.rotationYaw;
            packet.pitch = NoRotate.mc.player.rotationPitch;
        }
    }
}

