package cn.origin.cube.event.events.client;

import cn.origin.cube.event.events.EventStage;
import net.minecraft.client.gui.GuiScreen;

public class DisplayGuiScreenEvent
        extends EventStage {
    private GuiScreen screen;

    public DisplayGuiScreenEvent(GuiScreen screen) {
        this.screen = screen;
    }

    public GuiScreen getScreen() {
        return this.screen;
    }

    public void setScreen(GuiScreen screen) {
        this.screen = screen;
    }

    public static class Closed
            extends DisplayGuiScreenEvent {
        public Closed(GuiScreen screen) {
            super(screen);
        }
    }

    public static class Displayed
            extends DisplayGuiScreenEvent {
        public Displayed(GuiScreen screen) {
            super(screen);
        }
    }
}

