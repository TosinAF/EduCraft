package org.educraft.dummy;

import java.util.Random;

import org.educraft.DummyMod;

import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.item.Item;
import net.minecraft.world.World;

/**
 * The DummyZombie exists to drop DummyCoins when he dies, with a 0.33
 * probability that he will drop a coin instead of his normal drops.
 * 
 * @author iak12u
 * 
 */
public class DummyZombie extends EntityZombie {
	private Random itemRandomiser;

	public DummyZombie(World world) {
		super(world);
		this.itemRandomiser = new Random();
	}

	/**
	 * Sets the ID of the item this mob should drop when killed. 33% of the
	 * time, it will drop a DummyCoin; the rest of the time, it will drop rotten
	 * flesh as per usual.
	 * 
	 * @return the ID of the item to be dropped
	 */
	@Override
	protected int getDropItemId() {
		return (this.itemRandomiser.nextInt(3) == 0) ? DummyMod.DUMMY_COIN.itemID
				: Item.rottenFlesh.itemID;
	}

}
