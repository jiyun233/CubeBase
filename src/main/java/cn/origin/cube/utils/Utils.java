package cn.origin.cube.utils;

import net.minecraft.client.Minecraft;

public class Utils {
    public static final Minecraft mc = Minecraft.getMinecraft();
    public static boolean nullCheck() {
        return mc.player == null || mc.world == null;
    }
}
