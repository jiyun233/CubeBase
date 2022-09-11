package cn.origin.cube.module.modules.function

import cn.origin.cube.Cube
import cn.origin.cube.module.Category
import cn.origin.cube.module.Module
import cn.origin.cube.module.ModuleInfo
import cn.origin.cube.settings.ModeSetting
import cn.origin.cube.utils.player.InventoryUtil
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Items
import net.minecraft.item.ItemEnderPearl
import net.minecraft.util.EnumHand
import net.minecraft.util.math.RayTraceResult
import org.lwjgl.input.Mouse

@ModuleInfo(name = "MiddleClick", descriptions = "Click mouse middle button", category = Category.FUNCTION)
class MiddleClick : Module() {
    private var mode: ModeSetting<clickType> = registerSetting("Mode", clickType.ENDRPEARL)
    private var clicked = false
    override fun onUpdate() {
        if (mode.value == clickType.FRIEND) {
            if (mc.currentScreen == null) {
                if (Mouse.isButtonDown(2)) {
                    if (!clicked) {
                        val result = mc.objectMouseOver
                        if (result != null && result.typeOfHit == RayTraceResult.Type.ENTITY) {
                            val entity = result.entityHit
                            if (entity is EntityPlayer) {
                                val name = entity.getName()
                                if (Cube.friendManager!!.isFriend(name)) {
                                    Cube.friendManager!!.remove(name)
                                } else {
                                    Cube.friendManager!!.add(name)
                                }
                            }
                        }
                    }
                    clicked = true
                } else {
                    clicked = false
                }
            }
        } else {
            val oldSlot = mc.player.inventory.currentItem
            if (Mouse.isButtonDown(2)) {
                val var2 = mc.objectMouseOver
                if (var2.typeOfHit != RayTraceResult.Type.ENTITY && var2.typeOfHit != RayTraceResult.Type.BLOCK) {
                    val p = InventoryUtil.findHotbarItem(ItemEnderPearl::class.java)
                    if (p == -1) {
                        return
                    }
                    InventoryUtil.switchToHotbarSlot(p, false)
                    try {
                        mc.playerController.processRightClick(
                            mc.player,
                            mc.world,
                            if (mc.player.heldItemOffhand.item === Items.ENDER_PEARL) EnumHand.OFF_HAND else EnumHand.MAIN_HAND
                        )
                    } catch (ignored: Exception) {
                    }
                    InventoryUtil.switchToHotbarSlot(oldSlot, false)
                }
            }
        }
    }

    enum class clickType {
        FRIEND, ENDRPEARL
    }
}