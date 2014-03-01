package org.educraft.number.calculator;

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
		this.addRecipe(new ItemStack(EduCraft.NUMBER), "xyx", 'x',
				EduCraft.NUMBER, 'y', EduCraft.ADD_OPR);
		this.addRecipe(new ItemStack(EduCraft.NUMBER), "xyx", 'x',
				EduCraft.NUMBER, 'y', EduCraft.SUB_OPR);
		this.addRecipe(new ItemStack(EduCraft.NUMBER), "xyx", 'x',
				EduCraft.NUMBER, 'y', EduCraft.MUL_OPR);
		this.addRecipe(new ItemStack(EduCraft.NUMBER), "xyx", 'x',
				EduCraft.NUMBER, 'y', EduCraft.DIV_OPR);
	}

	public CalculatorRecipe addRecipe(ItemStack par1ItemStack,
			Object... par2ArrayOfObj) {
		String s = "";
		int i = 0;
		int j = 0;
		int k = 0;

		if (par2ArrayOfObj[i] instanceof String[]) {
			String[] astring = (String[]) ((String[]) par2ArrayOfObj[i++]);

			for (int l = 0; l < astring.length; ++l) {
				String s1 = astring[l];
				++k;
				j = s1.length();
				s = s + s1;
			}
		} else {
			while (par2ArrayOfObj[i] instanceof String) {
				String s2 = (String) par2ArrayOfObj[i++];
				++k;
				j = s2.length();
				s = s + s2;
			}
		}

		HashMap hashmap;

		for (hashmap = new HashMap(); i < par2ArrayOfObj.length; i += 2) {
			Character character = (Character) par2ArrayOfObj[i];
			ItemStack itemstack1 = null;

			if (par2ArrayOfObj[i + 1] instanceof Item) {
				itemstack1 = new ItemStack((Item) par2ArrayOfObj[i + 1]);
			} else if (par2ArrayOfObj[i + 1] instanceof Block) {
				itemstack1 = new ItemStack((Block) par2ArrayOfObj[i + 1], 1,
						32767);
			} else if (par2ArrayOfObj[i + 1] instanceof ItemStack) {
				itemstack1 = (ItemStack) par2ArrayOfObj[i + 1];
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
				par1ItemStack);
		this.recipes.add(shapedrecipe);
		return shapedrecipe;
	}

	public ItemStack findMatchingRecipe(
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
				IRecipe calRecipe = (IRecipe) this.recipes.get(j);

				if (calRecipe.matches(par1InventoryCrafting, par2World)) {
					return calRecipe.getCraftingResult(par1InventoryCrafting);
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