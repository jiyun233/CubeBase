package cn.origin.cube.event

import cn.origin.cube.Cube
import cn.origin.cube.event.events.world.Render3DEvent
import cn.origin.cube.utils.Utils
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.GlStateManager
import net.minecraftforge.client.event.ClientChatEvent
import net.minecraftforge.client.event.RenderGameOverlayEvent
import net.minecraftforge.client.event.RenderWorldLastEvent
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.InputEvent
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientConnectedToServerEvent
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientDisconnectionFromServerEvent
import org.lwjgl.input.Keyboard


class EventManager {
    val mc: Minecraft = Minecraft.getMinecraft()

    init {
        MinecraftForge.EVENT_BUS.register(this)
    }

    @SubscribeEvent
    fun onKeyInput(event: InputEvent.KeyInputEvent) {
        if (event.isCanceled || !Keyboard.getEventKeyState() || Keyboard.getEventKey() <= 0) return
        for (module in Cube.moduleManager!!.allModuleList) {
            if (module.keyBind.value.keyCode <= 0) continue
            if (Keyboard.isKeyDown(module.keyBind.value.keyCode)) module.toggle()
        }
    }

    @SubscribeEvent
    fun onTick(event: ClientTickEvent) {
        if (Utils.nullCheck()) return
        Cube.moduleManager!!.onUpdate()
    }

    @SubscribeEvent
    fun onLogin(event: ClientConnectedToServerEvent) {
        if (Utils.nullCheck()) return
        Cube.moduleManager!!.onLogin()
    }

    @SubscribeEvent
    fun onLogout(event: ClientDisconnectionFromServerEvent) {
        if (Utils.nullCheck()) return
        Cube.moduleManager!!.onLogout()
    }


    @SubscribeEvent
    fun onRender2D(e: RenderGameOverlayEvent.Text) {
        if (e.type == RenderGameOverlayEvent.ElementType.TEXT) {
            Cube.moduleManager!!.onRender2D()
        }
    }

    @SubscribeEvent
    fun onWorldRender(event: RenderWorldLastEvent) {
        if (event.isCanceled) return
        mc.profiler.startSection("CubeBase")
        GlStateManager.disableTexture2D()
        GlStateManager.enableBlend()
        GlStateManager.disableAlpha()
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0)
        GlStateManager.shadeModel(7425)
        GlStateManager.disableDepth()
        GlStateManager.glLineWidth(1.0f)
        val render3dEvent = Render3DEvent(event.partialTicks)
        Cube.moduleManager!!.onRender3D(render3dEvent)
        GlStateManager.glLineWidth(1.0f)
        GlStateManager.shadeModel(7424)
        GlStateManager.disableBlend()
        GlStateManager.enableAlpha()
        GlStateManager.enableTexture2D()
        GlStateManager.enableDepth()
        GlStateManager.enableCull()
        GlStateManager.enableCull()
        GlStateManager.depthMask(true)
        GlStateManager.enableTexture2D()
        GlStateManager.enableBlend()
        GlStateManager.enableDepth()
        mc.profiler.endSection()
    }

    @SubscribeEvent
    fun onChat(event: ClientChatEvent) {
        if (event.message.startsWith(Cube.commandPrefix)) {
            Cube.commandManager.run(event.message)
            event.isCanceled = true;
            Minecraft.getMinecraft().ingameGUI.chatGUI.addToSentMessages(event.message);
        }
    }
}