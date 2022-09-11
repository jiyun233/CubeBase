package cn.origin.cube.guis.buttons

import cn.origin.cube.guis.CategoryPanel
import net.minecraft.client.Minecraft

abstract class Button(
    var width: Float,
    var height: Float,
    var panelFather: CategoryPanel
) {
    val mc: Minecraft = Minecraft.getMinecraft()
    var x: Float = 0.0f;
    var y: Float = 0.0f;

    abstract fun drawButton(x: Float, y: Float, mouseX: Int, mouseY: Int)

    open fun mouseClicked(mouseX: Int, mouseY: Int, mouseButton: Int) {}
    open fun mouseReleased(mouseX: Int, mouseY: Int, mouseButton: Int) {}
    fun isHoveredButton(mouseX: Int, mouseY: Int): Boolean {
        return mouseX > x && mouseX < x + this.width && mouseY > y && mouseY < y + this.height
    }

    open fun keyTyped(typedChar: Char, keyCode: Int) {}
}