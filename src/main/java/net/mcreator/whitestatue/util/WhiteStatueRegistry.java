package net.mcreator.whitestatue.util;

import net.mcreator.whitestatue.data.WhiteStatueSavedData;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;

import java.util.Set;

public class WhiteStatueRegistry {

    private WhiteStatueRegistry() {
    }

    private static WhiteStatueSavedData getData(ServerLevel level) {
        MinecraftServer server = level.getServer();
        ServerLevel overworld = server.getLevel(Level.OVERWORLD);
        if (overworld == null) {
            overworld = level;
        }
        return overworld.getDataStorage().computeIfAbsent(WhiteStatueSavedData::load, WhiteStatueSavedData::new, WhiteStatueSavedData.DATA_NAME);
    }

    public static int getCount(ServerLevel level) {
        return getData(level).getLocations().size();
    }

    public static boolean isRegistered(ServerLevel level, BlockPos pos) {
        WhiteStatueSavedData data = getData(level);
        String key = WhiteStatueSavedData.encodeKey(level.dimension().location(), pos.getX(), pos.getY(), pos.getZ());
        return data.getLocations().contains(key);
    }

    public static boolean tryRegister(ServerLevel level, BlockPos pos, int limit) {
        WhiteStatueSavedData data = getData(level);
        String key = WhiteStatueSavedData.encodeKey(level.dimension().location(), pos.getX(), pos.getY(), pos.getZ());
        Set<String> locations = data.getLocations();
        if (locations.contains(key)) {
            return true;
        }
        if (locations.size() >= limit) {
            return false;
        }
        locations.add(key);
        data.setDirty();
        return true;
    }

    public static void unregister(ServerLevel level, BlockPos pos) {
        WhiteStatueSavedData data = getData(level);
        String key = WhiteStatueSavedData.encodeKey(level.dimension().location(), pos.getX(), pos.getY(), pos.getZ());
        if (data.getLocations().remove(key)) {
            data.setDirty();
        }
    }

    public static boolean isNear(ServerLevel level, BlockPos pos, int rangeHorizontal, int maxAltitudeDifference) {
        WhiteStatueSavedData data = getData(level);
        ResourceLocation dimension = level.dimension().location();
        int rangeSq = rangeHorizontal * rangeHorizontal;

        for (String entry : data.getLocations()) {
            String[] parts = entry.split("\\|");
            if (parts.length != 4) {
                continue;
            }
            if (!dimension.toString().equals(parts[0])) {
                continue;
            }
            int x;
            int y;
            int z;
            try {
                x = Integer.parseInt(parts[1]);
                y = Integer.parseInt(parts[2]);
                z = Integer.parseInt(parts[3]);
            } catch (NumberFormatException ex) {
                continue;
            }
            int dx = x - pos.getX();
            int dz = z - pos.getZ();
            int dy = Math.abs(y - pos.getY());
            if (dy > maxAltitudeDifference) {
                continue;
            }
            if (dx * dx + dz * dz <= rangeSq) {
                return true;
            }
        }
        return false;
    }
}
