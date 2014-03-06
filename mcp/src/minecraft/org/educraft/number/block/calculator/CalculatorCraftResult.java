package org.educraft.number.block.calculator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class CalculatorCraftResult extends TileEntity implements IInventory {
	private ItemStack result;

	public CalculatorCraftResult() {
		result = null;
	}

	@Override
	public int getSizeInventory() {
		return 1;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return i == 0 ? result : null;
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		if (i == 0 && result != null) {
			ItemStack tempStack;
			if (result.stackSize <= j) {
				tempStack = result;
				result = null;
				return tempStack;
			} else {
				tempStack = result.splitStack(j);
				return tempStack;
			}
		} else {
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		if (i == 0 && result != null) {
			ItemStack tempStack = result;
			result = null;
			return tempStack;
		} else {
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		if (i == 0) {
			result = itemstack;
		}
	}

	@Override
	public String getInvName() {
		return "calculator.result";
	}

	@Override
	public boolean isInvNameLocalized() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return true;
	}

	@Override
	public void openChest() {
	}

	@Override
	public void closeChest() {
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return true;
	}

}
