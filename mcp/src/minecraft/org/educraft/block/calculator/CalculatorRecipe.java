package org.educraft.block.calculator;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

import org.educraft.EduCraft;
import org.educraft.item.BaseNumber;
import org.educraft.item.MathematicalOperator;

/**
 * This class represents a recipe that is known to a CalculatorCraftingManager.
 */
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

	/**
	 * Determines whether the given crafting matrix matches the expected input
	 * of this recipe.
	 * 
	 * @param inventory
	 *            the crafting matrix to check
	 * @param world
	 *            the world this inventory is in
	 * @return true if the crafting matrix contains a correct pattern
	 */
	@Override
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

	/**
	 * Returns an ItemStack containing the output of this recipe. We first
	 * obtain the values of the two operands, compute the result, and then set
	 * the metadata of the returned ItemStack accordingly.
	 * 
	 * @param inventory
	 *            the crafting matrix containing the components for this recipe
	 * @return the crafting result
	 */
	@Override
	public ItemStack getCraftingResult(InventoryCrafting inventory) {
		ItemStack result = this.getRecipeOutput().copy();

		// get the result of the calculation
		int eval = evaluate(inventory.getStackInSlot(0),
				inventory.getStackInSlot(2), (MathematicalOperator) inventory
						.getStackInSlot(1).getItem());

		// set metadata on the returned item stack, and return
		result.setItemDamage(eval);
		return result;
	}

	/**
	 * Returns the size (number of slots) contained in this recipe.
	 * 
	 * @return the recipe size
	 */
	@Override
	public int getRecipeSize() {
		return 3;
	}

	/**
	 * Returns the expected output of this recipe.
	 * 
	 * @return the expected output
	 */
	@Override
	public ItemStack getRecipeOutput() {
		return this.recipeOutput;
	}

	/**
	 * Evaluates the result of applying the given operator to the two given
	 * operands.
	 * <p/>
	 * Note that the method takes two {@link net.minecraft.item.ItemStack}s, not
	 * {@link org.educraft.item.BaseNumber}s. This is because the value
	 * of a number item is determined by the damage of the stack it is in - if
	 * we pass the actual number, we can't do the evaluation.
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
