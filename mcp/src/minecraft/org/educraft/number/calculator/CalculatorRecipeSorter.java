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

	public int compareRecipes(IRecipe par1IRecipe, IRecipe par2IRecipe) {
		return par1IRecipe instanceof ShapelessRecipes
				&& par2IRecipe instanceof ShapedRecipes ? 1
				: (par2IRecipe instanceof ShapelessRecipes
						&& par1IRecipe instanceof ShapedRecipes ? -1
						: (par2IRecipe.getRecipeSize() < par1IRecipe
								.getRecipeSize() ? -1
								: (par2IRecipe.getRecipeSize() > par1IRecipe
										.getRecipeSize() ? 1 : 0)));
	}

	public int compare(Object par1Obj, Object par2Obj) {
		return this.compareRecipes((IRecipe) par1Obj, (IRecipe) par2Obj);
	}
}
