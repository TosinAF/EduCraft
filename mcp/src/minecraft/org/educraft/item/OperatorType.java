package org.educraft.item;

public enum OperatorType {
	TIMES("Multiplication", "x"), DIVIDE("Division", "/"), PLUS("Addition", "+"), MINUS(
			"Subtraction", "-");

	private String name, symbol;

	private OperatorType(String name, String symbol) {
		this.name = name;
		this.symbol = symbol;
	}

	public String getName() {
		return name;
	}

	public String getSymbol() {
		return symbol;
	}

}
