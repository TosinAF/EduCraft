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

/**
 * This class provides a tile entity for storing a Calculator's inventory in a
 * place where every player in the world can access it, rather than being
 * confined to an individual player's view.
 */
public class CalculatorTileEntity extends TileEntity {
	// data members representing the inventory
	private Container container;
	private InventoryCrafting craftMatrix;
	private IInventory craftResult;

	// the number of players using the tile entity
	private int playersUsing;

	/**
	 * Default constructor.
	 */
	public CalculatorTileEntity() {
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
