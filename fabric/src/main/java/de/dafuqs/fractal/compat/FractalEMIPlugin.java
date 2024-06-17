package de.dafuqs.fractal.compat;

import de.dafuqs.fractal.interfaces.*;
import dev.emi.emi.api.*;
import dev.emi.emi.api.widget.*;
import de.dafuqs.fractal.mixin.client.CreativeInventoryScreenAccessor;
import net.minecraft.client.gui.screen.ingame.*;
import net.minecraft.item.*;

public class FractalEMIPlugin implements EmiPlugin {
	
	@Override
	public void register(EmiRegistry registry) {
		registry.addExclusionArea(CreativeInventoryScreen.class, (screen, out) -> {
			if (screen != null) {
				ItemGroup selected = CreativeInventoryScreenAccessor.fractal$getSelectedGroup();
				if (selected instanceof ItemGroupParent parent && screen instanceof SubTabLocation stl && parent.fractal$getChildren() != null && !parent.fractal$getChildren().isEmpty()) {
					out.accept(new Bounds(stl.fractal$getX(), stl.fractal$getY(), 72, stl.fractal$getH()));
					out.accept(new Bounds(stl.fractal$getX2(), stl.fractal$getY(), 72, stl.fractal$getH2()));
				}
			}
		});
	}
	
}
