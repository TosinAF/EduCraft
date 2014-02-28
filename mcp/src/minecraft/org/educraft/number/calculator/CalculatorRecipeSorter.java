package org.educraft.number.calculator;

import java.util.Comparator;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;

/**
 * This class is esentially a clone of
 * {@link net.minecraft.item.crafting.RecipeSorter}. The base class is
 * package-private, preventing subclassing, so we define a new class that is
 * basically a clone of the original (for now).
 * 
 * @author ianknight
 * 
 */
class CalculatorRecipeSorter implements Comparator {
	
	private final CalculatorCraftingManager MANAGER;

	CalculatorRecipeSorter(CalculatorCraftingManager manager) {
		this.MANAGER = manager;
	}

	public int compare(Object par1Obj, Object par2Obj) {
		return this.compareRecipes((IRecipe) par1Obj, (IRecipe) par2Obj);
	}
	
	public int compareRecipes(IRecipe r1, IRecipe r2) {
		if (r1.getClass() == r2.getClass()) {
			// both recipes are of the same type
			if (r1 instanceof CalculatorRecipe) {
				// both recipes are calculator recipes, compare operators
				CalculatorRecipe cr1 = (CalculatorRecipe) r1;
				CalculatorRecipe cr2 = (CalculatorRecipe) r2;
				return cr1.getOperator().compareTo(cr2.getOperator());
			} else {
				// neither recipe is a calculator recipe
				return 1;
			}
		} else {
			// one recipe is a calculator recipe, the other isn't
			return -1;
		}
	}
	
}
