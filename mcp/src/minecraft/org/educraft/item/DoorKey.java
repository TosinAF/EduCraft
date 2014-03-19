package org.educraft.item;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import org.educraft.EduCraft;

public class DoorKey extends Item {
	public static final String[] NAMES = { "door", "red", "blue" };

	public DoorKey(int itemId) {
		super(itemId);

		// enable metadata
		setHasSubtypes(true);
		setMaxDamage(0);

		// display details
		setUnlocalizedName("doorKey");
		setMaxStackSize(4);
		setCreativeTab(EduCraft.tabEduCraft);
		setTextureName("educraft:key_yellow");
	}

	@Override
	public String getUnlocalizedName(ItemStack is) {
		return getUnlocalizedName() + "." + NAMES[is.getItemDamage()];
	}

	@Override
	public void getSubItems(int id, CreativeTabs creativeTab, List list) {
		for (int i = 0; i < NAMES.length; i++) {
			ItemStack stack = new ItemStack(id, 1, i);
			list.add(stack);
		}
	}
}
