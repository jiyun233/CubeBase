package cn.origin.cube.guis.buttons

import cn.origin.cube.guis.CategoryPanel
import cn.origin.cube.settings.Setting

abstract class SettingButton<T : Setting<*>>(
    width: Float,
    height: Float,
    val value: Setting<*>,
    val father: ModuleButton
) : Button(width, height, father.panelFather) {

}