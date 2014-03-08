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

/**
 * This class implements a block that can be used to perform calculations. It is
 * based on {@link net.minecraft.block.BlockWorkbench}, but with different
 * mechanics.
 */
public class BlockCalculator extends BlockContainer {
	/**
	 * The id used to identify this block in the GuiHandler.
	 */
	public static final int GUI_ID = 0;

	@SideOnly(Side.CLIENT)
	private Icon calculatorIconTop;
	@SideOnly(Side.CLIENT)
	private Icon calculatorIconFront;

	/**
	 * Class constructor.
	 * 
	 * @param id
	 *            the block id to register with the game
	 */
	public BlockCalculator(int id) {
		super(id, Material.ground);
		setUnlocalizedName("Calculator Table");
		setCreativeTab(EduCraft.tabEduCraft);
	}

	/**
	 * Determines what icon to display on which face of the calculator.
	 * 
	 * @param par1
	 *            unknown
	 * @param par2
	 *            unknown
	 */
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2) {
		return par1 == 1 ? this.calculatorIconTop : (par1 == 0 ? Block.planks
				.getBlockTextureFromSide(par1)
				: (par1 != 2 && par1 != 4 ? this.blockIcon
						: this.calculatorIconFront));
	}

	/**
	 * Adds all the relevant icons into the game, so that we can use them later.
	 * 
	 * @param register
	 *            where we will register all the icons
	 */
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister register) {
		this.blockIcon = register.registerIcon("EduCraft:calculator_side");
		this.calculatorIconTop = register
				.registerIcon("EduCraft:calculator_top");
		this.calculatorIconFront = register
				.registerIcon("EduCraft:calculator_front");
	}

	/**
	 * Called whenever a block of this type is placed in the world. This method
	 * creates a new CalculatorTileEntity and associates it with this block.
	 * 
	 * @param world
	 *            the world the block was added to
	 * @param x
	 *            x-position of the block
	 * @param y
	 *            y-position of the block
	 * @param z
	 *            z-position of the block
	 */
	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		world.setBlockTileEntity(x, y, z, createNewTileEntity(world));
		super.onBlockAdded(world, x, y, z);
	}

	/**
	 * Called whenever a player right-clicks on the block. Used to display the
	 * GUI of this block to the user.
	 * 
	 * @param world
	 *            the world this block is in
	 * @param x
	 *            x-position of the block
	 * @param y
	 *            y-position of the block
	 * @param z
	 *            z-position of the block
	 * @param player
	 *            the player activating the block
	 * @param par6
	 *            unknown
	 * @param par7
	 *            unknown
	 * @param par8
	 *            unknown
	 * @param par9
	 *            unknown
	 */
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z,
			EntityPlayer player, int par6, float par7, float par8, float par9) {
		if (player.isSneaking() || world.getBlockTileEntity(x, y, z) == null) {
			return false;
		} else {
			player.openGui(EduCraft.instance, 0, world, x, y, z);
			return true;
		}
	}

	/**
	 * Creates a new tile entity of type appropriate for this block.
	 * 
	 * @param world
	 *            the world this block is in
	 * @return the created tile entity
	 */
	@Override
	public TileEntity createNewTileEntity(World world) {
		return new CalculatorTileEntity();
	}
}
