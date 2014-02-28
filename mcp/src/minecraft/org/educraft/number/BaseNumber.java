package org.educraft.number;

import org.educraft.EduCraft;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class BaseNumber extends Item {

	public BaseNumber(int itemID) {
		super(itemID);
		setHasSubtypes(true);
		setMaxDamage(0);
		setTextureName("diamond");
		setMaxStackSize(1); // Change this later perhaps
		setCreativeTab(EduCraft.tabEduCraft);
		setUnlocalizedName("number");
	}

	@Override
	public String getUnlocalizedName(ItemStack is) {
		return getUnlocalizedName() + "." + String.valueOf(is.getItemDamage());
	}
}
