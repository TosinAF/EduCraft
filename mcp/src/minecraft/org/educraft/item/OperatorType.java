package org.educraft.item;

public enum OperatorType {
	TIMES("Multiplication", "x"), DIVIDE("Division", "/"), PLUS("Addition", "+"), MINUS(
			"Subtraction", "-");
			

	private String name, symbol;
	
	/**
	 * The Operator Type Enum Constructor
	 */

	private OperatorType(String name, String symbol) {
		this.name = name;
		this.symbol = symbol;
	}
	
	/**
	 * Returns the operator name
	 */

	public String getName() {
		return name;
	}
	
	/**
	 * Returns the operator symbol
	 */

	public String getSymbol() {
		return symbol;
	}

}
