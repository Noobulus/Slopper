package mod.noobulus.item;

import mod.noobulus.Slopper;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class SlopperTags {
    public static final TagKey<Item> EQUIPMENT_REPAIRED_BY_LIGHTNING = TagKey.of(RegistryKeys.ITEM, Identifier.of(Slopper.MOD_ID, "equipment_repaired_by_lightning"));
    public static final TagKey<Item> LIGHTNING_RESISTANT_ARMOR = TagKey.of(RegistryKeys.ITEM, Identifier.of(Slopper.MOD_ID, "lightning_resistant_armor"));

    public static void init() {
    }
}
