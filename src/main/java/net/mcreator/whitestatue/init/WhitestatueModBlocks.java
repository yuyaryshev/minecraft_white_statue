
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.whitestatue.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;

import net.mcreator.whitestatue.block.WhiteStatueBlock;
import net.mcreator.whitestatue.WhitestatueMod;

public class WhitestatueModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, WhitestatueMod.MODID);
	public static final RegistryObject<Block> WHITE_STATUE = REGISTRY.register("white_statue", () -> new WhiteStatueBlock());
	// Start of user code block custom blocks
	// End of user code block custom blocks
}
