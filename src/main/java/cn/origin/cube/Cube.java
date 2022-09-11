package cn.origin.cube;

import cn.origin.cube.command.CommandManager;
import cn.origin.cube.event.EventManager;
import cn.origin.cube.font.FontManager;
import cn.origin.cube.guis.ClickGuiScreen;
import cn.origin.cube.guis.HudEditorScreen;
import cn.origin.cube.managers.ConfigManager;
import cn.origin.cube.managers.FriendManager;
import cn.origin.cube.module.ModuleManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;

import java.awt.*;
import java.io.IOException;

@Mod(modid = Cube.MOD_ID, name = Cube.MOD_NAME, version = Cube.MOD_VERSION)
public class Cube {
    public static final String MOD_ID = "cube";

    public static final String MOD_NAME = "Cube Base";

    public static final String MOD_VERSION = "0.1";
    public static final Logger logger = LogManager.getLogger("Cube");
    public static EventManager eventManager = null;
    public static FontManager fontManager = null;
    public static ClickGuiScreen clickGui = null;
    public static HudEditorScreen hudEditor = null;
    public static FriendManager friendManager = null;
    public static ModuleManager moduleManager = null;
    public static ConfigManager configManager = null;
    public static CommandManager commandManager = null;

    public static String commandPrefix = ".";

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger.info("Begin loading Cube Base");
        Display.setTitle(MOD_NAME + " | " + MOD_VERSION);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        try {
            logger.info("Loading Cube Base...");
            loadManagers();
        } catch (IOException | FontFormatException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadManagers() throws IOException, FontFormatException {
        fontManager = new FontManager();
        friendManager = new FriendManager();
        moduleManager = new ModuleManager();
        eventManager = new EventManager();
        clickGui = new ClickGuiScreen();
        hudEditor = new HudEditorScreen();
        configManager = new ConfigManager();
        commandManager = new CommandManager();
    }
}
