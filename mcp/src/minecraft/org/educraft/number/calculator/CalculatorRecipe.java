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

	public CalculatorRecipe(int par1, int par2,
			ItemStack[] par3ArrayOfItemStack, ItemStack par4ItemStack) {
		this.recipeOutputItemID = par4ItemStack.itemID;
		this.recipeWidth = par1;
		this.recipeHeight = par2;
		this.recipeItems = par3ArrayOfItemStack;
		this.recipeOutput = par4ItemStack;
	}

	public boolean matches(InventoryCrafting inventory, World world) {
		// check if any slot is empty
		for (int i = 0; i < inventory.getSizeInventory(); i++) {
			if (inventory.getStackInSlot(i) == null) {
				return false;
			}
		}
		// check that we have numbers in slots 0,2 and an operator
		// in slot 1
		if (!((inventory.getStackInSlot(0).getItem() instanceof BaseNumber)
				&& (inventory.getStackInSlot(1).getItem() instanceof MathematicalOperator) && (inventory
				.getStackInSlot(2).getItem() instanceof BaseNumber))) {

			return false;
		}

		return eval(inventory.getStackInSlot(0), inventory.getStackInSlot(2),
				(MathematicalOperator) inventory.getStackInSlot(1).getItem()) > 0;
	}

	private int eval(ItemStack x, ItemStack y, MathematicalOperator operator) {

		int opr1 = x.getItemDamage();
		int opr2 = y.getItemDamage();

		int eval = 0;

		switch (operator.getOperator()) {
		case PLUS:
			eval = opr1 + opr2;
			break;
		case TIMES:
			eval = opr1 * opr2;
			break;
		case MINUS:
			eval = (opr1 > opr2) ? opr1 - opr2 : 0;
			break;
		case DIVIDE:
			eval = (opr1 > opr2) ? opr1 / opr2 : 0;
			break;
		}
		if (eval > EduCraft.MAX_NUMBER)
			eval = 0;

		return eval;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inventory) {
		ItemStack result = this.getRecipeOutput().copy();

		int eval = eval(inventory.getStackInSlot(0),
				inventory.getStackInSlot(2), (MathematicalOperator) inventory
						.getStackInSlot(1).getItem());

		// set metadata - 1 on the returned item stack, and return
		result.setItemDamage(eval);
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
