package mod.noobulus.item;

import mod.noobulus.Slopper;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class SlopperItems {
    public static Item register(Item item, String id) {
        Identifier itemID = Identifier.of(Slopper.MOD_ID, id); // generate item id
        Item registeredItem = Registry.register(Registries.ITEM, itemID, item); // register item
        return registeredItem; // return item
    }

    public static final Identifier SLOPPER_INTERACTION_RANGE_MODIFIER_ID = Identifier.ofVanilla("slopper_interaction_range");

    // armor
    public static final Item COPPER_HELMET = register(new ArmorItem(SlopperArmorMaterials.COPPER, ArmorItem.Type.HELMET, new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(SlopperArmorMaterials.COPPER_DURABILITY_MULTIPLIER))), "copper_helmet");
    public static final Item COPPER_CHESTPLATE = register(new ArmorItem(SlopperArmorMaterials.COPPER, ArmorItem.Type.CHESTPLATE, new Item.Settings().maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(SlopperArmorMaterials.COPPER_DURABILITY_MULTIPLIER))), "copper_chestplate");
    public static final Item COPPER_LEGGINGS = register(new ArmorItem(SlopperArmorMaterials.COPPER, ArmorItem.Type.LEGGINGS, new Item.Settings().maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(SlopperArmorMaterials.COPPER_DURABILITY_MULTIPLIER))), "copper_leggings");
    public static final Item COPPER_BOOTS = register(new ArmorItem(SlopperArmorMaterials.COPPER, ArmorItem.Type.BOOTS, new Item.Settings().maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(SlopperArmorMaterials.COPPER_DURABILITY_MULTIPLIER))), "copper_boots");

    // tools
    public static final Item COPPER_SWORD = register(new SwordItem(SlopperToolMaterials.COPPER, (new Item.Settings()).attributeModifiers(SwordItem.createAttributeModifiers(SlopperToolMaterials.COPPER, 3, -2.4F).with(EntityAttributes.PLAYER_BLOCK_INTERACTION_RANGE, new EntityAttributeModifier(SLOPPER_INTERACTION_RANGE_MODIFIER_ID, 0.5, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND))), "copper_sword");
    public static final Item COPPER_SHOVEL = register(new ShovelItem(SlopperToolMaterials.COPPER, (new Item.Settings()).attributeModifiers(ShovelItem.createAttributeModifiers(SlopperToolMaterials.COPPER, 1.5F, -3.0F).with(EntityAttributes.PLAYER_BLOCK_INTERACTION_RANGE, new EntityAttributeModifier(SLOPPER_INTERACTION_RANGE_MODIFIER_ID, 0.5, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND))), "copper_shovel");
    public static final Item COPPER_PICKAXE = register(new PickaxeItem(SlopperToolMaterials.COPPER, (new Item.Settings()).attributeModifiers(PickaxeItem.createAttributeModifiers(SlopperToolMaterials.COPPER, 1.0F, -2.8F).with(EntityAttributes.PLAYER_BLOCK_INTERACTION_RANGE, new EntityAttributeModifier(SLOPPER_INTERACTION_RANGE_MODIFIER_ID, 0.5, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND))), "copper_pickaxe");
    public static final Item COPPER_AXE = register(new AxeItem(SlopperToolMaterials.COPPER, (new Item.Settings()).attributeModifiers(AxeItem.createAttributeModifiers(SlopperToolMaterials.COPPER, 6.5F, -3.2F).with(EntityAttributes.PLAYER_BLOCK_INTERACTION_RANGE, new EntityAttributeModifier(SLOPPER_INTERACTION_RANGE_MODIFIER_ID, 0.5, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND))), "copper_axe");
    public static final Item COPPER_HOE = register(new HoeItem(SlopperToolMaterials.COPPER, (new Item.Settings()).attributeModifiers(HoeItem.createAttributeModifiers(SlopperToolMaterials.COPPER, -1.0F, -1.5F).with(EntityAttributes.PLAYER_BLOCK_INTERACTION_RANGE, new EntityAttributeModifier(SLOPPER_INTERACTION_RANGE_MODIFIER_ID, 0.5, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND))), "copper_hoe");

    // copper nugget
    public static final Item COPPER_NUGGET = register(new Item(new Item.Settings()), "copper_nugget"); // totally not a dummy item i prommy :pray:

    public static void init() {
    }
}
