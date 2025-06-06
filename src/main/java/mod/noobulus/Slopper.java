package mod.noobulus;

import mod.noobulus.item.SlopperArmorMaterials;
import mod.noobulus.item.SlopperEntityTypes;
import mod.noobulus.item.SlopperItemGroups;
import mod.noobulus.item.SlopperItems;
import mod.noobulus.sound.SlopperSounds;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Slopper implements ModInitializer {
	public static final String MOD_ID = "slopper";

	// logger to yap with
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// runs when minecraft is ready to load mod content
		SlopperSounds.init();
		SlopperEntityTypes.init();
		SlopperArmorMaterials.init();
		SlopperItems.init();
		SlopperItemGroups.init();
		LOGGER.info("Copper \"Improved!\"");
	}
}