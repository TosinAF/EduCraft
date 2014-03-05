package org.educraft.number.entity;

import java.util.Random;

import org.educraft.EduCraft;
import org.educraft.number.item.BaseNumber;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class NumberSkeleton extends EntitySkeleton {
	private static Random rnd = new Random();
	private int droppedItemId, value;

	public NumberSkeleton(World world) {
		super(world);
		setCustomNameTag("Number Skeleton");
		this.droppedItemId = Item.arrow.itemID;
		this.value = ((rnd.nextInt(10)) + 1) * 10;
	}
	
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
	protected void dropRareDrop(int par1)
    {
		//this.dropItem(Item.rottenFlesh.itemID, 1);
		//this.droppedItemId = this.droppedItemId; // This statement is useless on purpose. It stops the mob dropping rare items so we don't get junk
    }
	
	@Override
	protected void dropFewItems(boolean par1, int par2)
    {
		this.dropItem(EduCraft.NUMBER.itemID, 1);
		//Purposely wasted method
    }

}
