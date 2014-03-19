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

	/**
	 * Returns the integer code associated with this bench type. Used to set the
	 * metadata of the key the bench should return.
	 * 
	 * @return 0 for ODD, 1 for EVEN, 2 for ALL
	 */
	public int getTypeCode() {
		return this.type;
	}
}
