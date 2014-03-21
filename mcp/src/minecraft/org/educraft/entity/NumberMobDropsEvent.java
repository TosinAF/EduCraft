package org.educraft.entity;

import java.util.Random;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDropsEvent;

import org.educraft.EduCraft;

/**
 * Event handler class providing a single method to control the dropping of
 * items by entities.
 */

public class NumberMobDropsEvent {
	private static Random rnd = new Random();

	/**
	 * Called whenever an entity drops an item. This handler is used to ensure
	 * that when a number mob is killed, it drops a number of the correct value.
	 * 
	 * @param event
	 *            the event which triggered this handler
	 */
	@ForgeSubscribe
	public void onEntityDrop(LivingDropsEvent event) {
		if (event.source.getDamageType().equals("dummy")) {
			EntityLivingBase entity = event.entityLiving;
			if (entity instanceof INumberMob) {
				ItemStack droppedItem = new ItemStack(EduCraft.NUMBER, 1,
						((INumberMob) entity).getValue());
				entity.entityDropItem(droppedItem, 1.0f);
			}
		}
	}
}
