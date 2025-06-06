package mod.noobulus;

import mod.noobulus.item.SlopperEntityTypes;
import mod.noobulus.render.entity.CopperBoatEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class SlopperClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.

		EntityRendererRegistry.register(SlopperEntityTypes.COPPER_BOAT, (context) -> new CopperBoatEntityRenderer(context, false));
	}
}