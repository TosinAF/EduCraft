package org.educraft.number.block.ordering;

import org.educraft.EduCraft;
import org.educraft.number.item.BaseNumber;
import org.educraft.number.item.MathematicalOperator;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class OrderingRecipe implements IRecipe {

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

	public OrderingRecipe(int par1, int par2,
			ItemStack[] par3ArrayOfItemStack, ItemStack par4ItemStack) {
		this.recipeOutputItemID = par4ItemStack.itemID;
		this.recipeWidth = par1;
		this.recipeHeight = par2;
		this.recipeItems = par3ArrayOfItemStack;
		this.recipeOutput = par4ItemStack;
	}

	/*logic goes here*/
	public boolean matches(InventoryCrafting inventory, World world) {
		return false; 
	}

	/*logic goes here*/
	@Override
	public ItemStack getCraftingResult(InventoryCrafting inventory) {
		return null;
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
