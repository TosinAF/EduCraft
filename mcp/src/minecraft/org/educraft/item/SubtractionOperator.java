package org.educraft.item;

import org.educraft.EduCraft;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;


public class SubtractionOperator extends Item implements MathematicalOperator {
	
	public SubtractionOperator() {
		super(5001);
		setUnlocalizedName("subOperator");
		setMaxStackSize(4);
		setCreativeTab(EduCraft.tabEduCraft);
		setTextureName("educraft:subtraction");
	}

	@Override
	public OperatorType getOperator() {
		return OperatorType.MINUS;
	}

}
