package de.dafuqs.fractal.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import de.dafuqs.fractal.interfaces.*;
import de.dafuqs.fractal.api.*;
import net.fabricmc.api.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.gui.screen.ingame.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import org.jetbrains.annotations.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Environment(EnvType.CLIENT)
@Mixin(CreativeInventoryScreen.class)
public abstract class CreativeInventoryScreenCustomTextureMixin {
	
	@Shadow
	private static ItemGroup selectedTab;
	
	@Shadow protected abstract boolean hasScrollbar();
	
	@Unique
	private ItemSubGroup fractal$renderedItemSubGroup;
	
	@Unique
	private ItemGroup fractal$renderedItemGroup;
	
	@ModifyArg(method = "drawBackground", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawTexture(Lnet/minecraft/util/Identifier;IIIIII)V"))
	private Identifier injectCustomGroupTexture(Identifier original) {
		ItemSubGroup subGroup = getSelectedSubGroup();
		if (subGroup == null || subGroup.getBackgroundTexture() == null) return original;
		return subGroup.getBackgroundTexture();
	}

	// Use @WrapOperation as a "safe" @Redirect
	// to emulate 1.20.1 functionality and preserve API compatability
	@WrapOperation(method = "drawBackground", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Lnet/minecraft/util/Identifier;IIII)V"))
	private void drawCustomScrollbarTexture(DrawContext instance, Identifier texture, int x, int y, int width, int height, Operation<Void> original) {
		ItemSubGroup subGroup = getSelectedSubGroup();
		if (subGroup == null || subGroup.getBackgroundTexture() == null) {
			original.call(instance, texture, x, y, width, height);
			return;
		}
		instance.drawTexture(subGroup.getBackgroundTexture(), x, y, this.hasScrollbar() ? 0 : 12, 136, width, height);
	}
	@Inject(method = "drawBackground", at = @At("RETURN"))
	private void releaseGroupInstance(DrawContext context, float delta, int mouseX, int mouseY, CallbackInfo ci) {
		fractal$renderedItemSubGroup = null;
	}
	
	@Inject(method = "renderTabIcon", at = @At(value = "HEAD"))
	private void injectCustomTabTexture(DrawContext context, ItemGroup group, CallbackInfo ci) {
		fractal$renderedItemGroup = group;
		fractal$renderedItemSubGroup = getRenderedSubGroup();
	}

	@WrapOperation(method = "renderTabIcon", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Lnet/minecraft/util/Identifier;IIII)V"))
	private void drawCustomTabTexture(DrawContext instance, Identifier texture, int x, int y, int width, int height, Operation<Void> original) {
		// (semi-)pseudocode for V, adapted from 1.20.1 code
		/*
			int v = 0;
			if (group == selectedTab) v += 32;
			if (group.getRow() != ItemGroup.Row.TOP) v += 64;
		 */
		ItemSubGroup subGroup = getSelectedSubGroup();
		if (subGroup == null || subGroup.getBackgroundTexture() == null) {
			original.call(instance, texture, x, y, width, height);
			return;
		}
		int v = 0;
		if (fractal$renderedItemGroup == selectedTab) v += 32;
		if (fractal$renderedItemGroup.getRow() != ItemGroup.Row.TOP) v += 64;
		instance.drawTexture(subGroup.getBackgroundTexture(), x, y, selectedTab.getColumn() == 0 ? 195 : 223, v, width, height);
	}

	@Inject(method = "renderTabIcon", at = @At("RETURN"))
	private void restoreTabTexture(DrawContext context, ItemGroup group, CallbackInfo ci) {
		fractal$renderedItemGroup = null;
		fractal$renderedItemSubGroup = null;
	}
	
	@Unique
	private @Nullable ItemSubGroup getRenderedSubGroup() {
		return fractal$renderedItemGroup instanceof ItemGroupParent itemGroupParent ? itemGroupParent.fractal$getSelectedChild() : null;
	}
	
	@Unique
	private @Nullable ItemSubGroup getSelectedSubGroup() {
		return selectedTab instanceof ItemGroupParent itemGroupParent ? itemGroupParent.fractal$getSelectedChild() : null;
	}
	
}