package org.educraft.number;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class Number15 extends Item {
	public Number15(int itemID) {
		super(itemID);
		setCreativeTab(CreativeTabs.tabMisc);
		setTextureName("diamond");
		setMaxStackSize(16); // Change this later perhaps
		setUnlocalizedName("Number15");
	}
}
