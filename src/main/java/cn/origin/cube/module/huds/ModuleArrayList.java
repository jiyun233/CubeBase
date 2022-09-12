package cn.origin.cube.module.huds;

import cn.origin.cube.Cube;
import cn.origin.cube.module.*;
import cn.origin.cube.module.modules.client.ClickGui;
import cn.origin.cube.settings.ModeSetting;

import java.util.Comparator;
import java.util.stream.Collectors;

@HudModuleInfo(name = "ModuleArrayList", descriptions = "Show all enable module", category = Category.HUD, y = 100, x = 100)
public class ModuleArrayList extends HudModule {

    public ModeSetting<?> alignSetting = registerSetting("Align", alignMode.Left);
    public ModeSetting<?> sortSetting = registerSetting("Sort", sortMode.Top);
    public int count = 0;

    @Override
    public void onRender2D() {
        count = 0;
        Cube.moduleManager.getModuleList().stream()
                .filter(AbstractModule::isEnabled)
                .filter(module -> module.visible.getValue())
                .sorted(Comparator.comparing(module -> Cube.fontManager.CustomFont.getStringWidth(module.getFullHud())
                        * (sortSetting.getValue().equals(sortMode.Bottom) ? 1 : -1)))
                .forEach(module -> {
                    float modWidth = Cube.fontManager.CustomFont.getStringWidth(module.getFullHud());
                    String modText = module.getFullHud();
                    if (alignMode.Right.equals(alignSetting.getValue())) {
                        Cube.fontManager.CustomFont.drawStringWithShadow(modText,
                                (int) (this.x - 2 - modWidth + this.width),
                                this.y + (10 * count),
                                ClickGui.getCurrentColor().getRGB());
                    } else {
                        Cube.fontManager.CustomFont.drawStringWithShadow(modText,
                                this.x - 2,
                                this.y + (10 * count),
                                ClickGui.getCurrentColor().getRGB());
                    }
                    count++;
                });
        width = Cube.moduleManager.getModuleList().stream()
                .filter(AbstractModule::isEnabled)
                .noneMatch(module -> module.visible.getValue()) ? 20 :
                Cube.fontManager.CustomFont.getStringWidth(Cube.moduleManager.getModuleList()
                        .stream().filter(AbstractModule::isEnabled)
                        .filter(module -> module.visible.getValue())
                        .sorted(Comparator.comparing(module -> Cube.fontManager.CustomFont.getStringWidth(module.getFullHud()) * (-1)))
                        .collect(Collectors.toList()).get(0).getFullHud());
        height = ((Cube.fontManager.CustomFont.getHeight() + 1) *
                (int) Cube.moduleManager.getModuleList().stream()
                        .filter(AbstractModule::isEnabled).count());
    }

    enum alignMode {
        Left,
        Right
    }

    enum sortMode {
        Top,
        Bottom
    }
}
