package mod.noobulus.render.entity;

import mod.noobulus.Slopper;
import mod.noobulus.entity.CopperBoatEntity;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class SlopperEntityModelLayers {
    private static EntityModelLayer create(String id, String layer) {
        return new EntityModelLayer(Identifier.of(Slopper.MOD_ID, id), layer);
    }

    public static EntityModelLayer createCopperBoat(CopperBoatEntity.Type type) {
        return create("copper_boat/" + type.getName(), "main");
    }

    public static EntityModelLayer createCopperChestBoat(CopperBoatEntity.Type type) {
        return create("copper_chest_boat/" + type.getName(), "main");
    }
}
