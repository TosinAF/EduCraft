package org.educraft.number;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;


public class SubtractionOperator extends Item implements MathematicalOperator {
	
	public SubtractionOperator() {
		super(5001);
		setUnlocalizedName("subOperator");
		setMaxStackSize(4);
		setCreativeTab(CreativeTabs.tabMisc);
		setTextureName("educraft:subtraction");
	}

	@Override
	public OperatorType getOperator() {
		return OperatorType.MINUS;
	}

}
