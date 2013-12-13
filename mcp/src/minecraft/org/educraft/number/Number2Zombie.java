package org.educraft.number;

import org.educraft.DummyMod;
import org.educraft.EduCraft;

import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class Number2Zombie extends EntityZombie {
	
	private int droppedItemId;

	public Number2Zombie(World world) {
		super(world);
		setCustomNameTag("2");
		this.droppedItemId = Item.rottenFlesh.itemID;
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float damage) {
		if (source.getDamageType().equals("dummy")) {
			// dummy damage, set the dropped item to DummyCoin
			this.droppedItemId = EduCraft.NUMBER2.itemID;
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
}
