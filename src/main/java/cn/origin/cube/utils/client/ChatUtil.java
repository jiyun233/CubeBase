package cn.origin.cube.utils.client;

import cn.origin.cube.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ChatUtil {
    public static char COLOR_PREFIX = '\u00A7';

    public static final String BLACK = COLOR_PREFIX + "0";
    public static final String DARK_BLUE = COLOR_PREFIX + "1";
    public static final String DARK_GREEN = COLOR_PREFIX + "2";
    public static final String DARK_AQUA = COLOR_PREFIX + "3";
    public static final String DARK_RED = COLOR_PREFIX + "4";
    public static final String DARK_PURPLE = COLOR_PREFIX + "5";
    public static final String GOLD = COLOR_PREFIX + "6";
    public static final String GRAY = COLOR_PREFIX + "7";
    public static final String DARK_GRAY = COLOR_PREFIX + "8";
    public static final String BLUE = COLOR_PREFIX + "9";
    public static final String GREEN = COLOR_PREFIX + "a";
    public static final String AQUA = COLOR_PREFIX + "b";
    public static final String RED = COLOR_PREFIX + "c";
    public static final String LIGHT_PURPLE = COLOR_PREFIX + "d";
    public static final String YELLOW = COLOR_PREFIX + "e";
    public static final String WHITE = COLOR_PREFIX + "f";
    public static final String OBFUSCATED = COLOR_PREFIX + "k";
    public static final String BOLD = COLOR_PREFIX + "l";
    public static final String STRIKE_THROUGH = COLOR_PREFIX + "m";
    public static final String UNDER_LINE = COLOR_PREFIX + "n";
    public static final String ITALIC = COLOR_PREFIX + "o";
    public static final String RESET = COLOR_PREFIX + "r";


    public static final String PREFIX = translateAlternateColorCodes("&8[&9&lCube&bBase&8]&r");

    public static String translateAlternateColorCodes(String textToTranslate) {
        char[] b = textToTranslate.toCharArray();
        for (int i = 0; i < b.length - 1; i++) {
            if (b[i] == '&' && "0123456789AaBbCcDdEeFfKkLlMmNnOoRrXx".indexOf(b[i + 1]) > -1) {
                b[i] = COLOR_PREFIX;
                b[i + 1] = Character.toLowerCase(b[i + 1]);
            }
        }
        return new String(b);
    }


    public static void sendRawMessage(String message) {
        Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new TextComponentString(message));
    }

    public static void sendColoredMessage(String message) {
        sendRawMessage(translateAlternateColorCodes(message));
    }


    public static void sendMessage(String message) {
        sendColoredMessage(PREFIX + message);
    }

    @SideOnly(Side.CLIENT)
    public static void sendNoSpamRawMessage(String message) {
        if (Utils.nullCheck()) return;
        final GuiNewChat chat = Minecraft.getMinecraft().ingameGUI.getChatGUI();
        chat.printChatMessageWithOptionalDeletion(new TextComponentString(message), 135820);
    }

    public static void sendNoSpamColoredMessage(String message) {
        sendNoSpamRawMessage(translateAlternateColorCodes(message));
    }

    public static void sendNoSpamMessage(String message) {
        sendNoSpamColoredMessage(PREFIX + message);
    }
}
