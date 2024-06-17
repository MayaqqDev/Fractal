package de.dafuqs.fractal;

import net.fabricmc.api.*;

public class FractalFabric implements ModInitializer {
	@Override
	public void onInitialize() {
		Fractal.init();
	}
}