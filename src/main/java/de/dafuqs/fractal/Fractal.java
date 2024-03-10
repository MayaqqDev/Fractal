package de.dafuqs.fractal;

import de.dafuqs.fractal.api.*;
import de.dafuqs.fractal.interfaces.*;
import net.fabricmc.api.*;
import net.fabricmc.fabric.api.itemgroup.v1.*;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.registry.*;
import net.minecraft.text.*;
import net.minecraft.util.*;

public class Fractal implements ModInitializer {
	public static final Identifier GROUP_ID = new Identifier("mymod", "main");
	
	public static final ItemGroup MAIN = FabricItemGroup.builder()
			.icon(() -> new ItemStack(Blocks.REDSTONE_BLOCK))
			.entries((displayContext, entries) -> {
				entries.add(Items.APPLE, ItemGroup.StackVisibility.PARENT_TAB_ONLY);
				ItemGroupParent parent = Fractal.MAIN;
				for (ItemSubGroup subGroup : parent.fractal$getChildren()) {
					entries.addAll(subGroup.getSearchTabStacks(), ItemGroup.StackVisibility.SEARCH_TAB_ONLY);
				}
			})
			.displayName(Text.translatable("itemGroup.mymod.main"))
			.noRenderedName()
			.build();
	
	public static final ItemGroup EQUIPMENT = new ItemSubGroup.Builder(MAIN, new Identifier("fractal", "equipment"), Text.translatable("itemGroup.mymod.equipment")).entries((displayContext, entries) -> entries.add(Items.APPLE)).build();
	public static final ItemGroup FUNCTIONAL = new ItemSubGroup.Builder(MAIN, new Identifier("fractal", "functional"), Text.translatable("itemGroup.mymod.functional")).entries((displayContext, entries) -> entries.add(Items.BAKED_POTATO)).build();
	public static final ItemGroup CUISINE = new ItemSubGroup.Builder(MAIN, new Identifier("fractal", "cuisine"), Text.translatable("itemGroup.mymod.cuisine")).entries((displayContext, entries) -> entries.add(Items.CACTUS)).build();
	public static final ItemGroup RESOURCES = new ItemSubGroup.Builder(MAIN, new Identifier("fractal", "resources"), Text.translatable("itemGroup.mymod.resources")).entries((displayContext, entries) -> entries.add(Items.DANDELION)).build();
	public static final ItemGroup RESOURCES2 = new ItemSubGroup.Builder(MAIN, new Identifier("fractal", "resources2"), Text.translatable("itemGroup.mymod.resources2")).entries((displayContext, entries) -> entries.add(Items.DANDELION)).build();
	public static final ItemGroup RESOURCES3 = new ItemSubGroup.Builder(MAIN, new Identifier("fractal", "resources3"), Text.translatable("itemGroup.mymod.resources3")).entries((displayContext, entries) -> entries.add(Items.DANDELION)).build();
	public static final ItemGroup RESOURCES4 = new ItemSubGroup.Builder(MAIN, new Identifier("fractal", "resources4"), Text.translatable("itemGroup.mymod.resources4")).entries((displayContext, entries) -> entries.add(Items.DANDELION)).build();
	public static final ItemGroup RESOURCES5 = new ItemSubGroup.Builder(MAIN, new Identifier("fractal", "resources5"), Text.translatable("itemGroup.mymod.resources5")).entries((displayContext, entries) -> entries.add(Items.DANDELION)).build();
	public static final ItemGroup RESOURCES6 = new ItemSubGroup.Builder(MAIN, new Identifier("fractal", "resources6"), Text.translatable("itemGroup.mymod.resources6")).entries((displayContext, entries) -> entries.add(Items.DANDELION)).build();
	public static final ItemGroup RESOURCES7 = new ItemSubGroup.Builder(MAIN, new Identifier("fractal", "resources7"), Text.translatable("itemGroup.mymod.resources7")).entries((displayContext, entries) -> entries.add(Items.DANDELION)).build();
	public static final ItemGroup RESOURCES8 = new ItemSubGroup.Builder(MAIN, new Identifier("fractal", "resources8"), Text.translatable("itemGroup.mymod.resources8")).entries((displayContext, entries) -> entries.add(Items.DANDELION)).build();
	public static final ItemGroup RESOURCES9 = new ItemSubGroup.Builder(MAIN, new Identifier("fractal", "resources9"), Text.translatable("itemGroup.mymod.resources9")).entries((displayContext, entries) -> entries.add(Items.DANDELION)).build();
	public static final ItemGroup RESOURCES10 = new ItemSubGroup.Builder(MAIN, new Identifier("fractal", "resources10"), Text.translatable("itemGroup.mymod.resources10")).entries((displayContext, entries) -> entries.add(Items.DANDELION)).build();
	public static final ItemGroup RESOURCES11 = new ItemSubGroup.Builder(MAIN, new Identifier("fractal", "resources11"), Text.translatable("itemGroup.mymod.resources11")).entries((displayContext, entries) -> entries.add(Items.DANDELION)).build();
	public static final ItemGroup RESOURCES12 = new ItemSubGroup.Builder(MAIN, new Identifier("fractal", "resources12"), Text.translatable("itemGroup.mymod.resources12")).entries((displayContext, entries) -> entries.add(Items.DANDELION)).build();
	public static final ItemGroup RESOURCES13 = new ItemSubGroup.Builder(MAIN, new Identifier("fractal", "resources13"), Text.translatable("itemGroup.mymod.resources13")).entries((displayContext, entries) -> entries.add(Items.DANDELION)).build();
	public static final ItemGroup RESOURCES14 = new ItemSubGroup.Builder(MAIN, new Identifier("fractal", "resources14"), Text.translatable("itemGroup.mymod.resources14")).entries((displayContext, entries) -> entries.add(Items.DANDELION)).build();
	public static final ItemGroup RESOURCES15 = new ItemSubGroup.Builder(MAIN, new Identifier("fractal", "resources15"), Text.translatable("itemGroup.mymod.resources15")).entries((displayContext, entries) -> entries.add(Items.DANDELION)).build();
	public static final ItemGroup RESOURCES16 = new ItemSubGroup.Builder(MAIN, new Identifier("fractal", "resources16"), Text.translatable("itemGroup.mymod.resources16")).entries((displayContext, entries) -> entries.add(Items.DANDELION)).build();
	
	@Override
	public void onInitialize() {
		Registry.register(Registries.ITEM_GROUP, GROUP_ID, MAIN);
	}
	
}