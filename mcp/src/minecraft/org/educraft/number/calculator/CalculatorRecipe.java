package org.educraft.number.calculator;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

import org.educraft.EduCraft;
import org.educraft.number.AdditionOperator;
import org.educraft.number.BaseNumber;
import org.educraft.number.MathematicalOperator;
import org.educraft.number.OperatorType;
import org.educraft.number.SubtractionOperator;

public class CalculatorRecipe implements IRecipe {
	
	/** How many horizontal slots this recipe is wide. */
	public final int recipeWidth;

	/** How many vertical slots this recipe uses. */
	public final int recipeHeight;

	/** Is a array of ItemStack that composes the recipe. */
	public final ItemStack[] recipeItems;

	/** Is the ItemStack that you get when craft the recipe. */
	private ItemStack recipeOutput;

	/** Is the itemID of the output item that you get when craft the recipe. */
	public final int recipeOutputItemID;
	private boolean field_92101_f;

	public CalculatorRecipe(int par1, int par2, ItemStack[] par3ArrayOfItemStack,
			ItemStack par4ItemStack) {
		this.recipeOutputItemID = par4ItemStack.itemID;
		this.recipeWidth = par1;
		this.recipeHeight = par2;
		this.recipeItems = par3ArrayOfItemStack;
		this.recipeOutput = par4ItemStack;
	}
	
	/**
	 * Used to check if a recipe matches current crafting inventory
	 */
	public boolean matches(InventoryCrafting par1InventoryCrafting,
			World par2World) {
		
		for (int i = 0; i <= 3 - this.recipeWidth; ++i) {
			for (int j = 0; j <= 3 - this.recipeHeight; ++j) {
				if (this.checkMatch(par1InventoryCrafting, i, j, true)) {
					return true;
				}

				if (this.checkMatch(par1InventoryCrafting, i, j, false)) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Checks if the region of a crafting inventory is match for the recipe.
	 */
	private boolean checkMatch(InventoryCrafting par1InventoryCrafting,
			int par2, int par3, boolean par4) {
		
		for (int k = 0; k < 3; ++k) {
			for (int l = 0; l < 3; ++l) {
				int i1 = k - par2;
				int j1 = l - par3;
				ItemStack itemstack = null;

				if (i1 >= 0 && j1 >= 0 && i1 < this.recipeWidth
						&& j1 < this.recipeHeight) {
					if (par4) {
						itemstack = this.recipeItems[this.recipeWidth - i1 - 1
								+ j1 * this.recipeWidth];
					} else {
						itemstack = this.recipeItems[i1 + j1 * this.recipeWidth];
					}
				}

				ItemStack itemstack1 = par1InventoryCrafting
						.getStackInRowAndColumn(k, l);

				if (itemstack1 != null || itemstack != null) {
					if (itemstack1 == null && itemstack != null
							|| itemstack1 != null && itemstack == null) {
						return false;
					}

					if (itemstack.itemID != itemstack1.itemID) {
						return false;
					}

//					if (itemstack.getItemDamage() != 32767
//							&& itemstack.getItemDamage() != itemstack1
//									.getItemDamage()) {
//						return false;
//					}
				}
			}
		}

		return true;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inventory) {
		ItemStack result = this.getRecipeOutput().copy();

		// get the two operands
		int opr1 = inventory.getStackInSlot(0).getItemDamage() + 1;
		int opr2 = inventory.getStackInSlot(2).getItemDamage() + 1;
		// get the operator
		MathematicalOperator operator = (MathematicalOperator) inventory.getStackInSlot(1).getItem();
		OperatorType operatorType = operator.getOperator();
		int eval = 1;
		
		// compute the result
		// if we go negative, or get a decimal, return 1
		switch (operatorType) {
		case PLUS:
			eval = opr1 + opr2;
			break;
		case TIMES:
			eval = opr1 * opr2;
			break;
		case MINUS:
			eval = (opr1 > opr2) ? opr1 - opr2 : 1;
			break;
		case DIVIDE:
			eval = (opr1 > opr2) ? opr1 / opr2 : 1;
			break;
		}
		if (eval > 100) eval = 1;
		
		// set metadata - 1 on the returned item stack, and return
		result.setItemDamage(eval - 1);
		return result;		
	}

	@Override
	public int getRecipeSize() {
		return 3;
	}

	@Override
	public ItemStack getRecipeOutput() {
		return this.recipeOutput;
	}

}
