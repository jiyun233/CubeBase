package cn.origin.cube.inject.client;

import cn.origin.cube.event.events.player.UpdateWalkingPlayerEvent;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPlayerSP.class)
public class MixinEntityPlayerSP {

    @Inject(method = "onUpdateWalkingPlayer",at = @At("RETURN"))
    private void onUpdateWalkingPlayer(CallbackInfo ci){
        UpdateWalkingPlayerEvent event = new UpdateWalkingPlayerEvent();
        MinecraftForge.EVENT_BUS.post(event);
    }
}
