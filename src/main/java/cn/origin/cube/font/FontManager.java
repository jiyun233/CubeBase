package cn.origin.cube.font;

import cn.origin.cube.Cube;

import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class FontManager {
    public MinecraftFontRenderer CustomFont;

    public MinecraftFontRenderer IconFont;

    public FontManager() throws IOException, FontFormatException {
        CustomFont = new MinecraftFontRenderer(Font.createFont(Font.PLAIN, Objects.requireNonNull(Cube.class.getResourceAsStream("/assets/fonts/CustomFont.ttf"))).deriveFont(38f));
        IconFont = new MinecraftFontRenderer(Font.createFont(Font.PLAIN, Objects.requireNonNull(Cube.class.getResourceAsStream("/assets/fonts/CubeBaseIcon.ttf"))).deriveFont(38f));
    }

    public MinecraftFontRenderer getCustomFont(float size) {
        try {
            return new MinecraftFontRenderer(Font.createFont(Font.PLAIN,Objects.requireNonNull(Cube.class.getResourceAsStream("/assets/fonts/CustomFont.ttf"))).deriveFont(size));
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public MinecraftFontRenderer getIconFont(float size) {
        try {
            return new MinecraftFontRenderer(Font.createFont(Font.PLAIN,Objects.requireNonNull(Cube.class.getResourceAsStream("/assets/fonts/CubeBaseIcon.ttf"))).deriveFont(size));
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
