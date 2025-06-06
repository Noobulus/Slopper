package mod.noobulus.entity;

import mod.noobulus.item.SlopperEntityTypes;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.function.ValueLists;
import net.minecraft.world.World;

import java.util.function.IntFunction;
import java.util.function.ToIntFunction;

public class CopperBoatEntity extends BoatEntity {
    private static final TrackedData<Integer> COPPER_BOAT_TYPE;

    public CopperBoatEntity(EntityType entityType, World world) {
        super(entityType, world);
    }

    public CopperBoatEntity(World world, double x, double y, double z) {
        this(SlopperEntityTypes.COPPER_BOAT, world);
        this.setPosition(x, y, z);
        this.prevX = x;
        this.prevY = y;
        this.prevZ = z;
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(COPPER_BOAT_TYPE, Type.COPPER.ordinal());
    }

    public void setCopperVariant(CopperBoatEntity.Type type) {
        this.dataTracker.set(COPPER_BOAT_TYPE, type.ordinal());
    }

    public CopperBoatEntity.Type getCopperVariant() {
        return CopperBoatEntity.Type.getType((Integer)this.dataTracker.get(COPPER_BOAT_TYPE));
    }

    static {
        COPPER_BOAT_TYPE = DataTracker.registerData(CopperBoatEntity.class, TrackedDataHandlerRegistry.INTEGER);
    }

    public enum Type implements StringIdentifiable {
        COPPER(Blocks.COPPER_BLOCK, "copper");

        private final String name;
        private final Block baseBlock;
        public static final EnumCodec<Type> CODEC = StringIdentifiable.createCodec(Type::values);
        private static final IntFunction<Type> BY_ID = ValueLists.createIdToValueFunction((ToIntFunction<Type>) Enum::ordinal, values(), ValueLists.OutOfBoundsHandling.ZERO);

        private Type(final Block baseBlock, final String name) {
            this.name = name;
            this.baseBlock = baseBlock;
        }

        public String asString() {
            return this.name;
        }

        public String getName() {
            return this.name;
        }

        public Block getBaseBlock() {
            return this.baseBlock;
        }

        @Override
        public String toString() {
            return this.name;
        }

        public static Type getType(int type) {
            return (Type)BY_ID.apply(type);
        }

        public static Type getType(String name) {
            return (Type)CODEC.byId(name, COPPER);
        }
    }
}
