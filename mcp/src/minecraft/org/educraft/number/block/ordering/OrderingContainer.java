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

public class OrderingContainer extends Container{

	public InventoryCrafting craftMatrix;
	public IInventory craftResult;
	private World worldObj;
	private int posX;
	private int posY;
	private int posZ;

	public OrderingContainer(InventoryPlayer par1InventoryPlayer,
			World par2World, int par3, int par4, int par5) {

		craftMatrix = new InventoryCrafting(this, 1, 3);
		craftResult = new InventoryCraftResult();
		worldObj = par2World;
		posX = par3;
		posY = par4;
		posZ = par5;

		this.addSlotToContainer(new SlotCrafting(par1InventoryPlayer.player,
				this.craftMatrix, this.craftResult, 0, 142, 35));
		int i1;

		for (i1 = 0; i1 < 3; ++i1) {
			this.addSlotToContainer(new Slot(this.craftMatrix, i1,
					10 + i1 * 36, 35));

		}

		for (int l = 0; l < 3; ++l) {
			for (i1 = 0; i1 < 9; ++i1) {
				this.addSlotToContainer(new Slot(par1InventoryPlayer, i1 + l
						* 9 + 9, 8 + i1 * 18, 84 + l * 18));
			}
		}

		for (int l = 0; l < 9; ++l) {
			this.addSlotToContainer(new Slot(par1InventoryPlayer, l,
					8 + l * 18, 142));
		}

		this.onCraftMatrixChanged(this.craftMatrix);

	}

	public void onCraftMatrixChanged(IInventory inventory) {
		this.craftResult.setInventorySlotContents(
				0,
				OrderingCraftingManager.getInstance().findMatchingRecipe(
						this.craftMatrix, this.worldObj));

	}

	public void onContainerClosed(EntityPlayer par1EntityPlayer) {
		super.onContainerClosed(par1EntityPlayer);

		if (!this.worldObj.isRemote) {
			for (int i = 0; i < 3; ++i) {
				ItemStack itemstack = this.craftMatrix
						.getStackInSlotOnClosing(i);

				if (itemstack != null) {
					par1EntityPlayer.dropPlayerItem(itemstack);
				}
			}
		}
	}

	public boolean canInteractWith(EntityPlayer player) {
		return this.worldObj.getBlockId(this.posX, this.posY, this.posZ) != EduCraft.ORDERING_BENCH.blockID ? false
				: player.getDistanceSq((double) this.posX + 0.5D,
						(double) this.posY + 0.5D, (double) this.posZ + 0.5D) <= 64.0D;
	}

	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
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

			slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
		}

		return itemstack;
	}
}