package mod.noobulus.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;

public class SlopperItemGroups {
    public static void init() {
        // armor
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register((itemGroup) -> itemGroup.addAfter(
                Items.LEATHER_BOOTS.getDefaultStack(), SlopperItems.COPPER_HELMET.getDefaultStack()));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register((itemGroup) -> itemGroup.addAfter(
                SlopperItems.COPPER_HELMET.getDefaultStack(), SlopperItems.COPPER_CHESTPLATE.getDefaultStack()));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register((itemGroup) -> itemGroup.addAfter(
                SlopperItems.COPPER_CHESTPLATE.getDefaultStack(), SlopperItems.COPPER_LEGGINGS.getDefaultStack()));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register((itemGroup) -> itemGroup.addAfter(
                SlopperItems.COPPER_LEGGINGS.getDefaultStack(), SlopperItems.COPPER_BOOTS.getDefaultStack()));

        // harvest tools
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register((itemGroup) -> itemGroup.addAfter(
                Items.STONE_HOE.getDefaultStack(), SlopperItems.COPPER_SHOVEL.getDefaultStack()));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register((itemGroup) -> itemGroup.addAfter(
                SlopperItems.COPPER_SHOVEL.getDefaultStack(), SlopperItems.COPPER_PICKAXE.getDefaultStack()));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register((itemGroup) -> itemGroup.addAfter(
                SlopperItems.COPPER_PICKAXE.getDefaultStack(), SlopperItems.COPPER_AXE.getDefaultStack()));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register((itemGroup) -> itemGroup.addAfter(
                SlopperItems.COPPER_AXE.getDefaultStack(), SlopperItems.COPPER_HOE.getDefaultStack()));

        // weapons
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register((itemGroup) -> itemGroup.addAfter(
                Items.STONE_SWORD.getDefaultStack(), SlopperItems.COPPER_SWORD.getDefaultStack()));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register((itemGroup) -> itemGroup.addAfter(
                Items.STONE_AXE.getDefaultStack(), SlopperItems.COPPER_AXE.getDefaultStack()));
    }
}
