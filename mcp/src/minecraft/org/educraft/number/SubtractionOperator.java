package org.educraft.number;

import net.minecraft.item.Item;


public class SubtractionOperator extends Item implements MathematicalOperator {
	
	public SubtractionOperator() {
		super(5001);
		setUnlocalizedName("subOperator");
	}

	@Override
	public OperatorType getOperator() {
		return OperatorType.MINUS;
	}

}
