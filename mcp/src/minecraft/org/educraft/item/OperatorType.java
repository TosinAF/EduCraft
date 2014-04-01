package org.educraft.item;

public enum OperatorType {
	TIMES("multiplication"), DIVIDE("division"), PLUS("addition"), MINUS(
			"subtraction");
			

	private String name;
	
	/**
	 * The Operator Type Enum Constructor
	 */

	private OperatorType(String name) {
		this.name = name;
	}
	
	/**
	 * Returns the operator name
	 */

	public String getName() {
		return name;
	}

}
