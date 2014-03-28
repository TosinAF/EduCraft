package org.educraft.item;

import org.educraft.EduCraft;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class AdditionOperator extends Item implements MathematicalOperator {
	
	/**
	 * The Addition Operator Constructor
	 * 
	 * @param id
	 *            operator id
	 */
	public AdditionOperator(int id) {
		super(id);
		setUnlocalizedName("addOperator");
		setMaxStackSize(4);
		setCreativeTab(EduCraft.tabEduCraft);
		setTextureName("educraft:addition");
	}
	
	/**
	 * Returns the Operator Type
	 */
	@Override
	public OperatorType getOperator() {
		// TODO Auto-generated method stub
		return OperatorType.PLUS;
	}

}
