package org.educraft.number.block.calculator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class CalculatorTileEntity extends TileEntity {
	private Container container;
	private InventoryCrafting craftMatrix;
	private IInventory craftResult;

	/**
	 * Default constructor.
	 */
	public CalculatorTileEntity() {
		this.container = null;
		this.craftMatrix = null;
		this.craftResult = null;
	}

	/**
	 * Set up the crafting matrix and the crafting result.
	 * <p/>
	 * We can't set them up in the constructor, because we don't have a
	 * reference to the container on creation. Should always initialise the tile
	 * entity as soon as we create the CalculatorContainer.
	 * <p/>
	 * The method is synchronized to prevent multiple players from activating
	 * the tile entity and overwriting each other's references.
	 * 
	 * @param container
	 *            the container to link the crafting inventory to
	 * @return the initialised tile entity
	 */
	public synchronized CalculatorTileEntity initialise(Container container) {
		if (this.container == null) {
			this.container = container;
			this.craftMatrix = new InventoryCrafting(this.container, 1, 3);
			this.craftResult = new InventoryCraftResult();
		}
		return this;
	}

	/**
	 * Returns the crafting matrix associated with this tile entity.
	 * 
	 * @return the crafting matrix
	 */
	public InventoryCrafting getCraftMatrix() {
		return craftMatrix;
	}

	/**
	 * Returns the inventory used to hold the crafting result.
	 * 
	 * @return the crafting result
	 */
	public IInventory getCraftResult() {
		return craftResult;
	}

	/**
	 * Determines whether or not the given player is able to access this tile
	 * entity or not.
	 * 
	 * @param player
	 *            the player attempting to use the tile entity
	 * @return true of the player can use it, false if not
	 */
	public boolean isUseableByPlayer(EntityPlayer player) {
		return worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) == this
				&& player.getDistanceSq(xCoord + 0.5, yCoord + 0.5,
						zCoord + 0.5) < 64;
	}

}
