package prophetsama.testing.mixin.gamerules;

import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.EntityLightningBolt;
import net.minecraft.core.util.helper.MathHelper;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import prophetsama.testing.MelonBTACommands;

@Mixin(value = EntityLightningBolt.class, remap = false)
public abstract class EntityLightningBoltMixin extends Entity{
	public EntityLightningBoltMixin(World world) {
		super(world);
	}

	@Redirect(method = "spawnInit()V",at = @At(value = "INVOKE", target = "Lnet/minecraft/core/world/World;areBlocksLoaded(IIII)Z"))
	private boolean removeFire(World instance, int x, int y, int z, int range) {
		return world.getGameRule(MelonBTACommands.FIRE_TICKS) && world.areBlocksLoaded(x, y, z, range);
	}

	@Redirect(method = "tick",at = @At(value = "INVOKE", target = "Lnet/minecraft/core/world/World;areBlocksLoaded(IIII)Z"))
	private boolean fixTick(World instance, int x, int y, int z, int range) {
		return world.getGameRule(MelonBTACommands.FIRE_TICKS) && world.areBlocksLoaded(x, y, z, range);
	}
}
