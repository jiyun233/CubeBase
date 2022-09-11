package cn.origin.cube.guis.buttons.setting

import cn.origin.cube.Cube
import cn.origin.cube.guis.buttons.ModuleButton
import cn.origin.cube.guis.buttons.SettingButton
import cn.origin.cube.module.modules.client.ClickGui
import cn.origin.cube.settings.*
import cn.origin.cube.utils.render.Render2DUtil
import java.awt.Color
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.roundToInt
import kotlin.math.roundToLong


class NumberSliderButton(width: Float, height: Float, value: Setting<*>, father: ModuleButton) :
    SettingButton<NumberSetting<*>>(width, height, value, father) {
    private lateinit var min: Number
    private lateinit var max: Number
    private var difference = 0
    private var dargging = false

    init {
        when (value) {
            is DoubleSetting -> {
                min = value.minValue
                max = value.maxValue
            }

            is FloatSetting -> {
                min = value.minValue
                max = value.maxValue
            }

            is IntegerSetting -> {
                min = value.minValue
                max = value.maxValue
            }

            is LongSetting -> {
                min = value.minValue
                max = value.maxValue
            }
        }

        difference = max.toInt() - min.toInt()
    }

    override fun drawButton(x: Float, y: Float, mouseX: Int, mouseY: Int) {
        dragSetting(mouseX)
        Render2DUtil.drawRect(x, y, this.width, this.height, Color(15, 15, 15, 95).rgb)
        Render2DUtil.drawRect(
            x,
            y + 0.5f,
            if ((value.value as Number).toDouble() <= min.toDouble()) 0.0f else width * partialMultiplier(),
            height - 1.0f,
            ClickGui.getCurrentColor().rgb
        )
        Cube.fontManager!!.CustomFont.drawStringWithShadow(
            value.name,
            x + 3,
            y + (height / 2) - (Cube.fontManager!!.CustomFont.height / 4),
            Color.WHITE.rgb
        )
        Cube.fontManager!!.CustomFont.drawStringWithShadow(
            when (value) {
                is FloatSetting -> getNoMoreThanOneDigits(value.value)
                is DoubleSetting -> getNoMoreThanTwoDigits(value.value)
                else -> {
                    value.value.toString()
                }
            },
            x + width - 3 - Cube.fontManager!!.CustomFont.getStringWidth(
                when (value) {
                    is FloatSetting -> getNoMoreThanOneDigits(value.value)
                    is DoubleSetting -> getNoMoreThanTwoDigits(value.value)
                    else -> {
                        value.value.toString()
                    }
                }
            ),
            y + (height / 2) - (Cube.fontManager!!.CustomFont.height / 4),
            Color.WHITE.rgb
        )
        this.x = x
        this.y = y
    }

    override fun mouseClicked(mouseX: Int, mouseY: Int, mouseButton: Int) {
        if (isHoveredButton(mouseX, mouseY) && mouseButton == 0 && value.isVisible && panelFather.isShowModules) {
            dargging = true
        }
    }

    override fun mouseReleased(mouseX: Int, mouseY: Int, mouseButton: Int) {
        if (mouseButton == 0) {
            dargging = false
        }
    }

    private fun dragSetting(mouseX: Int) {
        if (dargging) {
            setSettingFromX(mouseX)
        }
    }

    private fun setSettingFromX(mouseX: Int) {
        val percent = (mouseX.toFloat() - x) / width
        when (this.value) {
            is DoubleSetting -> {
                val result = this.value.minValue + (difference.toFloat() * percent).toDouble()
                if (result > this.value.maxValue) {
                    this.value.value = this.value.maxValue
                } else if (result < this.value.minValue) {
                    this.value.value = this.value.minValue
                } else {
                    this.value.value = result
                }
            }

            is FloatSetting -> {
                val result = this.value.minValue + difference.toFloat() * percent
                if (result > this.value.maxValue) {
                    this.value.value = this.value.maxValue
                } else if (result < this.value.minValue) {
                    this.value.value = this.value.minValue
                } else {
                    this.value.value = result
                }
            }

            is IntegerSetting -> {
                val result = this.value.minValue + (difference.toFloat() * percent)
                if (result > this.value.maxValue) {
                    this.value.value = this.value.maxValue
                } else if (result < this.value.minValue) {
                    this.value.value = this.value.minValue
                } else {
                    this.value.value = result.roundToInt()
                }
            }

            is LongSetting -> {
                val result = this.value.minValue + (difference.toFloat() * percent).roundToLong()
                if (result > this.value.maxValue) {
                    this.value.value = this.value.maxValue
                } else if (result < this.value.minValue) {
                    this.value.value = this.value.minValue
                } else {
                    this.value.value = result
                }
            }
        }
    }

    private fun middle(): Float {
        return max.toFloat() - min.toFloat()
    }

    private fun part(): Float {
        return (this.value.value as Number).toFloat() - min.toFloat()
    }

    private fun partialMultiplier(): Float {
        return part() / middle()
    }

    private fun getNoMoreThanTwoDigits(number: Double): String {
        val format = DecimalFormat("0.##")
        format.roundingMode = RoundingMode.FLOOR
        return format.format(number)
    }

    private fun getNoMoreThanOneDigits(number: Float): String {
        val format = DecimalFormat("0.#")
        format.roundingMode = RoundingMode.FLOOR
        return format.format(number)
    }
}