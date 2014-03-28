package org.educraft.item;

import org.educraft.EduCraft;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;


public class SubtractionOperator extends Item implements MathematicalOperator {
	
	/**
	 * The Subtraction Operator Constructor
	 * 
	 * @param id
	 *            operator id
	 */
	public SubtractionOperator(int id) {
		super(id);
		setUnlocalizedName("subOperator");
		setMaxStackSize(4);
		setCreativeTab(EduCraft.tabEduCraft);
		setTextureName("educraft:subtraction");
	}
	
	/**
	 * Returns the Operator Type
	 */
	@Override
	public OperatorType getOperator() {
		return OperatorType.MINUS;
	}

}
