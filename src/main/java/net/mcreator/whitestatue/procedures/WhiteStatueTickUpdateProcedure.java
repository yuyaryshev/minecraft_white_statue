package net.mcreator.whitestatue.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.GameType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.TagKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.client.Minecraft;

import java.util.List;
import java.util.Comparator;

public class WhiteStatueTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double d = 0;
		double r = 0;
		String entityRegId = "";
		r = 12;
		{
			final Vec3 _center = new Vec3(x, y, z);
			List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(r / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
			for (Entity entityiterator : _entfound) {
				entityRegId = ForgeRegistries.ENTITY_TYPES.getKey(entityiterator.getType()).toString();
				if (!(entityiterator instanceof TamableAnimal _tamEnt ? _tamEnt.isTame() : false) && (entityiterator instanceof Mob _mobEnt2 && _mobEnt2.isAggressive()
						|| entityiterator instanceof LivingEntity _livEnt3 && _livEnt3.getMobType() == MobType.UNDEAD || entityiterator.getType().is(TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation("minecraft:skeletons")))
						|| entityiterator.getType().is(TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation("minecraft:zombies"))) || false || false)) {
					if (world instanceof ServerLevel _level)
						_level.sendParticles(ParticleTypes.GLOW, (entityiterator.getX()), (entityiterator.getY()), (entityiterator.getZ()), 10, 1, 1, 1, 1);
					if (!entityiterator.level().isClientSide())
						entityiterator.discard();
				} else {
					if (!world.isClientSide() && world.getServer() != null)
						world.getServer().getPlayerList().broadcastSystemMessage(Component.literal(("Ignoreing entity near WhiteStatue, regId=" + entityRegId)), false);
				}
				if (new Object() {
					public boolean checkGamemode(Entity _ent) {
						if (_ent instanceof ServerPlayer _serverPlayer) {
							return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.SURVIVAL;
						} else if (_ent.level().isClientSide() && _ent instanceof Player _player) {
							return Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()) != null
									&& Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()).getGameMode() == GameType.SURVIVAL;
						}
						return false;
					}
				}.checkGamemode(entityiterator) && (entityiterator instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) < Math.min(entityiterator instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1, 10)) {
					if (entityiterator instanceof LivingEntity _entity)
						_entity.setHealth((float) Math.min(entityiterator instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1, 8));
					if (world instanceof ServerLevel _level)
						_level.sendParticles(ParticleTypes.GLOW, (entityiterator.getX()), (entityiterator.getY()), (entityiterator.getZ()), 10, 1, 1, 1, 1);
				}
			}
		}
		d = r * (-1);
		for (int index0 = 0; index0 < (int) (r * 2); index0++) {
			if (world instanceof ServerLevel _level)
				_level.sendParticles(ParticleTypes.GLOW, (x + d), y, (z - r), 1, 1, 1, 1, 1);
			if (world instanceof ServerLevel _level)
				_level.sendParticles(ParticleTypes.GLOW, (x + d), y, (z + r), 1, 1, 1, 1, 1);
			if (world instanceof ServerLevel _level)
				_level.sendParticles(ParticleTypes.GLOW, (x - r), y, (z + d), 1, 1, 1, 1, 1);
			if (world instanceof ServerLevel _level)
				_level.sendParticles(ParticleTypes.GLOW, (x + r), y, (z + d), 1, 1, 1, 1, 1);
			d = d + 1;
		}
	}
}
