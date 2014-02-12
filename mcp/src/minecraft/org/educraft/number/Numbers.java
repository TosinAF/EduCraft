package org.educraft.number;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class Numbers extends Item {
	public Numbers(int itemID) {
		super(itemID);
		setCreativeTab(CreativeTabs.tabMisc);
		setTextureName("diamond");
		setMaxStackSize(16); // Change this later perhaps
		setUnlocalizedName("Number");
	}
}
