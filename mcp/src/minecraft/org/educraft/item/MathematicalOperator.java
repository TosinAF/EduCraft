package org.educraft.item;

import net.minecraft.item.Item;

import org.educraft.EduCraft;

public class MathematicalOperator extends Item {
	private OperatorType type;
	
	public MathematicalOperator(int id, OperatorType type) {
		super(id);
		this.type = type;
		
		setUnlocalizedName("operator");
		setMaxStackSize(4);
		setCreativeTab(EduCraft.tabEduCraft);
		setTextureName("educraft:addition");
	}
	
	/**
	 * Interface for the operators
	 */
	public OperatorType getOperator() {
		return this.type;
	}

}
