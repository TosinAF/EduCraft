package org.educraft.block.ordering;

import org.educraft.EduCraft;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.world.World;

import org.educraft.EduCraft;
import org.educraft.block.CraftingTileEntity;

/**
 * Container used to represent the inventory of an ordering bench. It uses a
 * tile entity to make the inventory available to multiple users.
 */
public class OrderingContainer extends Container {
	// data members to store the inventory
	private CraftingTileEntity tileEntity;
	private InventoryCrafting craftMatrix;
	private IInventory craftResult;
	// the world this container is in
	private World worldObj;
	
	// the type of bench this container is paired with
	private BenchType benchType;

	/**
	 * Class constructor.
	 * 
	 * @param inventory
	 *            the inventory of the player
	 * @param tileEntity
	 *            tile entity containing the crafting inventories
	 * @param world
	 *            the world this container is in
	 */
	public OrderingContainer(InventoryPlayer inventory,
			CraftingTileEntity tileEntity, World world, BenchType type) {
		// set up the crafting inventories
		this.tileEntity = tileEntity.initialise(this);
		this.craftMatrix = tileEntity.getCraftMatrix();
		this.craftResult = tileEntity.getCraftResult();
		this.worldObj = world;
		
		this.benchType = type;

		// add slots to the crafting inventories
		this.addSlotToContainer(new SlotCrafting(inventory.player,
				this.craftMatrix, this.craftResult, 0, 142, 35));
		
		int i1;
		for (i1 = 0; i1 < 3; ++i1) {
			this.addSlotToContainer(new Slot(this.craftMatrix, i1,
					10 + i1 * 36, 35));
		}

		for (int l = 0; l < 3; ++l) {
			for (i1 = 0; i1 < 9; ++i1) {
				this.addSlotToContainer(new Slot(inventory, i1 + l * 9 + 9,
						8 + i1 * 18, 84 + l * 18));
			}
		}

		for (int l = 0; l < 9; ++l) {
			this.addSlotToContainer(new Slot(inventory, l, 8 + l * 18, 142));
		}

		this.onCraftMatrixChanged(this.craftMatrix);

	}

	/**
	 * Called whenever a player adds or removes something from the crafting
	 * matrix. Used to update the crafting result.
	 * 
	 * @param inventory
	 *            the crafting matrix
	 */
	@Override
	public void onCraftMatrixChanged(IInventory inventory) {
		this.craftResult.setInventorySlotContents(0,
				OrderingCraftingManager.INSTANCE
						.findMatchingRecipe(this.craftMatrix, this.benchType));
	}

	/**
	 * Called whenever a player closes the GUI. So long as at least one player
	 * is using the GUI, we should keep all the items in the crafting matrix. If
	 * no one is using it, then we should drop everything, like in a regular
	 * crafting bench.
	 * 
	 * @param player
	 *            the player who just closed the GUI
	 */
	@Override
	public void onContainerClosed(EntityPlayer player) {
		super.onContainerClosed(player);

		if (!this.worldObj.isRemote && !this.tileEntity.isBeingUsed()) {
			for (int i = 0; i < 3; ++i) {
				ItemStack itemstack = this.craftMatrix
						.getStackInSlotOnClosing(i);

				if (itemstack != null) {
					player.dropPlayerItem(itemstack);
				}
			}
		}
	}

	/**
	 * Determines whether the given player is allowed to use this container or
	 * not.
	 * 
	 * @param player
	 *            the player attempting to interact
	 * @return true if the player is allowed to interact
	 */
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return this.tileEntity.isUseableByPlayer(player);
	}

	/**
	 * Used to handle shift-clicking.
	 * 
	 * @param player
	 *            the player doing the shift-clicking
	 * @param i
	 *            index of the slot being shift-clicked
	 * @return the results of the shift click
	 */
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int i) {
		ItemStack tempStack = null;
		Slot slot = (Slot) this.inventorySlots.get(i);

		if (slot != null && slot.getHasStack()) {
			ItemStack slotStack = slot.getStack();
			tempStack = slotStack.copy();

			if (i < 4) {
				// item started off in container, move to player inventory
				if (!this.mergeItemStack(slotStack, 4, 35, true)) {
					return null;
				}
			} else if (!this.mergeItemStack(slotStack, 1, 4, false)) {
				// item was in player's inventory, move to container
				return null;
			}

			if (slotStack.stackSize == 0) {
				slot.putStack((ItemStack) null);
			} else {
				slot.onSlotChanged();
			}

			if (slotStack.stackSize == tempStack.stackSize) {
				return null;
			}

			slot.onPickupFromSlot(player, slotStack);
		}

		return tempStack;
	}
}