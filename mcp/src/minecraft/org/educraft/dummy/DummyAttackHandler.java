package org.educraft.dummy;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.AttackEntityEvent;

import org.educraft.EduCraft;
import org.educraft.number.Number15Zombie;
import org.educraft.number.Number2Zombie;
import org.educraft.number.entity.NumberZombie;

/**
 * This class is used to handle AttackEntityEvents, which arise when a player
 * attacks another entity.
 * 
 * @author iak12u
 * 
 */
public class DummyAttackHandler {

	/**
	 * Handles events where a player is attacking an entity. Ensures that if a
	 * player attacks a DummyZombie with a DummySword, the zombie is dealt dummy
	 * damage to get the right item to drop.
	 * 
	 * @param event
	 *            the event to handle
	 */
	@ForgeSubscribe
	public void onPlayerAttackEntity(AttackEntityEvent event) {
		EntityPlayer player = event.entityPlayer;
		ItemStack weapon = player.inventory.mainInventory[player.inventory.currentItem];
		Entity target = event.target;

		// handle the attack specially if the target of the attack is a
		// DummyZombie
		if (target instanceof NumberZombie || target instanceof DummyZombie || target instanceof Number2Zombie || target instanceof Number15Zombie) {
			// deal dummy damage if the player is wielding a DummySword
			if (weapon.itemID == EduCraft.MATHS_WAND.itemID) {
				target.attackEntityFrom(
						DummyDamageSource.causeDummyDamage(player), 5F);
			}
		}
	}
}
