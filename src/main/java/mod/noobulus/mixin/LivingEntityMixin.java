package mod.noobulus.mixin;

import mod.noobulus.item.SlopperTags;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Intrinsic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

    @Shadow public abstract boolean damage(DamageSource source, float amount);

    // never called but it makes the linter shut up so hey
    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    // this method exists in Entity, but not LivingEntity. so we make it exist in LivingEntity...
    @Intrinsic @Override
    public void onStruckByLightning(ServerWorld world, LightningEntity lightning) {
        super.onStruckByLightning(world, lightning);
    }

    // ... so that we can inject into it and add custom behavior!
    @Inject(at = @At("HEAD"), method = "onStruckByLightning(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/LightningEntity;)V", cancellable = true)
    private void injectCopperLightningFeatures(CallbackInfo ci) {
        repairTaggedItems();

        int lightningEffectScale = getLightningEffectScale(); // abstract checking the armor into another method
        if (lightningEffectScale != 4) { // if scale is 4 then there's no copper armor and it isn't our problem
            ci.cancel(); // if it is our problem, skip applying vanilla lightning damage
            applyDamageAndFire(lightningEffectScale);
        }
    }

    @Unique
    private void repairTaggedItems() {
        ((LivingEntity) (Object) this).getEquippedItems().forEach(itemStack -> { // get all equipped items and iterate through them
            if (itemStack.streamTags().anyMatch(Predicate.isEqual(SlopperTags.EQUIPMENT_REPAIRED_BY_LIGHTNING))) { // if any of them have the right tag...
                itemStack.setDamage(0); // ...fully repair them!
            }
        });
    }

    @Unique
    private int getLightningEffectScale() {
        AtomicInteger lightningEffectScale = new AtomicInteger(4); // no idea what an atomic integer is but the linter yelled at me to use it
        ((LivingEntity) (Object) this).getArmorItems().forEach(itemStack -> {
            if (itemStack.streamTags().anyMatch(Predicate.isEqual(SlopperTags.LIGHTNING_RESISTANT_ARMOR))) {
                lightningEffectScale.set(lightningEffectScale.get() - 1); // scale lightning damage and fire time based on lightning resistant armor slots
            }
        });
        return lightningEffectScale.get(); // spit this back
    }

    @Unique
    private void applyDamageAndFire(int lightningEffectScale) {
        if (lightningEffectScale == 0) return; // do nothing if the effects scale is zero

        // vanilla lightning behavior, but scalable as needed
        ((LivingEntity) (Object) this).setFireTicks(((LivingEntity) (Object) this).getFireTicks() + 1);
        if (((LivingEntity) (Object) this).getFireTicks() == 0) {
            ((LivingEntity) (Object) this).setOnFireFor(2.0F * lightningEffectScale);
        }

        ((LivingEntity) (Object) this).damage(((LivingEntity) (Object) this).getDamageSources().lightningBolt(), 2.0F * lightningEffectScale);
    }
}
