package org.educraft;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.MinecraftForge;

import org.educraft.dummy.DummyAttackHandler;
import org.educraft.dummy.DummyCoin;
import org.educraft.dummy.DummyCoinPile;
import org.educraft.dummy.DummySword;
import org.educraft.dummy.DummyZombie;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "DummyModID", name = "Dummy Mod", version = "0.4.0")
@NetworkMod(clientSideRequired = true)
public class DummyMod {

	/**
	 * Adds an instance of DummySword into the mod.
	 */
	public static final Item DUMMY_SWORD = new DummySword(5000);
	/**
	 * Adds an instance of DummyCoin into the mod.
	 */
	public static final Item DUMMY_COIN = new DummyCoin(5001);
	/**
	 * Adds an instance of DummyCoinPile into the mod.
	 */
	public static final Item DUMMY_COIN_PILE = new DummyCoinPile(5002);

	// The instance of your mod that Forge uses.
	@Instance(value = "DummyModID")
	public static DummyMod instance;

	// Says where the client and server 'proxy' code is loaded.
	@SidedProxy(clientSide = "org.educraft.client.ClientProxy", serverSide = "org.educraft.CommonProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		// Stub Method
	}

	@EventHandler
	public void load(FMLInitializationEvent event) {
		// register the zombie-killing event
		MinecraftForge.EVENT_BUS.register(new DummyAttackHandler());
		
		// register recipes for making and breaking DummyCoinPiles
		GameRegistry.addRecipe(new ItemStack(DUMMY_COIN_PILE), "ccc", "ccc",
				"ccc", 'c', new ItemStack(DUMMY_COIN));
		GameRegistry.addShapelessRecipe(new ItemStack(DUMMY_COIN, 9),
				new ItemStack(DUMMY_COIN_PILE));

		// register the DummyZombie as an entity that can spawn
		EntityRegistry.registerGlobalEntityID(DummyZombie.class,
				"Dummy Zombie", EntityRegistry.findGlobalUniqueEntityId(),
				32324, 2243);
		EntityRegistry.registerModEntity(DummyZombie.class, "Dummy Zombie",
				EntityRegistry.findGlobalUniqueEntityId(), this, 60, 3, true);
		EntityRegistry.addSpawn(DummyZombie.class, 10, 1, 2,
				EnumCreatureType.monster, BiomeGenBase.plains);

		// add localised names to language registry
		LanguageRegistry.addName(DUMMY_SWORD, "Dummy Sword");
		LanguageRegistry.addName(DUMMY_COIN, "Dummy Coin");
		LanguageRegistry.addName(DUMMY_COIN_PILE, "Pile of Dummy Coins");
		LanguageRegistry.instance().addStringLocalization(
				"entity.Dummy Zombie.name", "en_US", "Dummy Zombie");

		proxy.registerRenderers();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		// Stub Method
	}
}
