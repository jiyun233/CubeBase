package cn.origin.cube.command.commands

import cn.origin.cube.Cube
import cn.origin.cube.command.Command
import cn.origin.cube.command.CommandInfo
import cn.origin.cube.utils.client.ChatUtil

@CommandInfo(name = "prefix", descriptions = "Change command prefix", usage = "prefix <char>")
class PreFixCommand : Command() {
    override fun execute(args: Array<String>) {
        if (args.isEmpty()) {
            ChatUtil.sendMessage("&c&lUsage: prefix <char>")
            return
        }
        Cube.commandPrefix = args[0]
        Cube.configManager.saveCommand()
        ChatUtil.sendNoSpamMessage("&aPrefix set to ${args[0]}")
    }
}