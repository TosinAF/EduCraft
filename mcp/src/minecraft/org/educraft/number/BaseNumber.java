package org.educraft.number;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BaseNumber extends Item {
	public static final String[] NAMES = new String[100];
	static {
		for (int i = 0; i < 100; i++) {
			NAMES[i] = String.format("number%d", i+1);
		}
	}

	public BaseNumber(int itemID) {
		super(itemID);
		setHasSubtypes(true);
		setMaxDamage(0);

		setCreativeTab(CreativeTabs.tabMisc);
		setTextureName("diamond");
		setMaxStackSize(16); // Change this later perhaps
	}

	@Override
	public String getUnlocalizedName(ItemStack is) {
		return NAMES[is.getItemDamage() - 1];
	}
}
