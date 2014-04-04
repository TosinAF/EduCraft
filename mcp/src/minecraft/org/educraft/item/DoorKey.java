package org.educraft.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

import org.educraft.EduCraft;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class DoorKey extends Item {
	public static final String[] NAMES = { "key_red", "key_blue", "key_yellow" };

	/**
	 * Class constructor.
	 * 
	 * @param itemId
	 *            the unique item ID to assign to this item
	 */

	public DoorKey(int itemId) {
		super(itemId);

		// enable metadata
		setHasSubtypes(true);
		setMaxDamage(0);

		// display details
		setUnlocalizedName("doorKey");
		setMaxStackSize(4);
		setCreativeTab(EduCraft.tabEduCraft);
	}

	/**
	 * Returns a string used to identify this item internally. Takes the form
	 * "doorKey.x", where x is the item's metadata.
	 * 
	 * @param is
	 *            the item stack to get the name of
	 */
	@Override
	public String getUnlocalizedName(ItemStack is) {
		return getUnlocalizedName() + "." + NAMES[is.getItemDamage()];
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
	public void getSubItems(int id, CreativeTabs creativeTab, List list) {
		for (int i = 0; i < NAMES.length; i++) {
			ItemStack stack = new ItemStack(id, 1, i);
			list.add(stack);
		}
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
		icons = new Icon[NAMES.length];
		for (int i = 0; i < NAMES.length; i++) {
			icons[i] = icon.registerIcon("educraft:" + NAMES[i]);
		}
	}
}
