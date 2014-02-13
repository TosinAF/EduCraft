package org.educraft.number;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Numbers extends Item {

	public Numbers(int itemID, int value) {
		super(itemID);
		setHasSubtypes(true);
		setMaxDamage(0);

		setCreativeTab(CreativeTabs.tabMisc);
		setTextureName("diamond");
		setMaxStackSize(16); // Change this later perhaps
		setUnlocalizedName("Number");
	}
}
