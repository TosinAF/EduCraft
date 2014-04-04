package org.educraft.item;

import org.educraft.EduCraft;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemSword;

/**
 * A Maths Wand is used to obtain numbers by killing the skeletons and zombies
 * defined in {@link org.educraft.entity}.
 * 
 */
public class MathsWand extends ItemSword {

	/**
	 * Class constructor.
	 * 
	 * @param itemId
	 *            unique item ID to assign to this item
	 */
	public MathsWand(int itemId) {
		super(itemId, EnumToolMaterial.IRON);
		setCreativeTab(EduCraft.tabEduCraft);
		setTextureName("educraft:mathswand");
		setUnlocalizedName("mathsWand");
	}

}
