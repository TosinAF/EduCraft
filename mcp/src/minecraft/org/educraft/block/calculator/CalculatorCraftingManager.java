package org.educraft.block.calculator;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;

import org.educraft.EduCraft;
import org.educraft.item.BaseNumber;
import org.educraft.item.MathematicalOperator;
import org.educraft.item.OperatorType;

/**
 * This class is essentially identical to
 * {@link net.minecraft.item.crafting.CraftingManager}, but because that class
 * implements the singleton pattern, it cannot be extended. Thus, we create a
 * duplicate class providing the desired functionality.
 * 
 * @author ianknight
 * 
 */
public enum CalculatorCraftingManager {
	/**
	 * Singleton instance of the crafting manager.
	 */
	INSTANCE;

	/**
	 * Checks whether the given crafting matrix contains the right components
	 * for any recipe known to this crafting manager.
	 * 
	 * @param matrix
	 *            the crafting matrix to check
	 * @return the result of the recipe, or null if the patten is not recognised
	 */
	public ItemStack findMatchingRecipe(InventoryCrafting matrix) {

		// reject instantly if any stack is empty
		for (int i = 0; i < matrix.getSizeInventory(); i++) {
			if (matrix.getStackInSlot(i) == null) {
				System.err.println("CalculatorCraftingManager: rejecting due to empty slots");
				return null;
			}
		}
		// check that we have numbers in slots 0,2 and an operator
		// in slot 1
		ItemStack stack0 = matrix.getStackInSlot(0), stack1 = matrix
				.getStackInSlot(1), stack2 = matrix.getStackInSlot(2);

		if ((stack0.getItem() instanceof BaseNumber)
				&& (stack1.getItem() instanceof MathematicalOperator)
				&& (stack2.getItem() instanceof BaseNumber)) {
			// inventory contents are correct, accept if the result would be
			// a whole number greater than zero
			MathematicalOperator operator = (MathematicalOperator) stack1
					.getItem();
			int eval = evaluate(stack0, stack2, operator.getType());

			if (eval > 0) {
				ItemStack result = new ItemStack(EduCraft.NUMBER);
				result.setItemDamage(eval);
				return result;
			}

			System.err.println("CalculatorCraftingManager: rejecting due to result < 0");
			return null;

		} else {
			// one or more stacks have the wrong type, reject
			System.err.println("CalculatorCraftingManager: rejecting due to incorrect types");
			return null;
		}

	}
	
	/**
	 * Evaluates the result of an operator on two items
	 * 
	 * @param itemstack
	 *            first item
	 * 
	 * @param itemstack
	 *            second item
	 * 
	 * @param operator
	 *            operator type
	 * 
	 * @return 0, if an error or return final value after using the operator on the two items 
	 */

	private int evaluate(ItemStack x, ItemStack y, OperatorType operator) {
		// result is initially zero, since zero is our 'error code'
		int eval = 0;

		// get the values of the two operands
		int opr1 = x.getItemDamage();
		int opr2 = y.getItemDamage();

		// do the appropriate calculation depending on which
		// operator was given
		switch (operator) {
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
