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

public class BaseNumber extends Item {
	
	/**
	 * The Base Number Constructor
	 * 
	 * @param itemID
	 *           item id
	 */
	public BaseNumber(int itemID) {
		super(itemID);
		setHasSubtypes(true);
		setMaxDamage(0);
		setMaxStackSize(4);
		setCreativeTab(EduCraft.tabEduCraft);
		setUnlocalizedName("number");
	}
	
	/**
	 * Returns a string of the item's name & it's damage value
	 * 
	 * @param is
	 *           An Item Stack
	 */
	@Override
	public String getUnlocalizedName(ItemStack is) {
		return getUnlocalizedName() + "." + String.valueOf(is.getItemDamage());
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
		icons = new Icon[EduCraft.MAX_NUMBER];

		for (int i = 0; i < EduCraft.MAX_NUMBER; i++) {

			icons[i] = icon.registerIcon("educraft:" + i);
		}
	}
	
	/**
	 * Returns the children items of the parent item
	 */
	@Override
	public void getSubItems(int id, CreativeTabs tab, List list) {
		for (int i = 0; i < EduCraft.MAX_NUMBER; i++) {
			ItemStack itemstack = new ItemStack(id, 1, i);
			list.add(itemstack);
		}
	}
}
