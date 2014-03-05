package org.educraft.number.block.calculator;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

import org.educraft.EduCraft;
import org.educraft.number.item.AdditionOperator;
import org.educraft.number.item.BaseNumber;
import org.educraft.number.item.MathematicalOperator;
import org.educraft.number.item.OperatorType;
import org.educraft.number.item.SubtractionOperator;

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
		// reject instantly if any stack is empty
		for (int i = 0; i < inventory.getSizeInventory(); i++) {
			if (inventory.getStackInSlot(i) == null) {
				return false;
			}
		}
		// check that we have numbers in slots 0,2 and an operator
		// in slot 1
		ItemStack stack0 = inventory.getStackInSlot(0), stack1 = inventory
				.getStackInSlot(1), stack2 = inventory.getStackInSlot(2);
		
		if ((stack0.getItem() instanceof BaseNumber)
				&& (stack1.getItem() instanceof MathematicalOperator)
				&& (stack2.getItem() instanceof BaseNumber)) {
			// inventory contents are correct, accept if the result would be
			// a whole number greater than zero
			return evaluate(stack0, stack2,
					(MathematicalOperator) stack1.getItem()) > 0;
		} else {
			// one or more stacks have the wrong type, reject
			return false;
		}
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inventory) {
		ItemStack result = this.getRecipeOutput().copy();

		// get the result of the calculation
		//
		// note that this method only gets called once inventory has
		// been checked for correctness, so we don't need to check the
		// types of the item stacks again
		int eval = evaluate(inventory.getStackInSlot(0),
				inventory.getStackInSlot(2), (MathematicalOperator) inventory
						.getStackInSlot(1).getItem());

		// set metadata on the returned item stack, and return
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

	/**
	 * Evaluates the result of applying the given operator to the two given
	 * operands.
	 * <p/>
	 * Note that the method takes two {@link net.minecraft.item.ItemStack}s, not
	 * {@link org.educraft.number.item.BaseNumber}s. This is because the value of a
	 * number item is determined by the damage of the stack it is in - if we
	 * pass the actual number, we can't do the evaluation.
	 * 
	 * @param x
	 *            an ItemStack containing the left-hand operand
	 * @param y
	 *            an ItemStack containing the right-hand operand
	 * @param operator
	 * @return the result of the evaluation, or 0 if the result would be
	 *         negative, fractional, or too large
	 */
	private int evaluate(ItemStack x, ItemStack y, MathematicalOperator operator) {
		// result is initially zero, since zero is our 'error code'
		int eval = 0;
		
		// get the values of the two operands
		int opr1 = x.getItemDamage();
		int opr2 = y.getItemDamage();

		// do the appropriate calculation depending on which
		// operator was given
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
			// need to cast to double so that we can check for remainders
			double tempEval = (double) opr1 / opr2;
			eval = (tempEval % 1 == 0) ? (int) tempEval : 0;
			break;
		}

		// if the result would be too large, reject
		if (eval > EduCraft.MAX_NUMBER) {
			eval = 0;
		}

		return eval;
	}

}
