package mod.noobulus.mixin;

import mod.noobulus.item.SlopperTags;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LightningEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Intrinsic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Predicate;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends Entity {
    public ItemEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Intrinsic
    @Override
    public void onStruckByLightning(ServerWorld world, LightningEntity lightning) {
        super.onStruckByLightning(world, lightning);
    }

    @Inject(at = @At("HEAD"), method = "onStruckByLightning(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/LightningEntity;)V", cancellable = true)
    private void injectCopperLightningFeatures(CallbackInfo ci) {
        ItemStack stack = ((ItemEntity) (Object) this).getStack();
        if (stack.streamTags().anyMatch(Predicate.isEqual(SlopperTags.EQUIPMENT_REPAIRED_BY_LIGHTNING))) {
            stack.setDamage(0);
            ci.cancel();
        }
    }
}
