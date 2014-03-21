package org.educraft.entity;

/**
 * Interface defining anything that can drop a number.
 */
public interface INumberMob {

	/**
	 * Returns the number that this mob should drop when killed.
	 * 
	 * @return the mob's number
	 */
	public int getValue();

}
