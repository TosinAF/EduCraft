package org.educraft.number;

import net.minecraft.item.Item;

public class AdditionOperator extends Item implements MathematicalOperator {
	
	public AdditionOperator() {
		super(5000);
	}

	@Override
	public OperatorType getOperator() {
		// TODO Auto-generated method stub
		return OperatorType.PLUS;
	}

}
