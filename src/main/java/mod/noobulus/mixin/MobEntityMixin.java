package mod.noobulus.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import mod.noobulus.item.SlopperItems;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Random;

@Mixin(MobEntity.class)
public class MobEntityMixin {
    // generally speaking, it makes the most sense to replace armor with equal protection values, EXCEPT in the case of helmets where durability is usually more relevant
    // since helmet durability dictates how long an undead entity can stay in the sun. also i don't think anyone is gonna notice this in practice LOL

    @Unique
    private static final Random slopperRandom = new Random();

    @ModifyReturnValue(method = "getEquipmentForSlot(Lnet/minecraft/entity/EquipmentSlot;I)Lnet/minecraft/item/Item;", at = @At(value = "RETURN", ordinal = 3))
    private static Item injectCopperHelmetSpawns(Item original) {
        return slopperRandom.nextBoolean() ? original : SlopperItems.COPPER_HELMET;
    }

    @ModifyReturnValue(method = "getEquipmentForSlot(Lnet/minecraft/entity/EquipmentSlot;I)Lnet/minecraft/item/Item;", at = @At(value = "RETURN", ordinal = 6))
    private static Item injectCopperChestplateSpawns(Item original) {
        return slopperRandom.nextBoolean() ? original : SlopperItems.COPPER_CHESTPLATE;
    }

    @ModifyReturnValue(method = "getEquipmentForSlot(Lnet/minecraft/entity/EquipmentSlot;I)Lnet/minecraft/item/Item;", at = @At(value = "RETURN", ordinal = 11))
    private static Item injectCopperLeggingSpawns(Item original) {
        return slopperRandom.nextBoolean() ? original : SlopperItems.COPPER_LEGGINGS;
    }

    @ModifyReturnValue(method = "getEquipmentForSlot(Lnet/minecraft/entity/EquipmentSlot;I)Lnet/minecraft/item/Item;", at = @At(value = "RETURN", ordinal = 16))
    private static Item injectCopperBootSpawns(Item original) {
        return slopperRandom.nextBoolean() ? original : SlopperItems.COPPER_BOOTS;
    }
}
