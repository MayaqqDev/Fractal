package de.dafuqs.fractal.mixin.client;

import net.fabricmc.api.*;
import net.minecraft.client.gui.screen.ingame.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.*;

@Environment(EnvType.CLIENT)
@Mixin(CreativeInventoryScreen.class)
public interface CreativeInventoryScreenAccessor {
	
	@Accessor(value = "selectedTab")
	int getSelectedTab();
	
}