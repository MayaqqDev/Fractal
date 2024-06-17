package de.dafuqs.fractal.forge;

import de.dafuqs.fractal.Fractal;
import net.minecraftforge.fml.common.Mod;

@Mod(Fractal.MOD_ID)
public class FractalForge {
    public FractalForge() {
        Fractal.init();
    }
}