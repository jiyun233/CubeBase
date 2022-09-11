package cn.origin.cube.command.commands;

import cn.origin.cube.Cube;
import cn.origin.cube.command.Command;
import cn.origin.cube.command.CommandInfo;
import cn.origin.cube.utils.client.ChatUtil;

import java.util.Arrays;

@CommandInfo(name = "help", aliases = {"?", "h"}, descriptions = "Show command list", usage = "Help")
public class HelpCommand extends Command {
    @Override
    public void execute(String[] args) {
        ChatUtil.sendMessage("Commands list:");
        for (Command command : Cube.commandManager.getCommands()) {
            ChatUtil.sendColoredMessage("&bCommand: &6" + command.name + "&b " + command.descriptions + " &bUsage: " + command.usage + " &bAliases: " + Arrays.toString(command.aliases));
        }
    }
}
