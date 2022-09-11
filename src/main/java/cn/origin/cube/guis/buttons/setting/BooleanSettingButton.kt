package cn.origin.cube.guis.buttons.setting

import cn.origin.cube.Cube
import cn.origin.cube.guis.buttons.ModuleButton
import cn.origin.cube.guis.buttons.SettingButton
import cn.origin.cube.module.modules.client.ClickGui
import cn.origin.cube.settings.BooleanSetting
import cn.origin.cube.utils.render.Render2DUtil
import java.awt.Color

class BooleanSettingButton(
    width: Float,
    height: Float,
    value: BooleanSetting,
    father: ModuleButton
) : SettingButton<BooleanSetting>(width, height, value, father) {
    override fun drawButton(x: Float, y: Float, mouseX: Int, mouseY: Int) {
        Render2DUtil.drawRect(x, y, this.width, this.height, Color(15, 15, 15, 95).rgb)
        Cube.fontManager!!.CustomFont.drawStringWithShadow(
            value.name,
            x + 3,
            y + (height / 2) - (Cube.fontManager!!.CustomFont.height / 4),
            if (value.value as Boolean) ClickGui.getCurrentColor().rgb else Color.WHITE.rgb
        )
        this.x = x
        this.y = y
    }

    override fun mouseClicked(mouseX: Int, mouseY: Int, mouseButton: Int) {
        if (!isHoveredButton(mouseX, mouseY) || !value.isVisible || !father.isShowSettings) {
            return
        }
        when (mouseButton) {
            0, 1 -> value.value = !(value.value as Boolean)
        }
    }

}