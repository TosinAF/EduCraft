package org.educraft.number.entity;
import org.educraft.EduCraft;
import java.util.Random;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
public class NumberMobDropsEvent {
	private static Random rnd = new Random();
	@ForgeSubscribe

	public void onEntityDrop(LivingDropsEvent event){
		if(event.source.getDamageType().equals("dummy")){
			if (event.entityLiving instanceof NumberZombie) {
				int numberDropped = (rnd.nextInt(10)) + 1;
				ItemStack droppedItem = new ItemStack(EduCraft.NUMBER.itemID,1,1);
				droppedItem.setItemDamage(numberDropped);
				event.entityLiving.entityDropItem(droppedItem, 1.0f);
			}
			else if (event.entityLiving instanceof NumberSkeleton) {
				int numberDropped = ((rnd.nextInt(10)) + 1) * 10;
				ItemStack droppedItem = new ItemStack(EduCraft.NUMBER.itemID,1,1);
				droppedItem.setItemDamage(numberDropped);
				event.entityLiving.entityDropItem(droppedItem, 1.0f);
			}
		}
	}
}
