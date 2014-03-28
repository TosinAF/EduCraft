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
	 * The Door Key Constructor
	 * 
	 * @param itemID
	 *           item id
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
	 * Returns a string of the item's name & it's damage value
	 * 
	 * @param is
	 *           An Item Stack
	 */

	@Override
	public String getUnlocalizedName(ItemStack is) {
		return getUnlocalizedName() + "." + NAMES[is.getItemDamage()];
	}
	
	/**
	 * Returns the children items of the parent item
	 */
	 
	@Override
	public void getSubItems(int id, CreativeTabs creativeTab, List list) {
		for (int i = 0; i < NAMES.length; i++) {
			ItemStack stack = new ItemStack(id, 1, i);
			list.add(stack);
		}
	}
	
	/**
	 * Array where every index will contain an icon for each damage level
	 */

	@SideOnly(Side.CLIENT)
	public static Icon[] icons;
	
	/**
	 * Returns the icon for a specified damage level
	 * 
	 * @param damage
	 *           level of damage of the item
	 */

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int damage) {
		return icons[damage];
	}
	
	/**
	 * Populates the icon array with the approitate icons for each damage level
	 * 
	 * @param damage
	 *           level of damage of the item
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
