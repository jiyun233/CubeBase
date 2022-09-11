package cn.origin.cube.inject.client;

import cn.origin.cube.Cube;
import cn.origin.cube.event.events.client.DisplayGuiScreenEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.crash.CrashReport;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(Minecraft.class)
public class MixinMinecraft {
    @Redirect(method = {"run"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;displayCrashReport(Lnet/minecraft/crash/CrashReport;)V"))
    public void onCrashReport(Minecraft minecraft, CrashReport crashReport) {
        this.saveNekoConfiguration();
    }

    @Inject(method = {"shutdown"}, at = @At(value = "HEAD"))
    public void shutdown(CallbackInfo info) {
        this.saveNekoConfiguration();
    }

    @Inject(method = "displayGuiScreen", at = @At("HEAD"), cancellable = true)
    public void displayGuiScreen(GuiScreen guiScreenIn, CallbackInfo info) {
        DisplayGuiScreenEvent.Closed closeEvent = new DisplayGuiScreenEvent.Closed(Minecraft.getMinecraft().currentScreen);
        MinecraftForge.EVENT_BUS.post(closeEvent);
        DisplayGuiScreenEvent.Displayed displayEvent = new DisplayGuiScreenEvent.Displayed(guiScreenIn);
        MinecraftForge.EVENT_BUS.post(displayEvent);
    }

    public void saveNekoConfiguration() {
        Cube.logger.warn("Saving configuration please wait...");
        Objects.requireNonNull(Cube.configManager).saveAll();
        Cube.logger.warn("Configuration saved!");
    }
}
