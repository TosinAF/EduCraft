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
import org.educraft.number.AdditionOperator;
import org.educraft.number.DivisionOperator;
import org.educraft.number.MultiplicationOperator;
import org.educraft.number.Number;
import org.educraft.number.Number15;
import org.educraft.number.Number2;
import org.educraft.number.Number2Zombie;
import org.educraft.number.Number30;
import org.educraft.number.SubtractionOperator;

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

	// instances of the mathematical operators
	public static final Item ADD_OPR = new AdditionOperator();
	public static final Item SUB_OPR = new SubtractionOperator();
	public static final Item MUL_OPR = new MultiplicationOperator();
	public static final Item DIV_OPR = new DivisionOperator();
	public static final Item NUMBER15 = new Number15(6005);
	public static final Item NUMBER30 = new Number30(6006);
	public static final Item NUMBER2 = new Number2(6007);
	
	// The instance of your mod that Forge uses.
	@Instance(value = "EduCraft")
	public static EduCraft instance;

	// Says where the client and server 'proxy' code is loaded.
	@SidedProxy(clientSide = "org.educraft.client.ClientProxy", serverSide = "org.educraft.CommonProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void load(FMLInitializationEvent event) {
		// TODO implement with EduCraft files
		// localised names for mathematical operators
		LanguageRegistry.addName(NUMBER15, "15");
		LanguageRegistry.addName(NUMBER30, "30");
		LanguageRegistry.addName(NUMBER2, "2");
		LanguageRegistry.addName(ADD_OPR, "Addition sign");
		LanguageRegistry.addName(SUB_OPR, "Subtraction sign");
		LanguageRegistry.addName(MUL_OPR, "Multiplication sign");
		LanguageRegistry.addName(DIV_OPR, "Division sign");
		
		// crafting recipes for mathematical operators
		ItemStack sticks = new ItemStack(Item.stick);
		GameRegistry.addRecipe(new ItemStack(NUMBER30), "xyz", 'x', NUMBER15,'y', MUL_OPR,'z', NUMBER2);
		GameRegistry.addRecipe(new ItemStack(NUMBER30), "xyz", 'x', NUMBER2,'y', MUL_OPR,'z', NUMBER15);
		GameRegistry.addRecipe(new ItemStack(NUMBER2), "xyz", 'x', NUMBER30,'y', DIV_OPR,'z', NUMBER15);
		GameRegistry.addRecipe(new ItemStack(NUMBER15), "xyz", 'x', NUMBER30,'y', DIV_OPR,'z', NUMBER2);
		GameRegistry.addRecipe(new ItemStack(COAL), "xyz", 'x', NUMBER15,'y', DIV_OPR,'z', NUMBER30); //Put coal item here instead of COAL
		GameRegistry.addRecipe(new ItemStack(COAL), "xyz", 'x', NUMBER2,'y', DIV_OPR,'z', NUMBER30); //Put coal item here instead of COAL
		
		GameRegistry.addRecipe(new ItemStack(ADD_OPR), " s ", "sss", " s ",
				's', sticks);
		GameRegistry.addRecipe(new ItemStack(SUB_OPR), "   ", "sss", "   ",
				's', sticks);
		GameRegistry.addRecipe(new ItemStack(MUL_OPR), "s s", " s ", "s s",
				's', sticks);
		GameRegistry.addRecipe(new ItemStack(DIV_OPR), "  s", " s ", "s  ",
				's', sticks);
		// recipes to break operators down into sticks
		GameRegistry.addShapelessRecipe(new ItemStack(Item.stick, 4),
				new ItemStack(ADD_OPR));
		GameRegistry.addShapelessRecipe(new ItemStack(Item.stick, 2),
				new ItemStack(SUB_OPR));
		GameRegistry.addShapelessRecipe(new ItemStack(Item.stick, 4),
				new ItemStack(MUL_OPR));
		GameRegistry.addShapelessRecipe(new ItemStack(Item.stick, 2),
				new ItemStack(DIV_OPR));
		
		//last two numbers are colours of spawn egg.
		EntityRegistry.registerGlobalEntityID(Number2Zombie.class,
				"Number 2 Zombie", EntityRegistry.findGlobalUniqueEntityId(),
				32324, 2243);
		
		EntityRegistry.registerModEntity(Number2Zombie.class, "Number 2 Zombie",
				EntityRegistry.findGlobalUniqueEntityId(), this, 60, 3, true);
		EntityRegistry.addSpawn(Number2Zombie.class, 10, 1, 2,
				EnumCreatureType.monster, BiomeGenBase.plains);
		
		EntityRegistry.registerGlobalEntityID(Number15Zombie.class,
				"Number 15 Zombie", EntityRegistry.findGlobalUniqueEntityId(),
				32324, 2243);
		
		EntityRegistry.registerModEntity(Number15Zombie.class, "Number 15 Zombie",
				EntityRegistry.findGlobalUniqueEntityId(), this, 60, 3, true);
		EntityRegistry.addSpawn(Number15Zombie.class, 10, 1, 2,
				EnumCreatureType.monster, BiomeGenBase.plains);

		proxy.registerRenderers();
	}

}
