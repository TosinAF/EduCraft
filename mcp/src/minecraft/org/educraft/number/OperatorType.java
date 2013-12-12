package org.educraft.number;

public enum OperatorType {
	PLUS("Addition","+"),
	MINUS("Subtraction","-"),
	TIMES("Multiplication","x"),
	DIVIDE("Division","/");
	
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
