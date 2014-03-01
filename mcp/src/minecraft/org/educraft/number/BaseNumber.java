package org.educraft.number;

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

	public BaseNumber(int itemID) {
		super(itemID);
		setHasSubtypes(true);
		setMaxDamage(0);
		setMaxStackSize(1); // Change this later perhaps
		setCreativeTab(EduCraft.tabEduCraft);
		setUnlocalizedName("number");
	}

	@Override
	public String getUnlocalizedName(ItemStack is) {
		return getUnlocalizedName() + "." + String.valueOf(is.getItemDamage());
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
		icons = new Icon[EduCraft.MAX_NUMBER];
		
		for(int i = 0; i < EduCraft.MAX_NUMBER; i++) {
			
			icons[i] = icon.registerIcon("educraft:" + i);
		}
	}
}
