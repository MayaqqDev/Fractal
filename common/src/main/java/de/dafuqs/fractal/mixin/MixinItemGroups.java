package de.dafuqs.fractal.mixin;

import de.dafuqs.fractal.api.*;
import net.minecraft.world.item.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(CreativeModeTabs.class)
public class MixinItemGroups {
	
	@Inject(at = @At("HEAD"), method = "buildAllTabContents")
	private static void updateEntries(CreativeModeTab.ItemDisplayParameters displayContext, CallbackInfo ci) {
		ItemSubGroup.SUB_GROUPS.forEach((group) -> {
			group.updateEntries(displayContext);
		});
	}
}
