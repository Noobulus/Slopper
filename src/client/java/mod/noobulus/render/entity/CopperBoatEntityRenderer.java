package mod.noobulus.render.entity;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.util.Pair;
import mod.noobulus.Slopper;
import mod.noobulus.entity.CopperBoatEntity;
import mod.noobulus.render.model.CopperBoatEntityModel;
import mod.noobulus.render.model.CopperChestBoatEntityModel;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import org.joml.Quaternionf;

import java.util.Map;
import java.util.stream.Stream;

// something tells me you aren't doing OOP right if i have to re-impl the entire renderer
public class CopperBoatEntityRenderer extends EntityRenderer<CopperBoatEntity> {
    private final Map<CopperBoatEntity.Type, Pair<Identifier, CompositeEntityModel<CopperBoatEntity>>> texturesAndModels;

    public CopperBoatEntityRenderer(EntityRendererFactory.Context ctx, boolean chest) {
        super(ctx);
        this.shadowRadius = 0.8F;
        this.texturesAndModels = Stream.of(CopperBoatEntity.Type.values()).collect(ImmutableMap.toImmutableMap((type) -> type, (type) -> Pair.of(getTexture(type, chest), this.createModel(ctx, type, chest))));
    }

    private CompositeEntityModel<CopperBoatEntity> createModel(EntityRendererFactory.Context ctx, CopperBoatEntity.Type type, boolean chest) {
        EntityModelLayer entityModelLayer = chest ? SlopperEntityModelLayers.createCopperChestBoat(type) : SlopperEntityModelLayers.createCopperBoat(type);
        ModelPart modelPart = ctx.getPart(entityModelLayer);
        return (chest ? new CopperChestBoatEntityModel(modelPart) : new CopperBoatEntityModel(modelPart));
    }

    private static Identifier getTexture(CopperBoatEntity.Type type, boolean chest) {
        return chest ? Identifier.of(Slopper.MOD_ID,"textures/entity/copper_chest_boat/" + type.getName() + ".png") : Identifier.of(Slopper.MOD_ID,"textures/entity/copper_boat/" + type.getName() + ".png");
    }

    @Override
    public void render(CopperBoatEntity copperBoatEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.push();
        matrixStack.translate(0.0F, 0.375F, 0.0F);
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180.0F - f));
        float h = (float) copperBoatEntity.getDamageWobbleTicks() - g;
        float j = copperBoatEntity.getDamageWobbleStrength() - g;
        if (j < 0.0F) {
            j = 0.0F;
        }

        if (h > 0.0F) {
            matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(MathHelper.sin(h) * h * j / 10.0F * (float) copperBoatEntity.getDamageWobbleSide()));
        }

        float k = copperBoatEntity.interpolateBubbleWobble(g);
        if (!MathHelper.approximatelyEquals(k, 0.0F)) {
            matrixStack.multiply((new Quaternionf()).setAngleAxis(copperBoatEntity.interpolateBubbleWobble(g) * ((float)Math.PI / 180F), 1.0F, 0.0F, 1.0F));
        }

        Pair<Identifier, CompositeEntityModel<BoatEntity>> pair = (Pair)this.texturesAndModels.get(copperBoatEntity.getCopperVariant());
        Identifier identifier = (Identifier)pair.getFirst();
        CompositeEntityModel<BoatEntity> compositeEntityModel = (CompositeEntityModel)pair.getSecond();
        matrixStack.scale(-1.0F, -1.0F, 1.0F);
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(90.0F));
        compositeEntityModel.setAngles(copperBoatEntity, g, 0.0F, -0.1F, 0.0F, 0.0F);
        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(compositeEntityModel.getLayer(identifier));
        compositeEntityModel.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV);
        if (!copperBoatEntity.isSubmergedInWater()) {
            VertexConsumer vertexConsumer2 = vertexConsumerProvider.getBuffer(RenderLayer.getWaterMask());
            if (compositeEntityModel instanceof ModelWithWaterPatch) {
                ModelWithWaterPatch modelWithWaterPatch = (ModelWithWaterPatch)compositeEntityModel;
                modelWithWaterPatch.getWaterPatch().render(matrixStack, vertexConsumer2, i, OverlayTexture.DEFAULT_UV);
            }
        }

        matrixStack.pop();
        super.render(copperBoatEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    public Identifier getTexture(CopperBoatEntity boatEntity) {
        return (Identifier)((Pair)this.texturesAndModels.get(boatEntity.getCopperVariant())).getFirst();
    }
}
