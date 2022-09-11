package cn.origin.cube.module.modules.world;

import cn.origin.cube.module.Category;
import cn.origin.cube.module.Module;
import cn.origin.cube.module.ModuleInfo;
import cn.origin.cube.settings.StringSetting;
import cn.origin.cube.utils.client.ChatUtil;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.entity.EntityOtherPlayerMP;

import java.util.UUID;

@ModuleInfo(name = "FakePlayer", descriptions = "Spawn other player", category = Category.WORLD)
public class FakePlayer extends Module {
    private final StringSetting name = registerSetting("name", "FakePlayer");
    private EntityOtherPlayerMP otherPlayer;

    @Override
    public void onEnable() {
        if (fullNullCheck()) {
            this.disable();
            return;
        }
        this.otherPlayer = null;
        if (mc.player != null) {
            this.otherPlayer = new EntityOtherPlayerMP(mc.world, new GameProfile(UUID.randomUUID(), this.name.getValue()));
            ChatUtil.sendMessage(ChatUtil.AQUA + String.format("%s has been spawned.", this.name.getValue()));
            this.otherPlayer.copyLocationAndAnglesFrom(mc.player);
            this.otherPlayer.rotationYawHead = mc.player.rotationYawHead;
            mc.world.addEntityToWorld(-100, this.otherPlayer);
        }
    }

    @Override
    public void onDisable() {
        if (mc.world != null && mc.player != null) {
            super.onDisable();
            if (otherPlayer == null) return;
            mc.world.removeEntity(this.otherPlayer);
        }
    }
}

