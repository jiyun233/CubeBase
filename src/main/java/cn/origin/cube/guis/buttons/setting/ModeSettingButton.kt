package cn.origin.cube.guis.buttons.setting

import cn.origin.cube.Cube
import cn.origin.cube.guis.buttons.ModuleButton
import cn.origin.cube.guis.buttons.SettingButton
import cn.origin.cube.settings.ModeSetting
import cn.origin.cube.utils.render.Render2DUtil
import java.awt.Color

class ModeSettingButton(
    width: Float,
    height: Float,
    value: ModeSetting<*>,
    father: ModuleButton
) : SettingButton<ModeSetting<*>>(width, height, value, father) {
    override fun drawButton(x: Float, y: Float, mouseX: Int, mouseY: Int) {
        Render2DUtil.drawRect(x, y, this.width, this.height, Color(15, 15, 15, 95).rgb)
        Cube.fontManager!!.CustomFont.drawStringWithShadow(
            value.name,
            x + 3,
            y + (height / 2) - (Cube.fontManager!!.CustomFont.height / 4),
            Color.WHITE.rgb
        )
        Cube.fontManager!!.CustomFont.drawStringWithShadow(
            (value as ModeSetting<*>).valueAsString,
            x + width - 3 - Cube.fontManager!!.CustomFont.getStringWidth(value.valueAsString),
            y + (height / 2) - (Cube.fontManager!!.CustomFont.height / 4),
            Color.WHITE.rgb
        )
        this.x = x
        this.y = y
    }

    override fun mouseClicked(mouseX: Int, mouseY: Int, mouseButton: Int) {
        if (!this.isHoveredButton(mouseX, mouseY) || !value.isVisible || !father.isShowSettings) {
            return
        }
        if (mouseButton == 0) {
            (this.value as ModeSetting<*>).forwardLoop()
        }
    }
}