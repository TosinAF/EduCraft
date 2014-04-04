package org.educraft.item;

import java.util.List;

import org.educraft.EduCraft;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

/**
 * A number between 1 and {@link org.educraft.EduCraft.MAX_NUMBER}.
 * 
 * This can be dropped by a hostile mob, or collected through other means.
 *
 */
public class BaseNumber extends Item {

	/**
	 * Class constructor.
	 * 
	 * @param itemId
	 *            unique item ID to assign to this item
	 */
	public BaseNumber(int itemId) {
		super(itemId);
		setHasSubtypes(true);
		setMaxDamage(0);
		setMaxStackSize(4);
		setCreativeTab(EduCraft.tabEduCraft);
		setUnlocalizedName("number");
	}

	/**
	 * Returns a string used to identify this item internally. Takes the format
	 * "number.x", where x is the value of the number.
	 * 
	 * @param is
	 *            the item stack to get the name of
	 */
	@Override
	public String getUnlocalizedName(ItemStack is) {
		return getUnlocalizedName() + "." + String.valueOf(is.getItemDamage());
	}

	/**
	 * Contains icons for every possible metadata value of this item.
	 */
	@SideOnly(Side.CLIENT)
	public static Icon[] icons;

	/**
	 * Returns the appropriate icon to display this item, based on its metadata.
	 * 
	 * @param damage
	 *            level of damage of the item
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int damage) {
		return icons[damage];
	}

	/**
	 * Populates the icon array with the approitate icons for each damage level
	 * 
	 * @param icon
	 *            the icon store we will add our icons to
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister icon) {
		icons = new Icon[EduCraft.MAX_NUMBER];

		for (int i = 0; i < EduCraft.MAX_NUMBER; i++) {

			icons[i] = icon.registerIcon("educraft:" + i);
		}
	}

	/**
	 * Populates the given list with instances of this class. Every possible
	 * metadata value is added, so that we can display them all in the creative
	 * mode inventory.
	 * 
	 * @param id
	 *            the id of the item to register
	 * @param tab
	 *            the creative tab to display the items in
	 * @param list
	 *            the list to add the metadata items to
	 */
	@Override
	public void getSubItems(int id, CreativeTabs tab, List list) {
		for (int i = 0; i < EduCraft.MAX_NUMBER; i++) {
			ItemStack itemstack = new ItemStack(id, 1, i);
			list.add(itemstack);
		}
	}
}
