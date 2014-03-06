package org.educraft.number.block.calculator;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

import org.educraft.EduCraft;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Calculator extends BlockContainer {

	@SideOnly(Side.CLIENT)
	private Icon calculatorIconTop;
	@SideOnly(Side.CLIENT)
	private Icon calculatorIconFront;

	public Calculator(int id) {
		super(id, Material.ground);
		setUnlocalizedName("Calculator Table");
		setCreativeTab(EduCraft.tabEduCraft);
	}

	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2) {

		return par1 == 1 ? this.calculatorIconTop : (par1 == 0 ? Block.planks
				.getBlockTextureFromSide(par1)
				: (par1 != 2 && par1 != 4 ? this.blockIcon
						: this.calculatorIconFront));
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister
				.registerIcon("EduCraft:calculator_side");
		this.calculatorIconTop = par1IconRegister
				.registerIcon("EduCraft:calculator_top");
		this.calculatorIconFront = par1IconRegister
				.registerIcon("EduCraft:calculator_front");
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3,
			int par4, EntityPlayer par5EntityPlayer, int par6, float par7,
			float par8, float par9) {
		if (!par5EntityPlayer.isSneaking()) {
			// only open the GUI if the player isn't sneaking
			// this allows the player to place buttons, etc. on the surface
			par5EntityPlayer.openGui(EduCraft.instance, 0, par1World, par2,
					par3, par4);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new CalculatorTileEntity();
	}
}
