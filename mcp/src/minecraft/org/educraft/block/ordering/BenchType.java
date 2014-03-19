package org.educraft.block.ordering;

/**
 * Enum type describing the different kinds of ordering bench.
 */
public enum BenchType {
	/**
	 * A bench which accepts only odd numbers.
	 */
	ODD(0),
	/**
	 * A bench which accepts only even numbers.
	 */
	EVEN(1),
	/**
	 * A bench which accepts all numbers.
	 */
	ALL(2);
	
	private int type;
	
	BenchType(int type) {
		this.type = type;
	}
	
	public int getTypeCode() {
		return this.type;
	}
}
