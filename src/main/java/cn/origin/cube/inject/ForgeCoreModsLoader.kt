package cn.origin.cube.inject

import cn.origin.cube.Cube
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin.MCVersion
import org.spongepowered.asm.launch.MixinBootstrap
import org.spongepowered.asm.mixin.MixinEnvironment
import org.spongepowered.asm.mixin.Mixins

@MCVersion("1.12.2")
@IFMLLoadingPlugin.Name("Cube")
class ForgeCoreModsLoader : IFMLLoadingPlugin {
    init {
        MixinBootstrap.init()
        Mixins.addConfiguration("mixins.cube.json")
        MixinEnvironment.getDefaultEnvironment().obfuscationContext = "searge"
        Cube.logger.info(MixinEnvironment.getDefaultEnvironment().obfuscationContext)
    }
    override fun getASMTransformerClass(): Array<String?> { return arrayOfNulls(0) }
    override fun getModContainerClass(): String? { return null }
    override fun getSetupClass(): String? { return null }
    override fun injectData(data: Map<String, Any>) {}
    override fun getAccessTransformerClass(): String? { return null }
}