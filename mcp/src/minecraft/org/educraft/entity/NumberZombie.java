package org.educraft.entity;

import java.util.Random;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import org.educraft.EduCraft;
import org.educraft.item.BaseNumber;

/**
 * Customised zombie that drops a number between 1 and 10 upon death, in place
 * of its normal drops.
 */
public class NumberZombie extends EntityZombie implements INumberMob {
	private static Random rnd = new Random();
	private int droppedItemId, value;

	/**
	 * Class constructor. The value of the zombie is randomly generated here,
	 * and displayed as part of its name.
	 * 
	 * @param world
	 *            the world the zombie is being placed in
	 */
	public NumberZombie(World world) {
		super(world);
		this.droppedItemId = Item.rottenFlesh.itemID;
		this.value = (rnd.nextInt(10)) + 1;
		setCustomNameTag(String.valueOf("Number " + this.value));
	}

	@Override
	public int getValue() {
		return this.value;
	}

	/**
	 * Called whenever this zombie is attacked. It ensures that the skeleton
	 * only drops a number after being killed by a Maths Wand.
	 * 
	 * @param source
	 *            the source of the damage inflicted
	 * @param damage
	 *            the amount of damage dealt
	 * @return true if this zombie can be hurt by the given damage type
	 */
	@Override
	public boolean attackEntityFrom(DamageSource source, float damage) {
		if (source.getDamageType().equals("dummy")) {
			// dummy damage, set the dropped item to DummyCoin
			this.droppedItemId = EduCraft.NUMBER.itemID;
			return super.attackEntityFrom(source, damage);
		} else if (source.getDamageType().equals("player")) {
			// other damage caused by player, set dropped item to rotten flesh
			this.droppedItemId = Item.rottenFlesh.itemID;
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
		// This statement is useless on purpose. It stops the mob dropping rare
		// items so we don't get junk.
	}

	@Override
	protected void dropFewItems(boolean par1, int par2) {
		this.dropItem(EduCraft.NUMBER.itemID, 1);
		// Purposely wasted method
	}

}
