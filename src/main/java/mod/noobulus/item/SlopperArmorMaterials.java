package mod.noobulus.item;

import mod.noobulus.Slopper;
import mod.noobulus.sound.SlopperSounds;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class SlopperArmorMaterials {
    public static final RegistryEntry<ArmorMaterial> COPPER = registerMaterial(
            "copper", // id
            Map.of(
                    ArmorItem.Type.HELMET, 1,
                    ArmorItem.Type.CHESTPLATE, 5,
                    ArmorItem.Type.LEGGINGS, 3,
                    ArmorItem.Type.BOOTS, 1
            ), // prot values per piece
            15, // enchantability
            RegistryEntry.of(SlopperSounds.ITEM_ARMOR_EQUIP_COPPER), // equip sound
            () -> Ingredient.ofItems(Items.COPPER_INGOT), // repair ingredient
            0.0F, // toughness
            0.0F, // kb resist
            false // dyeable bool (always false)
    );

    public static final int COPPER_DURABILITY_MULTIPLIER = 21; // durability multiplier - iron is 15

    public static RegistryEntry<ArmorMaterial> registerMaterial(String id, Map<ArmorItem.Type, Integer> defensePoints, int enchantability, RegistryEntry<SoundEvent> equipSound, Supplier<Ingredient> repairIngredientSupplier, float toughness, float knockbackResistance, boolean dyeable) {
        List<ArmorMaterial.Layer> layers = List.of(
                // one layer, no suffix needed
                new ArmorMaterial.Layer(Identifier.of(Slopper.MOD_ID, id), "", dyeable)
        );

        ArmorMaterial material = new ArmorMaterial(defensePoints, enchantability, equipSound, repairIngredientSupplier, layers, toughness, knockbackResistance);
        material = Registry.register(Registries.ARMOR_MATERIAL, Identifier.of(Slopper.MOD_ID, id), material); // register the material

        return RegistryEntry.of(material); // might want the registry entry
    }

    public static void init() {}
}