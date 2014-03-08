package org.educraft;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.MinecraftForge;

import org.educraft.dummy.DummyAttackHandler;
import org.educraft.dummy.MathsWand;
import org.educraft.number.Number15Zombie;
import org.educraft.number.Number2;
import org.educraft.number.Number2Zombie;
import org.educraft.number.Number30;
import org.educraft.number.block.EduCraftGuiHandler;
import org.educraft.number.block.calculator.BlockCalculator;
import org.educraft.number.block.operators.BlockOperatorBench;
import org.educraft.number.block.ordering.BlockOrderingBench;
import org.educraft.number.entity.NumberMobDropsEvent;
import org.educraft.number.entity.NumberSkeleton;
import org.educraft.number.entity.NumberZombie;
import org.educraft.number.item.AdditionOperator;
import org.educraft.number.item.BaseNumber;
import org.educraft.number.item.DivisionOperator;
import org.educraft.number.item.MultiplicationOperator;
import org.educraft.number.item.SubtractionOperator;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "EduCraft", name = "EduCraft", version = "0.2.0")
@NetworkMod(clientSideRequired = true)
public class EduCraft {

	
	public static final String GuiTexturePrefix = "educraft" + ":" + "textures/gui/";
	
	/* ONLY NEEDED FOR CHRISTMAS DEMONSTRATION */
	public static final Item NUMBER30 = new Number30(6006);
	public static final Item NUMBER2 = new Number2(6007);
	/* ONLY NEEDED FOR CHRISTMAS DEMONSTRATION ENDS */

	// EduCraft creative tab
	public static CreativeTabs tabEduCraft = new CreativeTabs("tabEduCraft") {
		public ItemStack getIconItemStack() {
			return new ItemStack(ADD_OPR, 1, 0);
		}
	};

	// max number that we will work with
	public static final int MAX_NUMBER = 1000;

	// instance of the maths wand
	public static final Item MATHS_WAND = new MathsWand(6000);
	// instances of the mathematical operators
	public static final Item ADD_OPR = new AdditionOperator();
	public static final Item SUB_OPR = new SubtractionOperator();
	public static final Item MUL_OPR = new MultiplicationOperator();
	public static final Item DIV_OPR = new DivisionOperator();
	public static final Item NUMBER = new BaseNumber(6005);

	// instance of the calculator
	public static final Block CALCULATOR = new BlockCalculator(500);
	// instance of the operator bench
	public static final Block OPERATOR_BENCH = new BlockOperatorBench(501);
	// instance of ordering bench
	public static final Block ORDERING_BENCH = new BlockOrderingBench(502);


	// The instance of your mod that Forge uses.
	@Instance(value = "EduCraft")
	public static EduCraft instance;

	// GUIHandler for EduCraft blocks
	private EduCraftGuiHandler eduCraftGuiHandler = new EduCraftGuiHandler();

	// Says where the client and server 'proxy' code is loaded.
	@SidedProxy(clientSide = "org.educraft.client.ClientProxy", serverSide = "org.educraft.CommonProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void load(FMLInitializationEvent event) {
		// localised name for the EduCraft tab
		LanguageRegistry.instance().addStringLocalization(
				"itemgroup.tabEduCraft", "en_us", "EduCraft");

		// register the calculator table
		GameRegistry.registerBlock(CALCULATOR, "calculatorTable");
		MinecraftForge.setBlockHarvestLevel(CALCULATOR, "axe", 0);
		LanguageRegistry.addName(CALCULATOR, "Calculator Table");

		// register the operator bench
		GameRegistry.registerBlock(OPERATOR_BENCH, "operatorBench");
		MinecraftForge.setBlockHarvestLevel(OPERATOR_BENCH, "axe", 0);
		LanguageRegistry.addName(OPERATOR_BENCH, "Operator Bench");
		
		// register the ordering bench
		GameRegistry.registerBlock(ORDERING_BENCH, "orderingBench");
		MinecraftForge.setBlockHarvestLevel(ORDERING_BENCH, "axe", 0);
		LanguageRegistry.addName(ORDERING_BENCH, "Ordering Bench");

		// GameRegistry.registerItem(NUMBER, BaseNumber.class);

		/* MATHS WAND */
		// localised name for maths wand
		LanguageRegistry.addName(MATHS_WAND, "Maths Wand");

		/* NUMBERS */
		// register names for each possible metadata value in turn
		ItemStack numStack;
		for (int i = 1; i <= MAX_NUMBER; i++) {
			numStack = new ItemStack(NUMBER, 1, i);
			LanguageRegistry.addName(numStack,
					String.format("Number %d", numStack.getItemDamage()));
		}

		/* ONLY NEEDED FOR CHRISTMAS DEMONSTRATION */
		LanguageRegistry.addName(NUMBER30, "30");
		LanguageRegistry.addName(NUMBER2, "2");
		/* ONLY NEEDED FOR CHRISTMAS DEMONSTRATION ENDS */

		/* MATHEMATICAL OPERATORS */
		LanguageRegistry.addName(ADD_OPR, "Addition sign");
		LanguageRegistry.addName(SUB_OPR, "Subtraction sign");
		LanguageRegistry.addName(MUL_OPR, "Multiplication sign");
		LanguageRegistry.addName(DIV_OPR, "Division sign");

		/* CUSTOM CRAFTING TABLE */
		LanguageRegistry.addName(CALCULATOR, "Calculator");
		
		// recipes to break operators down into sticks
		ItemStack sticks = new ItemStack(Item.stick);
		GameRegistry.addShapelessRecipe(new ItemStack(Item.stick, 4),
				new ItemStack(ADD_OPR));
		GameRegistry.addShapelessRecipe(new ItemStack(Item.stick, 2),
				new ItemStack(SUB_OPR));
		GameRegistry.addShapelessRecipe(new ItemStack(Item.stick, 4),
				new ItemStack(MUL_OPR));
		GameRegistry.addShapelessRecipe(new ItemStack(Item.stick, 2),
				new ItemStack(DIV_OPR));

		/* NUMBER ZOMBIES */
		// register the generic number zombie
		EntityRegistry.registerGlobalEntityID(NumberZombie.class,
				"Number Zombie", EntityRegistry.findGlobalUniqueEntityId(),
				20000, 2500);
		EntityRegistry.registerModEntity(NumberZombie.class, "Number Zombie",
				EntityRegistry.findGlobalUniqueEntityId(), this, 60, 3, true);
		EntityRegistry.addSpawn(NumberZombie.class, 10, 1, 2,
				EnumCreatureType.monster, BiomeGenBase.plains);

		/* NUMBER SKELETONS */
		// register the generic number skeleton
		EntityRegistry.registerGlobalEntityID(NumberSkeleton.class,
				"Number Skeleton", EntityRegistry.findGlobalUniqueEntityId(),
				32324, 2243);
		EntityRegistry.registerModEntity(NumberZombie.class, "Number Skeleton",
				EntityRegistry.findGlobalUniqueEntityId(), this, 60, 3, true);
		EntityRegistry.addSpawn(NumberSkeleton.class, 10, 1, 2,
				EnumCreatureType.monster, BiomeGenBase.plains);

		/* ONLY NEEDED FOR CHRISTMAS DEMONSTRATION */
		// register the number 2 zombie

		EntityRegistry.registerGlobalEntityID(Number2Zombie.class,
				"Number 2 Zombie", EntityRegistry.findGlobalUniqueEntityId(),
				32324, 2243);
		EntityRegistry.registerModEntity(Number2Zombie.class,
				"Number 2 Zombie", EntityRegistry.findGlobalUniqueEntityId(),
				this, 60, 3, true);
		EntityRegistry.addSpawn(Number2Zombie.class, 10, 1, 2,
				EnumCreatureType.monster, BiomeGenBase.plains);
		// register the number 15 zombie
		EntityRegistry.registerGlobalEntityID(Number15Zombie.class,
				"Number 15 Zombie", EntityRegistry.findGlobalUniqueEntityId(),
				32324, 2243);
		EntityRegistry.registerModEntity(Number15Zombie.class,
				"Number 15 Zombie", EntityRegistry.findGlobalUniqueEntityId(),
				this, 60, 3, true);
		EntityRegistry.addSpawn(Number15Zombie.class, 10, 1, 2,
				EnumCreatureType.monster, BiomeGenBase.plains);
		/* ONLY NEEDED FOR CHRISTMAS DEMONSTRATION ENDS */

		// register the attack handler
		MinecraftForge.EVENT_BUS.register(new DummyAttackHandler());
		MinecraftForge.EVENT_BUS.register(new NumberMobDropsEvent());

		// Important keep it
		NetworkRegistry.instance().registerGuiHandler(this, eduCraftGuiHandler);

		proxy.registerRenderers();
	}
}
