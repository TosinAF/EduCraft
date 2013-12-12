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
		LanguageRegistry.addName(ADD_OPR, "Addition sign");
		LanguageRegistry.addName(SUB_OPR, "Subtraction sign");
		LanguageRegistry.addName(MUL_OPR, "Multiplication sign");
		LanguageRegistry.addName(DIV_OPR, "Division sign");
		// crafting recipes for mathematical operators
		GameRegistry.addRecipe(new ItemStack(ADD_OPR), " s ", "sss", " s ",
				'c', new ItemStack(Item.stick));
		GameRegistry.addRecipe(new ItemStack(SUB_OPR), "   ", "sss", "   ",
				'c', new ItemStack(Item.stick));
		GameRegistry.addRecipe(new ItemStack(MUL_OPR), "s s", " s ", "s s",
				'c', new ItemStack(Item.stick));
		GameRegistry.addRecipe(new ItemStack(DIV_OPR), "  s", " s ", "s  ",
				'c', new ItemStack(Item.stick));
		// recipes to break operators down into sticks
		GameRegistry.addShapedRecipe(new ItemStack(Item.stick, 4), new ItemStack(ADD_OPR));
		GameRegistry.addShapedRecipe(new ItemStack(Item.stick, 2), new ItemStack(SUB_OPR));
		GameRegistry.addShapedRecipe(new ItemStack(Item.stick, 4), new ItemStack(MUL_OPR));
		GameRegistry.addShapedRecipe(new ItemStack(Item.stick, 2), new ItemStack(DIV_OPR));

		proxy.registerRenderers();
	}

}
