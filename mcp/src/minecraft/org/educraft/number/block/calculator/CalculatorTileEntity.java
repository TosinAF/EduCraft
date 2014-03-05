package org.educraft.number.block.calculator;

import org.educraft.number.item.BaseNumber;
import org.educraft.number.item.MathematicalOperator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class CalculatorTileEntity extends TileEntity implements IInventory {
	private ItemStack[] contents = new ItemStack[4];

	@Override
	public int getSizeInventory() {
		return 4;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return this.contents[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		if (this.contents[i] != null) {
			ItemStack tempStack;

			if (this.contents[i].stackSize <= j) {
				tempStack = this.contents[i];
				this.contents[i] = null;
				this.onInventoryChanged();
				return tempStack;
			} else {
				tempStack = this.contents[i].splitStack(j);
				if (this.contents[i].stackSize == 0) {
					this.contents[i] = null;
				}
				this.onInventoryChanged();
				return tempStack;
			}
		} else {
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		if (this.contents[i] != null) {
			ItemStack itemstack = this.contents[i];
			this.contents[i] = null;
			return itemstack;
		} else {
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		this.contents[i] = itemstack;

		if (itemstack != null
				&& itemstack.stackSize > this.getInventoryStackLimit()) {
			itemstack.stackSize = this.getInventoryStackLimit();
		}

		this.onInventoryChanged();
	}

	@Override
	public String getInvName() {
		return "Calculator";
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
		return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord,
				this.zCoord) != this ? false : entityplayer.getDistanceSq(
				(double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D,
				(double) this.zCoord + 0.5D) <= 64.0D;

	}

	@Override
	public void openChest() {
		// TODO Auto-generated method stub

	}

	@Override
	public void closeChest() {
		// TODO Auto-generated method stub

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
