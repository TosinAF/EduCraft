package org.educraft.dummy;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * The DummyCoin is an item obtained by killing zombies with a DummySword. It is
 * used to assemble a DummyCoinPile.
 * 
 * @author iak12u
 * 
 */
public class DummyCoin extends Item {

	/**
	 * Constructor for a DummyCoin.
	 */
	public DummyCoin() {
		super(6000);
		setCreativeTab(CreativeTabs.tabMisc);
		setTextureName("emerald");
		setMaxStackSize(64);
		setUnlocalizedName("dummyCoin");
	}

}
