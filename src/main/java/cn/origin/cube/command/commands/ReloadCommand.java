package cn.origin.cube.command.commands;

import cn.origin.cube.Cube;
import cn.origin.cube.command.Command;
import cn.origin.cube.command.CommandInfo;
import cn.origin.cube.utils.client.ChatUtil;

@CommandInfo(name = "reload", aliases = {"reloadConfig"}, descriptions = "reload configuration file", usage = "reload")
public class ReloadCommand extends Command {

    @Override
    public void execute(String[] args) {
        Cube.configManager.loadAll();
        ChatUtil.sendMessage("&aSuccess reload all configurations");
    }
}
