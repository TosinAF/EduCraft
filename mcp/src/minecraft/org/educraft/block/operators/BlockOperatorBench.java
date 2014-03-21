package org.educraft.block.operators;

import org.educraft.EduCraft;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockWorkbench;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockOperatorBench extends BlockWorkbench {
	/**
	 * The id used to identify this block in the GuiHandler.
	 */
	public static final int GUI_ID = 1;
	
	@SideOnly(Side.CLIENT)
	private Icon operatorIconTop;
	@SideOnly(Side.CLIENT)
	private Icon operatorIconFront;

	public BlockOperatorBench(int id) {
		super(id);
		setUnlocalizedName("operatorBench");
		setCreativeTab(EduCraft.tabEduCraft);
	}

	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2) {
		return par1 == 1 ? this.operatorIconTop : (par1 == 0 ? Block.planks
				.getBlockTextureFromSide(par1)
				: (par1 != 2 && par1 != 4 ? this.blockIcon
						: this.operatorIconFront));
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister register) {
		this.blockIcon = register.registerIcon("EduCraft:operator_bench_side");
		this.operatorIconTop = register
				.registerIcon("EduCraft:operator_bench_top");
		this.operatorIconFront = register
				.registerIcon("EduCraft:operator_bench_front");
	}
	
	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3,
			int par4, EntityPlayer par5EntityPlayer, int par6, float par7,
			float par8, float par9) {
		if (!par5EntityPlayer.isSneaking()) {
			par5EntityPlayer.openGui(EduCraft.instance, GUI_ID, par1World, par2,
					par3, par4);
			return true;
		} else {
			return false;
		}
	}
	
}
