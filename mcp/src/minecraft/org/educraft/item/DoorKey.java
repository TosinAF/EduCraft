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

	@SideOnly(Side.CLIENT)
	public static Icon[] icons;

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int damage) {
		return icons[damage];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister icon) {
		icons = new Icon[NAMES.length];
		for (int i = 0; i < NAMES.length; i++) {
			icons[i] = icon.registerIcon("educraft:" + NAMES[i]);
		}
	}
}
