package org.educraft.number.calculator;

import org.educraft.EduCraft;

import net.minecraft.block.BlockWorkbench;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class Calculator extends BlockWorkbench {
	
	public Calculator(int id) {
		super(id);
		setCreativeTab(EduCraft.tabEduCraft);
	}
	
	@Override
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
		if (!par5EntityPlayer.isSneaking()) {
			// only open the GUI if the player isn't sneaking
			// this allows the player to place buttons, etc. on the surface
			par5EntityPlayer.openGui(EduCraft.instance, 0, par1World, par2, par3, par4);
			return true;
		} else {
			return false;
		}
	}

}
