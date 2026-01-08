package net.mcreator.whitestatue.network;

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class WhitestatueModVariables {
	public static String UnknownEntityRegId = "\"\"";

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
	}
}
