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
			CraftingTileEntity tileEntity, World world) {
		// set up the crafting inventories
		this.tileEntity = tileEntity.initialise(this);
		this.craftMatrix = tileEntity.getCraftMatrix();
		this.craftResult = tileEntity.getCraftResult();

		this.worldObj = world;

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
	public void onCraftMatrixChanged(IInventory inventory) {
		this.craftResult.setInventorySlotContents(0, OrderingCraftingManager
				.getInstance().findMatchingRecipe(this.craftMatrix));
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
	public boolean canInteractWith(EntityPlayer player) {
		return this.tileEntity.isUseableByPlayer(player);
	}

	/**
	 * Used to handle shift-clicking.
	 * 
	 * @param player
	 *            the player doing the shift-clicking
	 * @param par2
	 *            unknown
	 * @return the results of the shift click
	 */
	public ItemStack transferStackInSlot(EntityPlayer player, int par2) {
		ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(par2);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (par2 == 0) {
				if (!this.mergeItemStack(itemstack1, 10, 46, true)) {
					return null;
				}

				slot.onSlotChange(itemstack1, itemstack);
			} else if (par2 >= 10 && par2 < 37) {
				if (!this.mergeItemStack(itemstack1, 37, 46, false)) {
					return null;
				}
			} else if (par2 >= 37 && par2 < 46) {
				if (!this.mergeItemStack(itemstack1, 10, 37, false)) {
					return null;
				}
			} else if (!this.mergeItemStack(itemstack1, 10, 46, false)) {
				return null;
			}

			if (itemstack1.stackSize == 0) {
				slot.putStack((ItemStack) null);
			} else {
				slot.onSlotChanged();
			}

			if (itemstack1.stackSize == itemstack.stackSize) {
				return null;
			}

			slot.onPickupFromSlot(player, itemstack1);
		}

		return itemstack;
	}
}