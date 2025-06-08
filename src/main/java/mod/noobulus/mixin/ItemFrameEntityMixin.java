package mod.noobulus.mixin;

import mod.noobulus.item.SlopperTags;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.AbstractDecorationEntity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Intrinsic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Predicate;

@Mixin(ItemFrameEntity.class)
public abstract class ItemFrameEntityMixin extends AbstractDecorationEntity {
    // another mixin, another useless constructor
    protected ItemFrameEntityMixin(EntityType<? extends AbstractDecorationEntity> entityType, World world) {
        super(entityType, world);
    }

    // same shenanigans as LivingEntityMixin but item frames aren't a LivingEntity so we have to do it again
    @Intrinsic @Override
    public void onStruckByLightning(ServerWorld world, LightningEntity lightning) {
        super.onStruckByLightning(world, lightning);
    }

    @Inject(at = @At("HEAD"), method = "onStruckByLightning(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/LightningEntity;)V", cancellable = true)
    private void injectCopperLightningFeatures(CallbackInfo ci) {
        ItemStack stack = ((ItemFrameEntity) (Object) this).getHeldItemStack();
        if (stack.streamTags().anyMatch(Predicate.isEqual(SlopperTags.EQUIPMENT_REPAIRED_BY_LIGHTNING))) {
            stack.setDamage(0);
            ci.cancel(); // be nice and prevent the item frame from dying if it has a repairable item in it
        }
    }
}
