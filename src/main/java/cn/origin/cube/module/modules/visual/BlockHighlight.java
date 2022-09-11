package cn.origin.cube.module.modules.visual;

import cn.origin.cube.event.events.world.Render3DEvent;
import cn.origin.cube.module.Category;
import cn.origin.cube.module.Module;
import cn.origin.cube.module.ModuleInfo;
import cn.origin.cube.module.modules.client.ClickGui;
import cn.origin.cube.settings.BooleanSetting;
import cn.origin.cube.settings.FloatSetting;
import cn.origin.cube.settings.IntegerSetting;
import cn.origin.cube.utils.render.Render3DUtil;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;

import java.awt.*;

@ModuleInfo(name = "BlockHighlight", descriptions = "Render current block", category = Category.VISUAL)
public class BlockHighlight extends Module {
    private final BooleanSetting outline = registerSetting("Outline", true);
    private final BooleanSetting full = registerSetting("FullBlock", true);
    private final FloatSetting width = registerSetting("OutlineWidth", 1.5f, 0.0f, 10.0f).booleanVisible(outline);

    private final IntegerSetting alpha = registerSetting("Alpha", 55, 0, 255).booleanVisible(full);

    @Override
    public void onRender3D(Render3DEvent event) {
        if (fullNullCheck()) return;
        BlockPos blockpos;
        Minecraft mc = Minecraft.getMinecraft();
        RayTraceResult ray = mc.objectMouseOver;
        if (ray != null && ray.typeOfHit == RayTraceResult.Type.BLOCK && mc.world.getBlockState(blockpos = ray.getBlockPos()).getMaterial() != Material.AIR && mc.world.getWorldBorder().contains(blockpos)) {
            Render3DUtil.drawBlockBox(blockpos, new Color(ClickGui.getCurrentColor().getRed(), ClickGui.getCurrentColor().getGreen(), ClickGui.getCurrentColor().getBlue(), full.getValue() ? alpha.getValue() : 0), outline.getValue(), width.getValue());
        }
    }
}

