package cn.origin.cube.module.modules.combat;

import cn.origin.cube.event.events.player.UpdateWalkingPlayerEvent;
import cn.origin.cube.module.Category;
import cn.origin.cube.module.Module;
import cn.origin.cube.module.ModuleInfo;
import cn.origin.cube.settings.BooleanSetting;
import cn.origin.cube.utils.player.BlockUtil;
import cn.origin.cube.utils.player.InventoryUtil;
import net.minecraft.block.BlockObsidian;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@ModuleInfo(name = "Surround", descriptions = "Auto place block surround feet", category = Category.COMBAT)
public class Surround extends Module {
    public static BlockPos[] surroundPos = new BlockPos[]{
            new BlockPos(0, -1, 0),
            new BlockPos(1, -1, 0),
            new BlockPos(-1, -1, 0),
            new BlockPos(0, -1, 1),
            new BlockPos(0, -1, -1),
            new BlockPos(1, 0, 0),
            new BlockPos(-1, 0, 0),
            new BlockPos(0, 0, 1),
            new BlockPos(0, 0, -1),
    };
    public int slot;
    public int oldslot;
    public BlockPos startPos;
    public BooleanSetting packet = registerSetting("Packet", true);
    public BooleanSetting rot = registerSetting("Rotate", false);
    public BlockPos newPos2;

    @Override
    public void onEnable() {
        if (fullNullCheck()) return;
        startPos = new BlockPos(Math.floor(mc.player.posX), Math.floor(mc.player.posY), Math.floor(mc.player.posZ));
    }


    @SubscribeEvent
    public void onUpdate(UpdateWalkingPlayerEvent event) {
        if (fullNullCheck()) {
            this.toggle();
            return;
        }
        slot = InventoryUtil.findHotbarBlock(BlockObsidian.class);
        oldslot = mc.player.inventory.currentItem;
        if (startPos != null) {
            if (!startPos.equals(new BlockPos(Math.floor(mc.player.posX), Math.floor(mc.player.posY), Math.floor(mc.player.posZ)))) {
                this.toggle();
                return;
            }
        }
        if (slot == -1) {
            toggle();
            return;
        }
        for (BlockPos pos : surroundPos) {
            newPos2 = addPos(pos);
            if (slot == -1) this.toggle();
            InventoryUtil.switchToHotbarSlot(slot, false);
            BlockUtil.placeBlock(newPos2, EnumHand.MAIN_HAND, rot.getValue(), packet.getValue());
            InventoryUtil.switchToHotbarSlot(oldslot, false);
        }
    }

    public BlockPos addPos(BlockPos pos) {
        BlockPos pPos = new BlockPos(mc.player);
        return new BlockPos(pPos.getX() + pos.getX(), pPos.getY() + pos.getY(), pPos.getZ() + pos.getZ());
    }
}
