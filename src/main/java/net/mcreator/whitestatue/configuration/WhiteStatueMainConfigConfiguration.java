package net.mcreator.whitestatue.configuration;

import net.minecraftforge.common.ForgeConfigSpec;

public class WhiteStatueMainConfigConfiguration {
	public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec SPEC;
	public static final ForgeConfigSpec.ConfigValue<String> WHITELISTED;
	public static final ForgeConfigSpec.ConfigValue<String> BLACKLISTED;
	public static final ForgeConfigSpec.IntValue STATUE_LIMIT;
	static {
		WHITELISTED = BUILDER.comment("These entities will not be affected by White Statue.").define("Whitelisted entities registryids", "minecraft:player;corpse:corpse");
		BLACKLISTED = BUILDER.comment("These entities will be despawned by White Statue").define("Blacklisted entities registryids", "minecraft:spider;");
		STATUE_LIMIT = BUILDER.comment("Maximum number of white statues allowed globally.").defineInRange("WhiteStatueLimit", 32, 1, 100000);

		SPEC = BUILDER.build();
	}

}
