package cn.origin.cube.command.commands

import cn.origin.cube.Cube
import cn.origin.cube.command.Command
import cn.origin.cube.command.CommandInfo
import cn.origin.cube.module.AbstractModule
import cn.origin.cube.module.ModuleManager
import cn.origin.cube.settings.BindSetting
import cn.origin.cube.utils.client.ChatUtil
import org.lwjgl.input.Keyboard
import java.util.*


@CommandInfo(name = "bind", aliases = ["KeyBind"],descriptions = "Bind module to key",usage = "bind <module> <key>")
class ModuleBindCommand : Command() {
    override fun execute(args: Array<String>) {
        if (args.size == 1) {
            ChatUtil.sendNoSpamMessage("&cPlease specify a module.")
            return
        }
        try {
            val module = args[0]
            val rKey = args[1]
            val m: AbstractModule? = Cube.moduleManager.getModuleByName(module)
            if (m == null) {
                ChatUtil.sendNoSpamMessage("Unknown module '$module'!")
                return
            }
            val key = Keyboard.getKeyIndex(rKey.uppercase())
            if (Keyboard.KEY_NONE == key) {
                ChatUtil.sendMessage("&cUnknown Key $rKey")
                return
            }
            m.keyBind.value = BindSetting.KeyBind(key)
            ChatUtil.sendMessage("&aSuccess bind ${m.name} to key: ${args[1]}")
        } catch (e: Exception) {
            ChatUtil.sendMessage("&c&lUsage: bind <module> <bind>")
        }
    }
}