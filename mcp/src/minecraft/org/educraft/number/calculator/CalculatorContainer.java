package org.educraft.number.calculator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.world.World;

import org.educraft.EduCraft;

public class CalculatorContainer extends ContainerWorkbench {
	private World worldObj;
	private int posX;
	private int posY;
	private int posZ;

	public CalculatorContainer(InventoryPlayer par1InventoryPlayer,
			World par2World, int par3, int par4, int par5) {
		super(par1InventoryPlayer, par2World, par3, par4, par5);
		craftMatrix = new InventoryCrafting(this, 3, 1);
		worldObj = par2World;
		posX = par3;
		posY = par4;
		posZ = par5;
	}

	@Override
	public void onCraftMatrixChanged(IInventory inventory) {
		this.craftResult
				.setInventorySlotContents(
						0,
						CalculatorCraftingManager.getInstance()
								.findMatchingRecipe(this.craftMatrix,
										this.worldObj));

	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return this.worldObj.getBlockId(this.posX, this.posY, this.posZ) != EduCraft.CALCULATOR.blockID ? false
				: player.getDistanceSq((double) this.posX + 0.5D,
						(double) this.posY + 0.5D, (double) this.posZ + 0.5D) <= 64.0D;
	}

}
