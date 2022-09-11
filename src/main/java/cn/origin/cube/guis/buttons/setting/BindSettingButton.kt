package cn.origin.cube.guis.buttons.setting

import cn.origin.cube.Cube
import cn.origin.cube.guis.buttons.ModuleButton
import cn.origin.cube.guis.buttons.SettingButton
import cn.origin.cube.settings.BindSetting
import cn.origin.cube.settings.BindSetting.KeyBind
import cn.origin.cube.utils.render.Render2DUtil
import org.lwjgl.input.Keyboard
import java.awt.Color

class BindSettingButton(
    width: Float,
    height: Float,
    value: BindSetting,
    father: ModuleButton
) : SettingButton<BindSetting>(width, height, value, father) {

    private var listening = false

    override fun drawButton(x: Float, y: Float, mouseX: Int, mouseY: Int) {
        Render2DUtil.drawRect(x, y, this.width, this.height, Color(15, 15, 15, 95).rgb)
        Cube.fontManager!!.CustomFont.drawStringWithShadow(
            "${value.name}: ${if (listening) "..." else Keyboard.getKeyName((value.value as KeyBind).keyCode)}",
            x + 3,
            y + (height / 2) - (Cube.fontManager!!.CustomFont.height / 4),
            Color.WHITE.rgb
        )
        this.x = x
        this.y = y
    }

    override fun mouseClicked(mouseX: Int, mouseY: Int, mouseButton: Int) {
        if (!isHoveredButton(mouseX, mouseY) || !value.isVisible || !father.isShowSettings) {
            return
        }
        when (mouseButton) {
            0 -> listening = true
            1 -> value.value = value.defaultValue
        }
    }

    override fun keyTyped(typedChar: Char, keyCode: Int) {
        if (this.listening) {
            (value as BindSetting).value = KeyBind(keyCode)
            this.listening = false
        }
    }
}