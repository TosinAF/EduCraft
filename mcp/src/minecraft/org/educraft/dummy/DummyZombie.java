package org.educraft.dummy;

import java.util.Random;

import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import org.educraft.DummyMod;

/**
 * The DummyZombie exists as a source of DummyCoins. If he is killed by a dummy
 * weapon, he will drop a DummyCoin; otherwise, he will drop rotten flesh as
 * normal.
 * 
 * @author iak12u
 * 
 */
public class DummyZombie extends EntityZombie {
	private int droppedItemId;

	public DummyZombie(World world) {
		super(world);
		setCustomNameTag("Dummy Zombie");
		this.droppedItemId = Item.rottenFlesh.itemID;
	}

	/**
	 * Checks what sort of damage the zombie is taking, and sets the ID of the
	 * item to drop accordingly.
	 * 
	 * If hit by a dummy weapon, then the zombie should drop a DummyCoin if
	 * killed. If hit by a regular weapon, the zombie should drop rotten flesh.
	 * If it takes damage from any other source, the item dropped should be
	 * unchanged.
	 */
	@Override
	public boolean attackEntityFrom(DamageSource source, float damage) {
		if (source.getDamageType().equals("dummy")) {
			// dummy damage, set the dropped item to DummyCoin
			this.droppedItemId = DummyMod.DUMMY_COIN.itemID;
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

	/**
	 * Returns the ID of the item to drop when killed.
	 * 
	 * @return the item ID, set according to the most recent damage source
	 */
	@Override
	protected int getDropItemId() {
		return this.droppedItemId;
	}

}
