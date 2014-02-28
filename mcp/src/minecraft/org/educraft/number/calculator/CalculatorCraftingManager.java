package org.educraft.number.calculator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.world.World;

import org.educraft.number.OperatorType;

/**
 * This class is essentially identical to
 * {@link net.minecraft.item.crafting.CraftingManager}, but because that class
 * implements the singleton pattern, it cannot be extended. Thus, we create a
 * duplicate class providing the desired functionality.
 * 
 * @author ianknight
 * 
 */
public class CalculatorCraftingManager {
	/** The static instance of this class */
	private static final CalculatorCraftingManager INSTANCE = new CalculatorCraftingManager();

	/** A list of all the recipes added */
	private List<IRecipe> recipes = new ArrayList<IRecipe>();

	/**
	 * Returns the static instance of this class
	 */
	public static final CalculatorCraftingManager getInstance() {
		return INSTANCE;
	}

	private CalculatorCraftingManager() {
		this.addRecipe(OperatorType.PLUS);
		this.addRecipe(OperatorType.MINUS);
		this.addRecipe(OperatorType.TIMES);
		this.addRecipe(OperatorType.DIVIDE);
		Collections.sort(this.recipes, new CalculatorRecipeSorter(this));
	}

	public CalculatorRecipe addRecipe(OperatorType opr) {
		CalculatorRecipe recipe = new CalculatorRecipe(opr);
		this.recipes.add(recipe);
		return recipe;
	}

	public ItemStack findMatchingRecipe(InventoryCrafting inventory, World world) {
		// check each recipe in turn for a match
		IRecipe r;
		for (int i = 0; i < this.recipes.size(); i++) {
			r = (IRecipe) this.recipes.get(i);
			if (r.matches(inventory, world)) {
				return r.getCraftingResult(inventory);
			}
		}
		// return null if we find no matches
		return null;
	}

	public ItemStack findMatchingRecipe2(
			InventoryCrafting par1InventoryCrafting, World par2World) {
		int i = 0;
		ItemStack itemstack = null;
		ItemStack itemstack1 = null;
		int j;

		for (j = 0; j < par1InventoryCrafting.getSizeInventory(); ++j) {
			ItemStack itemstack2 = par1InventoryCrafting.getStackInSlot(j);

			if (itemstack2 != null) {
				if (i == 0) {
					itemstack = itemstack2;
				}

				if (i == 1) {
					itemstack1 = itemstack2;
				}

				++i;
			}
		}

		if (i == 2 && itemstack.itemID == itemstack1.itemID
				&& itemstack.stackSize == 1 && itemstack1.stackSize == 1
				&& Item.itemsList[itemstack.itemID].isRepairable()) {
			Item item = Item.itemsList[itemstack.itemID];
			int k = item.getMaxDamage() - itemstack.getItemDamageForDisplay();
			int l = item.getMaxDamage() - itemstack1.getItemDamageForDisplay();
			int i1 = k + l + item.getMaxDamage() * 5 / 100;
			int j1 = item.getMaxDamage() - i1;

			if (j1 < 0) {
				j1 = 0;
			}

			return new ItemStack(itemstack.itemID, 1, j1);
		} else {
			for (j = 0; j < this.recipes.size(); ++j) {
				IRecipe irecipe = (IRecipe) this.recipes.get(j);

				if (irecipe.matches(par1InventoryCrafting, par2World)) {
					return irecipe.getCraftingResult(par1InventoryCrafting);
				}
			}

			return null;
		}
	}

	/**
	 * returns the List<> of all recipes
	 */
	public List getRecipeList() {
		return this.recipes;
	}
}