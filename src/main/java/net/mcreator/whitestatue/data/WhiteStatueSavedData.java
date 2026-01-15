package net.mcreator.whitestatue.data;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.saveddata.SavedData;

import java.util.LinkedHashSet;
import java.util.Set;

public class WhiteStatueSavedData extends SavedData {

    public static final String DATA_NAME = "whitestatue_locations";

    private final Set<String> locations = new LinkedHashSet<>();

    public Set<String> getLocations() {
        return locations;
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        ListTag list = new ListTag();
        for (String entry : locations) {
            CompoundTag item = new CompoundTag();
            item.putString("Key", entry);
            list.add(item);
        }
        tag.put("Locations", list);
        return tag;
    }

    public static WhiteStatueSavedData load(CompoundTag tag) {
        WhiteStatueSavedData data = new WhiteStatueSavedData();
        ListTag list = tag.getList("Locations", Tag.TAG_COMPOUND);
        for (int i = 0; i < list.size(); i++) {
            CompoundTag item = list.getCompound(i);
            if (item.contains("Key", Tag.TAG_STRING)) {
                data.locations.add(item.getString("Key"));
            }
        }
        return data;
    }

    public static String encodeKey(ResourceLocation dimension, int x, int y, int z) {
        return dimension + "|" + x + "|" + y + "|" + z;
    }
}
