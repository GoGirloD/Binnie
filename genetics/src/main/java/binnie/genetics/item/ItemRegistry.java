package binnie.genetics.item;

import binnie.core.api.gui.IGuiItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import binnie.core.item.ItemCore;
import binnie.genetics.CreativeTabGenetics;
import binnie.genetics.Genetics;
import binnie.genetics.core.GeneticsGUI;

public class ItemRegistry extends ItemCore implements IGuiItem {
	public ItemRegistry() {
		super("registry");
		this.setCreativeTab(CreativeTabGenetics.INSTANCE);
		this.setUnlocalizedName("registry");
		this.setMaxStackSize(1);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		Genetics.proxy.openGui(GeneticsGUI.REGISTRY, playerIn, playerIn.getPosition());
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}

	@Override
	public void openGuiOnRightClick(ItemStack itemStack, World world, EntityPlayer player) {
		Genetics.proxy.openGui(GeneticsGUI.REGISTRY, player, player.getPosition());
	}

	@Override
	public String getItemStackDisplayName(final ItemStack i) {
		return "Registry";
	}
}
