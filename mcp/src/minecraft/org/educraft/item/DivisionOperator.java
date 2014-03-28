package org.educraft.item;

import org.educraft.EduCraft;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;


public class DivisionOperator extends Item implements MathematicalOperator {
	/**
	 * The Division Operator Constructor
	 * 
	 * @param id
	 *            operator id
	 */
	public DivisionOperator(int id) {
		super(id);
		setUnlocalizedName("divOperator");
		setMaxStackSize(4);
		setCreativeTab(EduCraft.tabEduCraft);
		setTextureName("educraft:division");
	}
	
	/**
	 * Returns the Operator Type
	 */
	@Override
	public OperatorType getOperator() {
		return OperatorType.DIVIDE;
	}

}
