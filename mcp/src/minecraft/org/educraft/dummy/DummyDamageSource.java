package org.educraft.dummy;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;

public class DummyDamageSource extends DamageSource {

	protected DummyDamageSource() {
		super("dummy");
		setDamageBypassesArmor();

		// used to allow testing of damage in creative mode, without
		// mobs attacking back
		setDamageAllowedInCreativeMode();
	}

	/**
	 * Gets an instance of DummyDamageSource, for hurting mobs with.
	 * 
	 * @param source
	 *            the entity causing the damage
	 * @return the DamagSource instance
	 */
	public static DamageSource causeDummyDamage(EntityLivingBase source) {
		return new EntityDamageSource("dummy", source);
	}

}
