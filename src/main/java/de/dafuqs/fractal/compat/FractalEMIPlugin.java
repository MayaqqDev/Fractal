package de.dafuqs.fractal.compat;

import de.dafuqs.fractal.quack.*;
import dev.emi.emi.api.*;
import dev.emi.emi.api.widget.*;
import net.minecraft.client.gui.screen.ingame.*;
import net.minecraft.item.*;

public class FractalEMIPlugin implements EmiPlugin {
	
	@Override
	public void register(EmiRegistry registry) {
		registry.addExclusionArea(CreativeInventoryScreen.class, (screen, out) -> {
			if (screen != null) {
				ItemGroup selected = ItemGroup.GROUPS[screen.getSelectedTab()];
				if (selected instanceof ItemGroupParent parent && screen instanceof SubTabLocation stl && parent.fractal$getChildren() != null && !parent.fractal$getChildren().isEmpty()) {
					out.accept(new Bounds(stl.fractal$getX(), stl.fractal$getY(), stl.fractal$getW(), stl.fractal$getH()));
				}
			}
		});
	}
	
}
