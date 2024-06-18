package de.dafuqs.fractal.forge;

import de.dafuqs.fractal.Fractal;
import de.dafuqs.fractal.api.ItemSubGroup;
import de.dafuqs.fractal.interfaces.ItemGroupParent;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.fml.common.Mod;

@Mod("fractal")
public class FractalForge {

    public static final ResourceLocation GROUP_ID = ResourceLocation.fromNamespaceAndPath("mymod", "main");

    public static final CreativeModeTab MAIN = CreativeModeTab.builder()
            .icon(() -> new ItemStack(Blocks.REDSTONE_BLOCK))
            .displayItems((displayContext, entries) -> {
                entries.accept(Items.APPLE, CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
                ItemGroupParent parent = FractalForge.MAIN;
                for (ItemSubGroup subGroup : parent.fractal$getChildren()) {
                    entries.acceptAll(subGroup.getDisplayItems(), CreativeModeTab.TabVisibility.SEARCH_TAB_ONLY);
                }
            })
            .hideTitle()
            .title(Component.translatable("itemGroup.mymod.main"))
            .build();

    public static final ResourceLocation ITEM_GROUP_BACKGROUND_TEXTURE_IDENTIFIER = ResourceLocation.fromNamespaceAndPath("mymod", "textures/custom_item_group.png");

    public static final CreativeModeTab EQUIPMENT = new ItemSubGroup.Builder(MAIN, ResourceLocation.fromNamespaceAndPath("mymod", "equipment"), Component.translatable("itemGroup.mymod.equipment")).entries((displayContext, entries) -> entries.accept(Items.APPLE)).build();
    public static final CreativeModeTab FUNCTIONAL = new ItemSubGroup.Builder(MAIN, ResourceLocation.fromNamespaceAndPath("mymod", "functional"), Component.translatable("itemGroup.mymod.functional")).entries((displayContext, entries) -> entries.accept(Items.BAKED_POTATO)).build();
    public static final CreativeModeTab CUISINE = new ItemSubGroup.Builder(MAIN, ResourceLocation.fromNamespaceAndPath("mymod", "cuisine"), Component.translatable("itemGroup.mymod.cuisine")).entries((displayContext, entries) -> entries.accept(Items.CACTUS)).build();
    public static final CreativeModeTab RESOURCES = new ItemSubGroup.Builder(MAIN, ResourceLocation.fromNamespaceAndPath("mymod", "resources"), Component.translatable("itemGroup.mymod.resources")).entries((displayContext, entries) -> entries.accept(Items.DANDELION)).build();

    public FractalForge() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, GROUP_ID, MAIN);
        Fractal.init();
    }
}