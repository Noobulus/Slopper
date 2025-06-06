package mod.noobulus.item;

import mod.noobulus.Slopper;
import mod.noobulus.entity.CopperBoatEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class SlopperEntityTypes {
    private static <T extends Entity> EntityType register(EntityType.Builder<T> type, String id) {
        Identifier entityID = Identifier.of(Slopper.MOD_ID, id);
        return (EntityType) Registry.register(Registries.ENTITY_TYPE, entityID, type.build(String.valueOf(entityID)));
    }

    // copper boat
    public static final EntityType<CopperBoatEntity> COPPER_BOAT;

    // idk if this is needed, the mojang stuff does it for w/e reason
    static {
        COPPER_BOAT = register(EntityType.Builder.create(CopperBoatEntity::new, SpawnGroup.MISC).dimensions(1.375F, 0.5625F).eyeHeight(0.5625F).maxTrackingRange(10), "copper_boat");
    }

    public static void init() {
    }
}
