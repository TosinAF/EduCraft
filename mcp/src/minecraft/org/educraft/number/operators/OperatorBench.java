package org.educraft.number.operators;

import org.educraft.EduCraft;

import net.minecraft.block.BlockWorkbench;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class OperatorBench extends BlockWorkbench {

	public OperatorBench(int id) {
		super(id);
		setUnlocalizedName("operatorBench");
		setCreativeTab(EduCraft.tabEduCraft);
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3,
			int par4, EntityPlayer par5EntityPlayer, int par6, float par7,
			float par8, float par9) {
		if (!par5EntityPlayer.isSneaking()) {
			par5EntityPlayer.displayGUIWorkbench(par2, par3, par4);
			return true;
		} else {
			return false;
		}
	}

}
