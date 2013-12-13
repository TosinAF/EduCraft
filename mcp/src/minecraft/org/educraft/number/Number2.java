package org.educraft.number;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class Number2 extends Item {
	public Number2(int itemID) {
		super(itemID);
		setCreativeTab(CreativeTabs.tabMisc);
		setTextureName("emerald");
		setMaxStackSize(1); // Change this later perhaps
		setUnlocalizedName("Number2");
	}
}
