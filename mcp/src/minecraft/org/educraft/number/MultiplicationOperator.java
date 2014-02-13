package org.educraft.number;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class MultiplicationOperator extends Item implements
		MathematicalOperator {
	
	public MultiplicationOperator() {
		super(5002);
		setUnlocalizedName("multOperator");
		setMaxStackSize(4);
		setCreativeTab(CreativeTabs.tabMisc);
		setTextureName("educraft:multiplication");
	}

	@Override
	public OperatorType getOperator() {
		return OperatorType.TIMES;
	}

}
