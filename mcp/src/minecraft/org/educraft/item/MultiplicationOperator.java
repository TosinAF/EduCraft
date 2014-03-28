package org.educraft.item;

import org.educraft.EduCraft;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class MultiplicationOperator extends Item implements
		MathematicalOperator {
			
	/**
	 * The Multiplication Operator Constructor
	 * 
	 * @param id
	 *            operator id
	 */		
	public MultiplicationOperator(int id) {
		super(id);
		setUnlocalizedName("multOperator");
		setMaxStackSize(4);
		setCreativeTab(EduCraft.tabEduCraft);
		setTextureName("educraft:multiplication");
	}
	
	/**
	 * Returns the Operator Type
	 */
	@Override
	public OperatorType getOperator() {
		return OperatorType.TIMES;
	}

}
