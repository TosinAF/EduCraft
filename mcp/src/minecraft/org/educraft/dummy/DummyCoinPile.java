package org.educraft.dummy;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * A DummyCoinPile is obtained by combining nine DummyCoins, and is used as a
 * more efficient way of storing many coins.
 * 
 * @author iak12u
 * 
 */
public class DummyCoinPile extends Item {

	public DummyCoinPile(int itemID) {
		super(itemID);
		setCreativeTab(CreativeTabs.tabMisc);
		setTextureName("gold_ingot");
		setUnlocalizedName("dummyCoinPile");
		setMaxStackSize(16);
	}

}
