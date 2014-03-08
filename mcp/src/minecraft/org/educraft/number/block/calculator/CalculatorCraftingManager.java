package org.educraft.number.block.calculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

import org.educraft.EduCraft;

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
	// static instance of this class
	private static final CalculatorCraftingManager INSTANCE = new CalculatorCraftingManager();

	// list of recipes know to this crafting manager
	private List<CalculatorRecipe> recipes = new ArrayList<CalculatorRecipe>();

	/**
	 * Returns the singleton instance of this class
	 */
	public static final CalculatorCraftingManager getInstance() {
		return INSTANCE;
	}

	/**
	 * Default constructor.
	 */
	private CalculatorCraftingManager() {
		this.addRecipe(new ItemStack(EduCraft.NUMBER), "xyx", 'x',
				EduCraft.NUMBER, 'y', EduCraft.ADD_OPR);
		this.addRecipe(new ItemStack(EduCraft.NUMBER), "xyx", 'x',
				EduCraft.NUMBER, 'y', EduCraft.SUB_OPR);
		this.addRecipe(new ItemStack(EduCraft.NUMBER), "xyx", 'x',
				EduCraft.NUMBER, 'y', EduCraft.MUL_OPR);
		this.addRecipe(new ItemStack(EduCraft.NUMBER), "xyx", 'x',
				EduCraft.NUMBER, 'y', EduCraft.DIV_OPR);
	}

	/**
	 * Adds a new recipe to this crafting manager.
	 * 
	 * @param result
	 *            item stack containing the result of this recipe
	 * @param components
	 *            array describing the components of this recipe
	 * @return the newly created recipe object
	 */
	public CalculatorRecipe addRecipe(ItemStack result, Object... components) {
		String s = "";
		int i = 0;
		int j = 0;
		int k = 0;

		if (components[i] instanceof String[]) {
			String[] astring = (String[]) ((String[]) components[i++]);

			for (int l = 0; l < astring.length; ++l) {
				String s1 = astring[l];
				++k;
				j = s1.length();
				s = s + s1;
			}
		} else {
			while (components[i] instanceof String) {
				String s2 = (String) components[i++];
				++k;
				j = s2.length();
				s = s + s2;
			}
		}

		HashMap hashmap;

		for (hashmap = new HashMap(); i < components.length; i += 2) {
			Character character = (Character) components[i];
			ItemStack itemstack1 = null;

			if (components[i + 1] instanceof Item) {
				itemstack1 = new ItemStack((Item) components[i + 1]);
			} else if (components[i + 1] instanceof Block) {
				itemstack1 = new ItemStack((Block) components[i + 1], 1, 32767);
			} else if (components[i + 1] instanceof ItemStack) {
				itemstack1 = (ItemStack) components[i + 1];
			}

			hashmap.put(character, itemstack1);
		}

		ItemStack[] aitemstack = new ItemStack[j * k];

		for (int i1 = 0; i1 < j * k; ++i1) {
			char c0 = s.charAt(i1);

			if (hashmap.containsKey(Character.valueOf(c0))) {
				aitemstack[i1] = ((ItemStack) hashmap
						.get(Character.valueOf(c0))).copy();
			} else {
				aitemstack[i1] = null;
			}
		}

		CalculatorRecipe shapedrecipe = new CalculatorRecipe(j, k, aitemstack,
				result);
		this.recipes.add(shapedrecipe);
		return shapedrecipe;
	}

	/**
	 * Checks whether the given crafting matrix contains the right components
	 * for any recipe known to this crafting manager.
	 * 
	 * @param matrix
	 *            the crafting matrix to check
	 * @param world
	 *            the world associated with this crafting manager
	 * @return the result of the recipe, or null if the patten is not recognised
	 */
	public ItemStack findMatchingRecipe(InventoryCrafting matrix, World world) {
		int i = 0;
		ItemStack itemstack = null;
		ItemStack itemstack1 = null;
		int j;

		for (j = 0; j < matrix.getSizeInventory(); ++j) {
			ItemStack itemstack2 = matrix.getStackInSlot(j);

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
				CalculatorRecipe calRecipe = this.recipes.get(j);

				if (calRecipe.matches(matrix, world)) {
					return calRecipe.getCraftingResult(matrix);
				}
			}

			return null;
		}
	}

	/**
	 * Returns the List of all recipes known to this crafting manager.
	 */
	public List getRecipeList() {
		return this.recipes;
	}
}