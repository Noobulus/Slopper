package mod.noobulus.item;

import mod.noobulus.entity.CopperBoatEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.List;
import java.util.function.Predicate;

public class CopperBoatItem extends Item {
    private static final Predicate<Entity> RIDERS;
    private final CopperBoatEntity.Type type;
    private final boolean chest;

    public CopperBoatItem(boolean chest, CopperBoatEntity.Type type, Item.Settings settings) {
        super(settings);
        this.chest = chest;
        this.type = type;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        HitResult hitResult = raycast(world, user, RaycastContext.FluidHandling.ANY);
        if (hitResult.getType() == HitResult.Type.MISS) {
            return TypedActionResult.pass(itemStack);
        } else {
            Vec3d vec3d = user.getRotationVec(1.0F);
            double d = (double)5.0F;
            List<Entity> list = world.getOtherEntities(user, user.getBoundingBox().stretch(vec3d.multiply((double)5.0F)).expand((double)1.0F), RIDERS);
            if (!list.isEmpty()) {
                Vec3d vec3d2 = user.getEyePos();

                for(Entity entity : list) {
                    Box box = entity.getBoundingBox().expand((double)entity.getTargetingMargin());
                    if (box.contains(vec3d2)) {
                        return TypedActionResult.pass(itemStack);
                    }
                }
            }

            if (hitResult.getType() == HitResult.Type.BLOCK) {
                CopperBoatEntity copperBoatEntity = this.createEntity(world, hitResult, itemStack, user);
                copperBoatEntity.setCopperVariant(this.type);
                copperBoatEntity.setYaw(user.getYaw());
                if (!world.isSpaceEmpty(copperBoatEntity, copperBoatEntity.getBoundingBox())) {
                    return TypedActionResult.fail(itemStack);
                } else {
                    if (!world.isClient) {
                        world.spawnEntity(copperBoatEntity);
                        world.emitGameEvent(user, GameEvent.ENTITY_PLACE, hitResult.getPos());
                        itemStack.decrementUnlessCreative(1, user);
                    }

                    user.incrementStat(Stats.USED.getOrCreateStat(this));
                    return TypedActionResult.success(itemStack, world.isClient());
                }
            } else {
                return TypedActionResult.pass(itemStack);
            }
        }
    }

    private CopperBoatEntity createEntity(World world, HitResult hitResult, ItemStack stack, PlayerEntity player) {
        Vec3d vec3d = hitResult.getPos();
        CopperBoatEntity copperBoatEntity = (CopperBoatEntity)(new CopperBoatEntity(world, vec3d.x, vec3d.y, vec3d.z)); // (this.chest ? new ChestBoatEntity(world, vec3d.x, vec3d.y, vec3d.z) : new BoatEntity(world, vec3d.x, vec3d.y, vec3d.z));
        if (world instanceof ServerWorld serverWorld) {
            EntityType.copier(serverWorld, stack, player).accept(copperBoatEntity);
        }

        return copperBoatEntity;
    }

    static {
        RIDERS = EntityPredicates.EXCEPT_SPECTATOR.and(Entity::canHit);
    }
}
