package net.mcreator.whitestatue.events;

import net.mcreator.whitestatue.WhitestatueMod;
import net.mcreator.whitestatue.configuration.WhiteStatueMainConfigConfiguration;
import net.mcreator.whitestatue.init.WhitestatueModBlocks;
import net.mcreator.whitestatue.util.WhiteStatueRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = WhitestatueMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class WhiteStatuePlacementHandler {

    @SubscribeEvent
    public static void onPlace(BlockEvent.EntityPlaceEvent event) {
        if (!(event.getLevel() instanceof ServerLevel level)) {
            return;
        }
        Block block = event.getPlacedBlock().getBlock();
        if (block != WhitestatueModBlocks.WHITE_STATUE.get()) {
            return;
        }

        BlockPos pos = event.getPos();
        int limit = WhiteStatueMainConfigConfiguration.STATUE_LIMIT.get();
        boolean allowed = WhiteStatueRegistry.tryRegister(level, pos, limit);
        if (!allowed) {
            level.removeBlock(pos, false);
            Entity placer = event.getEntity();
            if (placer instanceof Player player) {
                player.displayClientMessage(Component.literal("White Statue limit reached (" + limit + ")."), false);
            } else if (level.getServer() != null) {
                level.getServer().getPlayerList().broadcastSystemMessage(Component.literal("White Statue limit reached (" + limit + ")."), false);
            }
        }
    }

    @SubscribeEvent
    public static void onBreak(BlockEvent.BreakEvent event) {
        if (!(event.getLevel() instanceof ServerLevel level)) {
            return;
        }
        Block block = event.getState().getBlock();
        if (block != WhitestatueModBlocks.WHITE_STATUE.get()) {
            return;
        }
        WhiteStatueRegistry.unregister(level, event.getPos());
    }
}
