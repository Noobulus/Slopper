package mod.noobulus.sound;

import mod.noobulus.Slopper;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class SlopperSounds {
    public static final SoundEvent ITEM_ARMOR_EQUIP_COPPER = registerSoundEvent("item.armor.equip_copper");

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = Identifier.of(Slopper.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void init() {}
}
