package binnie.extratrees.gui.database;

import java.util.Collection;

import binnie.genetics.api.ITreeBreedingSystem;
import net.minecraft.item.ItemStack;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import forestry.api.genetics.IAlleleSpecies;

import binnie.core.api.gui.IWidget;
import binnie.core.gui.controls.ControlText;
import binnie.core.gui.database.ControlSpeciesBox;
import binnie.core.gui.database.DatabaseTab;
import binnie.core.gui.database.PageAbstract;
import binnie.core.gui.database.WindowAbstractDatabase;
import binnie.core.gui.geometry.Area;
import binnie.core.gui.geometry.TextJustification;
import binnie.core.gui.minecraft.Window;

@SideOnly(Side.CLIENT)
public class PageWood extends PageAbstract<ItemStack> {
	public PageWood(final IWidget parent, final DatabaseTab tab) {
		super(parent, tab);
	}

	@Override
	public void onValueChanged(final ItemStack species) {
		this.deleteAllChildren();
		final WindowAbstractDatabase database = Window.get(this);
		new ControlText(this, new Area(0, 0, this.getSize().xPos(), 24), this.getValue().toString(), TextJustification.MIDDLE_CENTER);
		ITreeBreedingSystem breedingSystem = (ITreeBreedingSystem) database.getBreedingSystem();
		final Collection<IAlleleSpecies> trees = breedingSystem.getTreesThatHaveWood(species, database.isMaster(), database.getWorld(), database.getUsername());
		new ControlSpeciesBox(this, 4, 24, this.getSize().xPos() - 8, this.getSize().yPos() - 4 - 24).setOptions(trees);
	}
}
