package org.educraft.item;

import net.minecraft.item.Item;

import org.educraft.EduCraft;

public class MathematicalOperator extends Item {
	private OperatorType type;
	
	/**
	 * Math Operator Constructor 
	 * for creating new math operators for players to 
	 * use to combine numbers to match a value 
	 * that allows the player to progress to the next level
	 * 
	 * @param id
	 *          id of the operator
	 * 
	 * @param type
	 *          the operator type
	 * 
	 */
	
	public MathematicalOperator(int id, OperatorType type) {
		super(id);
		this.type = type;
		
		setUnlocalizedName("operator." + type.getName());
		setMaxStackSize(4);
		setCreativeTab(EduCraft.tabEduCraft);
		setTextureName("educraft:" + type.getName());
	}
	
	/**
	 * Interface for the operators
	 */
	public OperatorType getType() {
		return this.type;
	}

}
