package org.educraft.block.ordering;

import org.educraft.EduCraft;
import org.educraft.number.block.BlockTileEntity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockWorkbench;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockOrderingBench extends BlockContainer {

	public static final int GUI_ID = 2;
	
	@SideOnly(Side.CLIENT)
	private Icon orderingIconTop;
	@SideOnly(Side.CLIENT)
	private Icon orderingIconFront;
	
	public BlockOrderingBench(int id) {
		super(id, Material.ground);
		setUnlocalizedName("Ordering Bench");
		setCreativeTab(EduCraft.tabEduCraft);
	}
	
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2) {
		return par1 == 1 ? this.orderingIconTop : (par1 == 0 ? Block.planks
				.getBlockTextureFromSide(par1)
				: (par1 != 2 && par1 != 4 ? this.blockIcon
						: this.orderingIconFront));
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister register) {
		this.blockIcon = register.registerIcon("EduCraft:ordering_bench_side");
		this.orderingIconTop = register
				.registerIcon("EduCraft:ordering_bench_top");
		this.orderingIconFront = register
				.registerIcon("EduCraft:ordering_bench_front");
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
