package net.mcreator.whitestatue.init;

import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.mcreator.whitestatue.configuration.WhiteStatueMainConfigConfiguration;
import net.mcreator.whitestatue.WhitestatueMod;

@Mod.EventBusSubscriber(modid = WhitestatueMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WhitestatueModConfigs {
	@SubscribeEvent
	public static void register(FMLConstructModEvent event) {
		event.enqueueWork(() -> {
			ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, WhiteStatueMainConfigConfiguration.SPEC, "WhiteStatueMainConfig.toml");
		});
	}
}
