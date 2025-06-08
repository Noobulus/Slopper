package mod.noobulus.mixin;

import mod.noobulus.item.SlopperTags;
import net.minecraft.entity.decoration.ArmorStandEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Predicate;

@Mixin(ArmorStandEntity.class)
public class ArmorStandEntityMixin {
    @Inject(at = @At("HEAD"), method = "onStruckByLightning(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/LightningEntity;)V")
    private void injectCopperLightningFeatures(CallbackInfo ci) {
        repairTaggedItems();
    }

    // armor stands are sneaky and have a blank override method of onStruckByLightning
    @Unique
    private void repairTaggedItems() {
        ((ArmorStandEntity) (Object) this).getEquippedItems().forEach(itemStack -> {
            if (itemStack.streamTags().anyMatch(Predicate.isEqual(SlopperTags.EQUIPMENT_REPAIRED_BY_LIGHTNING))) {
                itemStack.setDamage(0);
            }
        });
    }
}
