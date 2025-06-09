package mod.noobulus.mixin;

import mod.noobulus.item.SlopperItems;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.DrownedEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Difficulty;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DrownedEntity.class)
public class DrownedEntityMixin extends ZombieEntity {
    @Unique
    private static final java.util.Random slopperRandom = new java.util.Random();

    public DrownedEntityMixin(World world) {
        super(world);
    }

    // since this injects at the tail, it won't bother the default logic and should work just fine
    // this checks for a slightly different threshold than zombies do to compensate for the existing 10% chance for geared spawns
    @Inject(method= "initEquipment(Lnet/minecraft/util/math/random/Random;Lnet/minecraft/world/LocalDifficulty;)V", at = @At("TAIL"))
    private void injectCopperToolSpawns(Random random, LocalDifficulty localDifficulty, CallbackInfo ci) {
        if (slopperRandom.nextFloat() < (((DrownedEntity)(Object) this).getWorld().getDifficulty() == Difficulty.HARD ? 0.055F : 0.011F)) {
            int i = slopperRandom.nextInt(3);
            if (i == 0) {
                ((DrownedEntity)(Object) this).equipStack(EquipmentSlot.MAINHAND, new ItemStack(SlopperItems.COPPER_SWORD));
            } else {
                ((DrownedEntity)(Object) this).equipStack(EquipmentSlot.MAINHAND, new ItemStack(SlopperItems.COPPER_SHOVEL));
            }
        }
    }
}
