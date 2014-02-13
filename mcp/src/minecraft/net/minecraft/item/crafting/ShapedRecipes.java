package net.minecraft.item.crafting;

//OWN CODE
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import org.educraft.number.AdditionOperator;
import org.educraft.number.DivisionOperator;
import org.educraft.number.MultiplicationOperator;
import org.educraft.number.Numbers;
import org.educraft.number.OperatorType;
import org.educraft.number.SubtractionOperator;

public class ShapedRecipes implements IRecipe {
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

	public ShapedRecipes(int par1, int par2, ItemStack[] par3ArrayOfItemStack,
			ItemStack par4ItemStack) {
		this.recipeOutputItemID = par4ItemStack.itemID;
		this.recipeWidth = par1;
		this.recipeHeight = par2;
		this.recipeItems = par3ArrayOfItemStack;
		this.recipeOutput = par4ItemStack;
	}

	public ItemStack getRecipeOutput() {
		return this.recipeOutput;
	}

	/**
	 * Used to check if a recipe matches current crafting inventory
	 */
	public boolean matches(InventoryCrafting par1InventoryCrafting,
			World par2World) {
		for (int i = 0; i <= 3 - this.recipeWidth; ++i) {
			for (int j = 0; j <= 3 - this.recipeHeight; ++j) {
				if (this.checkMatch(par1InventoryCrafting, i, j, true)) {
					return true;
				}

				if (this.checkMatch(par1InventoryCrafting, i, j, false)) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Checks if the region of a crafting inventory is match for the recipe.
	 */
	private boolean checkMatch(InventoryCrafting par1InventoryCrafting,
			int par2, int par3, boolean par4) {
		for (int k = 0; k < 3; ++k) {
			for (int l = 0; l < 3; ++l) {
				int i1 = k - par2;
				int j1 = l - par3;
				ItemStack itemstack = null;

				if (i1 >= 0 && j1 >= 0 && i1 < this.recipeWidth
						&& j1 < this.recipeHeight) {
					if (par4) {
						itemstack = this.recipeItems[this.recipeWidth - i1 - 1
								+ j1 * this.recipeWidth];
					} else {
						itemstack = this.recipeItems[i1 + j1 * this.recipeWidth];
					}
				}

				ItemStack itemstack1 = par1InventoryCrafting
						.getStackInRowAndColumn(k, l);

				if (itemstack1 != null || itemstack != null) {
					if (itemstack1 == null && itemstack != null
							|| itemstack1 != null && itemstack == null) {
						return false;
					}

					if (itemstack.itemID != itemstack1.itemID) {
						return false;
					}

					if (itemstack.getItemDamage() != 32767
							&& itemstack.getItemDamage() != itemstack1
									.getItemDamage()) {
						return false;
					}
				}
			}
		}

		return true;
	}

	/**
	 * Returns an Item that is the result of this recipe
	 */
	public ItemStack getCraftingResult(InventoryCrafting par1InventoryCrafting) {
		ItemStack output = this.getRecipeOutput().copy();

		if (this.field_92101_f) {
			for (int i = 0; i < par1InventoryCrafting.getSizeInventory(); ++i) {
				ItemStack itemstack1 = par1InventoryCrafting.getStackInSlot(i);

				if (itemstack1 != null && itemstack1.hasTagCompound()) {
					output.setTagCompound((NBTTagCompound) itemstack1.stackTagCompound
							.copy());
				}
			}
		}

		/* CUSTOM CODE FOR HANDLING NUMBER CALCULATIONS */
		if (output.getItem() instanceof Numbers) {
			int opd1 = -1, opd2 = -1, res;
			OperatorType opr = null;

			// get first operand
			for (int i : new int[] { 0, 3, 6 }) {
				if (par1InventoryCrafting.getStackInSlot(i).getItem() instanceof Numbers) {
					opd1 = par1InventoryCrafting.getStackInSlot(i)
							.getItemDamage();
					break;
				}
			}
			// get second operand
			for (int i : new int[] { 2, 5, 8 }) {
				if (par1InventoryCrafting.getStackInSlot(i).getItem() instanceof Numbers) {
					opd2 = par1InventoryCrafting.getStackInSlot(i)
							.getItemDamage();
					break;
				}
			}
			// get operator
			for (int i : new int[] { 1, 4, 7 }) {
				if (par1InventoryCrafting.getStackInSlot(i) != null) {
					Item operator = par1InventoryCrafting.getStackInSlot(i)
							.getItem();
					if (operator instanceof AdditionOperator) {
						opr = OperatorType.PLUS;
					} else if (operator instanceof SubtractionOperator) {
						opr = OperatorType.MINUS;
					} else if (operator instanceof MultiplicationOperator) {
						opr = OperatorType.TIMES;
					} else if (operator instanceof DivisionOperator) {
						opr = OperatorType.DIVIDE;
					}
					break;
				}
			}

			// create new item
			if ((opd1 == -1) || (opd2 == -1) || (opr == null)) {
				res = 1;
			} else
				switch (opr) {
				case PLUS:
					res = opd1 + opd2;
					break;
				case MINUS:
					res = opd1 - opd2;
					break;
				case TIMES:
					res = opd1 * opd2;
					break;
				case DIVIDE:
					res = opd1 / opd2;
					break;
				default:
					res = 1;
					break;
				}
			
			output.setItemDamage(res);
		}

		return output;
	}

	/**
	 * Returns the size of the recipe area
	 */
	public int getRecipeSize() {
		return this.recipeWidth * this.recipeHeight;
	}

	public ShapedRecipes func_92100_c() {
		this.field_92101_f = true;
		return this;
	}
}
