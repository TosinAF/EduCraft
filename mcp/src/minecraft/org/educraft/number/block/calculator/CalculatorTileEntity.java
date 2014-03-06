package org.educraft.number.block.calculator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.tileentity.TileEntity;

public class CalculatorTileEntity extends TileEntity {
	private InventoryCrafting craftMatrix;
	private IInventory craftResult;
	
	public CalculatorTileEntity() {
		this.craftMatrix = null;
		this.craftResult = null;
	}

	public CalculatorTileEntity initialise(Container container) {
		if (craftMatrix == null) {
			craftMatrix = new InventoryCrafting(container, 1, 3);
		}
		if (craftResult == null) {
			craftResult = new InventoryCraftResult();
		}
		return this;
	}

	public InventoryCrafting getCraftMatrix() {
		return craftMatrix;
	}

	public IInventory getCraftResult() {
		return craftResult;
	}

}
