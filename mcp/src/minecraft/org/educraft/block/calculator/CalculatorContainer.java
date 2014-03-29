package org.educraft.block.calculator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import org.educraft.EduCraft;
import org.educraft.block.CraftingTileEntity;

/**
 * This class is used to keep track of a particular {@link BlockCalculator}'s
 * inventory. It manages multiple users, allowing several players to use the
 * same calculator.
 */
public class CalculatorContainer extends Container {

	// data members holding the crafting inventory
	private CraftingTileEntity tileEntity;
	private InventoryCrafting craftMatrix;
	private IInventory craftResult;

	// reference to the world this container is in
	private World worldObj;

	public CalculatorContainer(InventoryPlayer inventory,
			CraftingTileEntity tileEntity, World world) {
		// set inventory
		this.tileEntity = tileEntity.initialise(this);
		this.craftMatrix = tileEntity.getCraftMatrix();
		this.craftResult = tileEntity.getCraftResult();
		// set world
		this.worldObj = world;

		// adds output slot to the container
		this.addSlotToContainer(new SlotCrafting(inventory.player,
				this.craftMatrix, this.craftResult, 0, 124, 35));
		int i1;

		// adds input slots to the container
		for (i1 = 0; i1 < 3; ++i1) {
			this.addSlotToContainer(new Slot(this.craftMatrix, i1,
					30 + i1 * 18, 35));
		}

		// adds slots of the WorkBench's inventory area to the container 
		for (int l = 0; l < 3; ++l) {
			for (i1 = 0; i1 < 9; ++i1) {
				this.addSlotToContainer(new Slot(inventory, i1 + l * 9 + 9,
						8 + i1 * 18, 84 + l * 18));
			}
		}

		// adds slots of player's in hand inventory to the container
		for (int l = 0; l < 9; ++l) {
			this.addSlotToContainer(new Slot(inventory, l, 8 + l * 18, 142));
		}

		this.onCraftMatrixChanged(this.craftMatrix);
	}

	/**
	 * Called whenever someone makes a change to the crafting matrix, to update
	 * the crafting result.
	 * 
	 * @param inventory
	 *            the crafting matrix that was changed
	 */
	@Override
	public void onCraftMatrixChanged(IInventory inventory) {
		this.craftResult.setInventorySlotContents(0,
				CalculatorCraftingManager.INSTANCE
						.findMatchingRecipe(this.craftMatrix));
	}

	/**
	 * Called whenever the container is closed. If no one is using the container
	 * any more, then it should drop everything inside it, like a crafting
	 * table.
	 * 
	 * @param player
	 *            the player who closed the container
	 */
	@Override
	public void onContainerClosed(EntityPlayer player) {
		super.onContainerClosed(player);
		this.tileEntity.decrUsers();

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
	 * Checks whether or not the player is allowed to see or make changes to
	 * this tile entity.
	 * 
	 * @param player
	 *            the player attempting to interact
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
	 *            index of the slot that was shift-clicked
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
				// item was in container, place in player's inventory
				if (!this.mergeItemStack(slotStack, 4, 35, true)) {
					return null;
				}
			} else if (!this.mergeItemStack(slotStack, 1, 4, false)) {
				// item was in inventory, place in container
				return null;
			}

			// update the slot appropriately
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
