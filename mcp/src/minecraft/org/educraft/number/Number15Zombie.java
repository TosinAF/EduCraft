package org.educraft.number;

import org.educraft.DummyMod;
import org.educraft.EduCraft;

import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class Number15Zombie extends EntityZombie {
	
	private int droppedItemId;

	public Number15Zombie(World world) {
		super(world);
		setCustomNameTag("15");
		this.droppedItemId = Item.rottenFlesh.itemID;
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float damage) {
		if (source.getDamageType().equals("dummy")) {
			// dummy damage, set the dropped item to DummyCoin
			this.droppedItemId = new ItemStack (EduCraft.NUMBER, 10).getItem().itemID;
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
