package org.educraft.number.block.ordering;

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
import org.educraft.number.block.BlockTileEntity;

public class OrderingContainer extends Container{

	private BlockTileEntity tileEntity;
	private InventoryCrafting craftMatrix;
	private IInventory craftResult;
	
	private World worldObj;

	public OrderingContainer(InventoryPlayer inventory,
			BlockTileEntity tileEntity, World world) {

		this.tileEntity = tileEntity.initialise(this);
		this.craftMatrix = tileEntity.getCraftMatrix();
		this.craftResult = tileEntity.getCraftResult();
		
		this.worldObj = world;

		this.addSlotToContainer(new SlotCrafting(inventory.player,
				this.craftMatrix, this.craftResult, 0, 142, 35));
		int i1;

		for (i1 = 0; i1 < 3; ++i1) {
			this.addSlotToContainer(new Slot(this.craftMatrix, i1,
					10 + i1 * 36, 35));

		}

		for (int l = 0; l < 3; ++l) {
			for (i1 = 0; i1 < 9; ++i1) {
				this.addSlotToContainer(new Slot(inventory, i1 + l
						* 9 + 9, 8 + i1 * 18, 84 + l * 18));
			}
		}

		for (int l = 0; l < 9; ++l) {
			this.addSlotToContainer(new Slot(inventory, l,
					8 + l * 18, 142));
		}

		this.onCraftMatrixChanged(this.craftMatrix);

	}

	public void onCraftMatrixChanged(IInventory inventory) {
		this.craftResult.setInventorySlotContents(
				0,
				OrderingCraftingManager.getInstance().findMatchingRecipe(
						this.craftMatrix));

	}

	public void onContainerClosed(EntityPlayer player) {
		super.onContainerClosed(player);

		if (!this.worldObj.isRemote) {
			for (int i = 0; i < 3; ++i) {
				ItemStack itemstack = this.craftMatrix
						.getStackInSlotOnClosing(i);

				if (itemstack != null) {
					player.dropPlayerItem(itemstack);
				}
			}
		}
	}

	public boolean canInteractWith(EntityPlayer player) {
		return this.tileEntity.isUseableByPlayer(player);
	}

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