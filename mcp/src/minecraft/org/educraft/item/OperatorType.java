package org.educraft.item;

/**
 * Describes the four types of arithmetic operator. Used to provide unlocalised
 * names to {@link MathematicalOperator}, and also used when performing
 * calculations.
 *
 */
public enum OperatorType {
	/**
	 * The addition operator.
	 */
	PLUS("addition"),
	/**
	 * The subtraction operator.
	 */
	MINUS("subtraction"),
	/**
	 * The multiplication operator.
	 */
	TIMES("multiplication"),
	/**
	 * The division operator.
	 */
	DIVIDE("division");

	private String name;

	/**
	 * Enum constructor.
	 * 
	 * @param name
	 *            the unlocalised name this operator type provides
	 */
	private OperatorType(String name) {
		this.name = name;
	}

	/**
	 * Returns the unlocalised name of this operator.
	 * 
	 * @return the unlocalised name
	 */
	public String getName() {
		return name;
	}

}
