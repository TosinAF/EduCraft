package org.educraft.number;

import java.util.Random;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import org.educraft.EduCraft;

public class NumberZombie extends EntityZombie {
	private static Random rnd = new Random();
	private int droppedItemId, value;

	public NumberZombie(World world) {
		super(world);
		setCustomNameTag("Number Zombie");
		this.droppedItemId = Item.rottenFlesh.itemID;
		this.value = (rnd.nextInt(10)) + 1;
	}
	
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
		if (stack.getItem() instanceof Numbers) {
			stack.setItemDamage(value);
		}
		return super.entityDropItem(stack, par2);
	}

}
