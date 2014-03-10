package org.educraft.item;

import org.educraft.EduCraft;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;


public class DivisionOperator extends Item implements MathematicalOperator {

	public DivisionOperator(int id) {
		super(id);
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
