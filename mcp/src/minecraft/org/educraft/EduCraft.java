package org.educraft;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.MinecraftForge;

import org.educraft.dummy.DummyAttackHandler;
import org.educraft.dummy.DummyCoin;
import org.educraft.dummy.DummyCoinPile;
import org.educraft.dummy.MathsWand;
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

@Mod(modid = "EduCraft", name = "EduCraft", version = "0.1.0")
@NetworkMod(clientSideRequired = true)
public class EduCraft {

	// The instance of your mod that Forge uses.
	@Instance(value = "EduCraft")
	public static EduCraft instance;

	// Says where the client and server 'proxy' code is loaded.
	@SidedProxy(clientSide = "org.educraft.client.ClientProxy", serverSide = "org.educraft.CommonProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void load(FMLInitializationEvent event) {
		// TODO implement with EduCraft files
	
		proxy.registerRenderers();
	}

}
