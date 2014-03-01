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
	
	public boolean matches(InventoryCrafting par1InventoryCrafting,
			World par2World) {
		
		if(par1InventoryCrafting.getStackInSlot(0) == null) {
			return false;
		}
		
		if(par1InventoryCrafting.getStackInSlot(1) == null) {
			return false;
		}
		
		if(par1InventoryCrafting.getStackInSlot(2) == null) {
			return false;
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
