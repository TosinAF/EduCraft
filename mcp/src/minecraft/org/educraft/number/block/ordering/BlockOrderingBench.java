package org.educraft.number.block.ordering;

import org.educraft.EduCraft;
import org.educraft.number.block.BlockTileEntity;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockWorkbench;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockOrderingBench extends BlockContainer {

	public static final int GUI_ID = 2;
	
	public BlockOrderingBench(int id) {
		super(id, Material.ground);
		setUnlocalizedName("Ordering Bench");
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
	
	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		world.setBlockTileEntity(x, y, z, createNewTileEntity(world));
		super.onBlockAdded(world, x, y, z);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world) {
		return new BlockTileEntity();
	}
}
