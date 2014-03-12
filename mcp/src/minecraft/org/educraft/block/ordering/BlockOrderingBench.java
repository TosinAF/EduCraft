package org.educraft.block.ordering;

import org.educraft.EduCraft;
import org.educraft.block.CraftingTileEntity;
import org.educraft.item.DoorKey;

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

/**
 * An ordering bench is a type of crafting bench that takes three numbers as
 * input, and will return a {@link org.educraft.item.DoorKey} if and only if the
 * numbers are arranged in order from smallest to largest
 */
public class BlockOrderingBench extends BlockContainer {
	/**
	 * The id used to identify this block in the gui handler.
	 */
	public static final int GUI_ID = 2;

	@SideOnly(Side.CLIENT)
	private Icon orderingIconTop;
	@SideOnly(Side.CLIENT)
	private Icon orderingIconFront;

	/**
	 * Class constructor.
	 * 
	 * @param id
	 *            the block id to register with the game
	 */
	public BlockOrderingBench(int id) {
		super(id, Material.ground);
		setUnlocalizedName("Ordering Bench");
		setCreativeTab(EduCraft.tabEduCraft);
	}

	/**
	 * Returns the icon for display on the given face of the block.
	 * 
	 * @param par1
	 *            unknown
	 * @param par2
	 *            unknown
	 */
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2) {
		return par1 == 1 ? this.orderingIconTop : (par1 == 0 ? Block.planks
				.getBlockTextureFromSide(par1)
				: (par1 != 2 && par1 != 4 ? this.blockIcon
						: this.orderingIconFront));
	}

	/**
	 * Registers all of this block's textures, so that we can view them in-game.
	 * 
	 * @param register
	 *            the store for all of our icons
	 */
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister register) {
		this.blockIcon = register.registerIcon("EduCraft:ordering_bench_side");
		this.orderingIconTop = register
				.registerIcon("EduCraft:ordering_bench_top");
		this.orderingIconFront = register
				.registerIcon("EduCraft:ordering_bench_front");
	}

	/**
	 * Called whenever a new instance of this block is placed. It creates a new
	 * tile entity and associates it with this block.
	 * 
	 * @param world
	 *            the world the block was placed in
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
	 * Called whenever a player right-clicks on this block. Used to display the
	 * GUI when appropriate.
	 * 
	 * @param world
	 *            the world this block is in
	 * @param x
	 *            the x-position of the block
	 * @param y
	 *            the y-position of the block
	 * @param z
	 *            the z-position of the block
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
		if (!player.isSneaking()) {
			player.openGui(EduCraft.instance, GUI_ID, world, x, y, z);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Obtains a new instance of the appropriate tile entity for this block.
	 * 
	 * @param world
	 *            the world this block was placed in
	 * @return the new tile entity
	 */
	@Override
	public TileEntity createNewTileEntity(World world) {
		return new CraftingTileEntity();
	}
}
