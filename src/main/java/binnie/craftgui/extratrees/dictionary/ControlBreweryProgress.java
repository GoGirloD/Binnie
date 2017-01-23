package binnie.craftgui.extratrees.dictionary;

import binnie.core.machines.Machine;
import binnie.core.util.ItemStackSet;
import binnie.craftgui.core.Attribute;
import binnie.craftgui.core.CraftGUI;
import binnie.craftgui.core.IWidget;
import binnie.craftgui.core.geometry.IArea;
import binnie.craftgui.core.geometry.IPoint;
import binnie.craftgui.minecraft.Window;
import binnie.craftgui.minecraft.control.ControlProgressBase;
import binnie.craftgui.resource.Texture;
import binnie.craftgui.resource.minecraft.StandardTexture;
import binnie.extratrees.core.ExtraTreeTexture;
import binnie.extratrees.machines.brewery.BreweryLogic;
import binnie.extratrees.machines.brewery.BreweryRecipes;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import org.lwjgl.opengl.GL11;

import java.util.Collections;

public class ControlBreweryProgress extends ControlProgressBase {
	static Texture Brewery = new StandardTexture(0, 69, 34, 39, ExtraTreeTexture.Gui);
	static Texture BreweryOverlay = new StandardTexture(34, 69, 34, 39, ExtraTreeTexture.Gui);

	@Override
	public void onRenderBackground() {
		CraftGUI.Render.texture(ControlBreweryProgress.Brewery, new IPoint(0.0f, 0.0f));
		final BreweryLogic logic = Machine.getInterface(BreweryLogic.class, Window.get(this).getInventory());
		if (logic == null || logic.currentCrafting == null || logic.currentCrafting.inputFluid == null) {
			return;
		}
		final int fermentedHeight = (int) (32.0f * logic.getProgress() / 100.0f);
		CraftGUI.Render.limitArea(new IArea(new IPoint(1.0f, 6.0f).add(this.getAbsolutePosition()), new IPoint(32.0f, 32 - fermentedHeight)));
		GL11.glEnable(3089);
		this.renderFluid(logic.currentCrafting.inputFluid, new IPoint(1.0f, 6.0f));
		this.renderFluid(logic.currentCrafting.inputFluid, new IPoint(17.0f, 6.0f));
		this.renderFluid(logic.currentCrafting.inputFluid, new IPoint(1.0f, 22.0f));
		this.renderFluid(logic.currentCrafting.inputFluid, new IPoint(17.0f, 22.0f));
		GL11.glDisable(3089);
		CraftGUI.Render.limitArea(new IArea(new IPoint(1.0f, 38 - fermentedHeight).add(this.getAbsolutePosition()), new IPoint(32.0f, fermentedHeight)));
		GL11.glEnable(3089);
		this.renderFluid(BreweryRecipes.getOutput(logic.currentCrafting), new IPoint(1.0f, 6.0f));
		this.renderFluid(BreweryRecipes.getOutput(logic.currentCrafting), new IPoint(17.0f, 6.0f));
		this.renderFluid(BreweryRecipes.getOutput(logic.currentCrafting), new IPoint(1.0f, 22.0f));
		this.renderFluid(BreweryRecipes.getOutput(logic.currentCrafting), new IPoint(17.0f, 22.0f));
		GL11.glDisable(3089);
		final ItemStackSet stacks = new ItemStackSet();
		Collections.addAll(stacks, logic.currentCrafting.inputGrains);
		stacks.add(logic.currentCrafting.ingredient);
		int x = 1;
		int y = 6;
		for (final ItemStack stack : stacks) {
			CraftGUI.Render.item(new IPoint(x, y), stack);
			x += 16;
			if (x > 18) {
				x = 1;
				y += 16;
			}
		}
	}

	@Override
	public void onRenderForeground() {
	}

	protected ControlBreweryProgress(final IWidget parent, final float x, final float y) {
		super(parent, x, y, 34.0f, 39.0f);
		this.addAttribute(Attribute.MouseOver);
	}

	public void renderFluid(final FluidStack fluid, final IPoint pos) {
//		final int hex = fluid.getFluid().getColor(fluid);
//		final int r = (hex & 0xFF0000) >> 16;
//		final int g = (hex & 0xFF00) >> 8;
//		final int b = hex & 0xFF;
//		final IIcon icon = fluid.getFluid().getIcon();
//		GL11.glColor4f(r / 255.0f, g / 255.0f, b / 255.0f, 1.0f);
//		GL11.glEnable(3042);
//		GL11.glBlendFunc(770, 771);
//		CraftGUI.Render.iconBlock(pos, fluid.getFluid().getIcon());
//		GL11.glDisable(3042);
	}

}
