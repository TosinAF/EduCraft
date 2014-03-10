package org.educraft.entity;

import java.util.Random;

import org.educraft.EduCraft;
import org.educraft.item.BaseNumber;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

/**
 * Customised skeleton that drops a multiple of ten between 10 and 100 upon
 * death, instead of its normal drops.
 */
public class NumberSkeleton extends EntitySkeleton implements INumberMob {
	private static Random rnd = new Random();
	private int droppedItemId, value;

	/**
	 * Class constructor. The value of the skeleton is set here, and is then
	 * displayed as part of its name.
	 * 
	 * @param world
	 *            the world the skeleton is being placed in
	 */
	public NumberSkeleton(World world) {
		super(world);
		setCustomNameTag("Number Skeleton");
		this.droppedItemId = Item.arrow.itemID;
		this.value = ((rnd.nextInt(10)) + 1) * 10;
		setCustomNameTag(String.valueOf("Number " + this.value));
	}

	@Override
	public int getValue() {
		return this.value;
	}

	/**
	 * Called whenever this skeleton is attacked. It ensures that the skeleton
	 * only drops a number after being killed by a Maths Wand.
	 * 
	 * @param source
	 *            the source of the damage inflicted
	 * @param damage
	 *            the amount of damage dealt
	 * @return true if this skeleton can be hurt by the given damage type
	 */
	@Override
	public boolean attackEntityFrom(DamageSource source, float damage) {
		if (source.getDamageType().equals("dummy")) {
			// dummy damage, set the dropped item to DummyCoin
			this.droppedItemId = EduCraft.NUMBER.itemID;
			return super.attackEntityFrom(source, damage);
		} else if (source.getDamageType().equals("player")) {
			// other damage caused by player, set dropped item to rotten flesh
			this.droppedItemId = Item.arrow.itemID;
			return super.attackEntityFrom(source, damage);
		} else {
			// miscellaneous damage, don't change the dropped item
			return super.attackEntityFrom(source, damage);
		}
	}

	@Override
	protected int getDropItemId() {
		return this.droppedItemId;
	}

	@Override
	public EntityItem entityDropItem(ItemStack stack, float par2) {
		if (stack.getItem() instanceof BaseNumber) {
			stack.setItemDamage(value);
		}
		return super.entityDropItem(stack, par2);
	}

	@Override
	protected void dropRareDrop(int par1) {
		// Do not fill this method with a drop, if you do the mob will dropped
		// two numbers ocassionally.
	}

	@Override
	protected void dropFewItems(boolean par1, int par2) {
		this.dropItem(EduCraft.NUMBER.itemID, 1);
		// Skeletons only drop multiple items, so they will always drop items
		// through this method.
	}

}
