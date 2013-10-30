package org.educraft.dummy;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemSword;

/**
 * The DummySword is an iron sword with the texture of a gold sword. It is used
 * to kill zombies in order to obtain DummyCoins.
 * 
 * @author iak12u
 * 
 */
public class DummySword extends ItemSword {

	/**
	 * Constructor for the DummySword.
	 */
	public DummySword() {
		super(5000, EnumToolMaterial.IRON);
		setCreativeTab(CreativeTabs.tabCombat);
		setTextureName("gold_sword");
		setUnlocalizedName("dummySword");
	}

}
