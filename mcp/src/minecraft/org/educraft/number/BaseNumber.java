package org.educraft.number;

import org.educraft.EduCraft;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class BaseNumber extends Item {
	public static final String[] NAMES = new String[EduCraft.MAX_NUMBER];
	static {
		for (int i = 0; i < EduCraft.MAX_NUMBER; i++) {
			NAMES[i] = String.format("number%d", i);
		}
	}

	public BaseNumber(int itemID) {
		super(itemID);
		setHasSubtypes(true);
		setMaxDamage(0);
		setTextureName("diamond");
		setMaxStackSize(1); // Change this later perhaps
		setCreativeTab(EduCraft.tabEduCraft);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack is) {
		return getUnlocalizedName() + "." + NAMES[is.getItemDamage()];
	}
}
