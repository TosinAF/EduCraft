package org.educraft.item;

import net.minecraft.item.Item;

import org.educraft.EduCraft;

/**
 * A mathematical operator which a player can craft and then use in
 * calculations.
 */
public class MathematicalOperator extends Item {
	private OperatorType type;

	/**
	 * Class constructor.
	 * 
	 * @param itemId
	 *            unique ID to assign to this item
	 * 
	 * @param type
	 *            the type of operator this item represents
	 * 
	 */
	public MathematicalOperator(int itemId, OperatorType type) {
		super(itemId);
		this.type = type;

		setUnlocalizedName("operator." + type.getName());
		setMaxStackSize(4);
		setCreativeTab(EduCraft.tabEduCraft);
		setTextureName("educraft:" + type.getName());
	}

	/**
	 * Returns the type of operator this item represents.
	 * 
	 * @return this item's operator type
	 */
	public OperatorType getType() {
		return this.type;
	}

}
