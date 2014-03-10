package org.educraft.block.ordering;

import org.educraft.EduCraft;

import net.minecraft.block.BlockWorkbench;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class BlockOrderingBench extends BlockWorkbench {

	public static final int GUI_ID = 2;
	
	public BlockOrderingBench(int id) {
		super(id);
		setUnlocalizedName("orderingBench");
		setCreativeTab(EduCraft.tabEduCraft);
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
