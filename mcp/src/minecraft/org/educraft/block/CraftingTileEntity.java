package org.educraft.block;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.tileentity.TileEntity;

import org.educraft.block.calculator.BlockCalculator;
import org.educraft.block.ordering.BenchType;
import org.educraft.block.ordering.BlockOrderingBench;

/**
 * This tile entity contains the inventories required to maintain a block
 * responsible for crafting. By storing the inventories in a tile entity, we
 * allow multiple players to interact with the same inventory.
 * 
 * @see BlockCalculator
 * @see BlockOrderingBench
 */
public class CraftingTileEntity extends TileEntity {
	// data members representing the inventory
	private Container container;
	private InventoryCrafting craftMatrix;
	private IInventory craftResult;

	// the number of players using the tile entity
	private int playersUsing;

	/**
	 * Default constructor.
	 */
	public CraftingTileEntity() {
		this.container = null;
		this.craftMatrix = null;
		this.craftResult = null;
		this.playersUsing = 0;
	}

	/**
	 * Set up the crafting matrix and the crafting result.
	 * <p/>
	 * We can't set them up in the constructor, because we don't have a
	 * reference to the container on creation. Should always initialise the tile
	 * entity as soon as we create the relevant container.
	 * 
	 * @param container
	 *            the container to link the crafting inventory to
	 * @param row
	 * 			the number of rows in the crafting matrix
	 * @param column
	 * 			the number of columns in the crafting matrix
	 * @return the initialised tile entity
	 */
	public synchronized CraftingTileEntity initialise(Container container, int row, int column) {
		if (this.container == null) {
			this.container = container;
			this.craftMatrix = new InventoryCrafting(this.container, row, column);
			this.craftResult = new InventoryCraftResult();
		}
		incrUsers();
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
	 * Returns the number of players currently interacting with the tile entity.
	 * 
	 * @return the number of users
	 */
	public synchronized int getNumberOfUsers() {
		return playersUsing;
	}

	/**
	 * Determines whether or not this tile entity is currently being used by
	 * anyone.
	 * 
	 * @return true if the count of players is greater than 0
	 */
	public synchronized boolean isBeingUsed() {
		return playersUsing > 0;
	}

	/**
	 * Increases the count of players using this tile entity.
	 */
	public synchronized void incrUsers() {
		playersUsing++;
	}

	/**
	 * Decreases the count of players using this tile entity.
	 */
	public synchronized void decrUsers() {
		if (playersUsing > 0) {
			playersUsing--;
		}
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
