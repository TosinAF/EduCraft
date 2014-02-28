package org.educraft.number.calculator;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

import org.educraft.EduCraft;
import org.educraft.number.BaseNumber;
import org.educraft.number.MathematicalOperator;
import org.educraft.number.OperatorType;

public class CalculatorRecipe implements IRecipe {
	private final OperatorType OPERATOR;
	private ItemStack output;

	public CalculatorRecipe(OperatorType operator) {
		this.OPERATOR = operator;
		this.output = new ItemStack(EduCraft.NUMBER);
	}

	public OperatorType getOperator() {
		return this.OPERATOR;
	}

	@Override
	public boolean matches(InventoryCrafting inventory, World world) {
		Item items[] = { null, null, null };
		int j = 0;
		for (int i = 0; i < inventory.getSizeInventory(); i++) {
			if ((inventory.getStackInSlot(i) != null) && (j < items.length)) {
				items[j++] = inventory.getStackInSlot(i).getItem();
			}
		}
		return (items[0] != null && items[0] instanceof BaseNumber)
				&& (items[1] != null && items[1] instanceof MathematicalOperator)
				&& (items[2] != null && items[2] instanceof BaseNumber);
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inventory) {
		ItemStack result = this.getRecipeOutput().copy();

		// get the two operands
		int opr1 = inventory.getStackInSlot(0).getItemDamage();
		int opr2 = inventory.getStackInSlot(2).getItemDamage();
		int eval = 1;

		// compute the result
		// if we go negative, or get a decimal, return 1
		switch (OPERATOR) {
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
		if (eval > 100)
			eval = 1;

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
		return this.output;
	}

}
