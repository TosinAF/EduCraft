package org.educraft.number;

import org.educraft.EduCraft;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;


public class DivisionOperator extends Item implements MathematicalOperator {

	public DivisionOperator() {
		super(5003);
		setUnlocalizedName("divOperator");
		setMaxStackSize(4);
		setCreativeTab(EduCraft.tabEduCraft);
		setTextureName("educraft:division");
	}
	
	@Override
	public OperatorType getOperator() {
		return OperatorType.DIVIDE;
	}

}
