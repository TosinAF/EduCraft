package org.educraft.number.block.calculator;

import org.educraft.number.item.BaseNumber;
import org.educraft.number.item.MathematicalOperator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class CalculatorCraftMatrix extends TileEntity implements IInventory {
	private ItemStack[] contents = new ItemStack[3];
	private Container eventHandler;

	public CalculatorCraftMatrix(Container container) {
		this.eventHandler = container;
	}

	@Override
	public int getSizeInventory() {
		return 3;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return i < getSizeInventory() ? contents[i] : null;
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		if (contents[i] != null) {
			ItemStack tempStack;
			if (contents[i].stackSize <= j) {
				// tried to remove more elements than there are, remove all
				tempStack = contents[i];
				contents[i] = null;
				this.eventHandler.onCraftMatrixChanged(this);
				return tempStack;
			} else {
				// remove the specified number of items
				tempStack = contents[i].splitStack(j);
				if (contents[i].stackSize == 0) {
					contents[i] = null;
				}
				this.eventHandler.onCraftMatrixChanged(this);
				return tempStack;
			}
		} else {
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		if (contents[i] != null) {
			// item in the slot, return it
			ItemStack tempStack = contents[i];
			contents[i] = null;
			return tempStack;
		} else {
			// no item
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		contents[i] = itemstack;
		eventHandler.onCraftMatrixChanged(this);
	}

	@Override
	public String getInvName() {
		return "calculator.crafting";
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
		switch (i) {
		case 0:
		case 2:
			return itemstack.getItem() instanceof BaseNumber;
		case 1:
			return itemstack.getItem() instanceof MathematicalOperator;
		default:
			return false;
		}
	}

}
