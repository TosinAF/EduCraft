package org.educraft.block;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import org.educraft.EduCraft;
import org.educraft.block.calculator.BlockCalculator;
import org.educraft.block.calculator.CalculatorContainer;
import org.educraft.block.calculator.CalculatorGui;
import org.educraft.block.operators.BlockOperatorBench;
import org.educraft.block.operators.OperatorContainer;
import org.educraft.block.operators.OperatorGui;
import org.educraft.block.ordering.BlockOrderingBench;
import org.educraft.block.ordering.OrderingContainer;
import org.educraft.block.ordering.OrderingGui;

import cpw.mods.fml.common.network.IGuiHandler;

/**
 * This class handles the display of all custom-made GUIs.
 */
public class EduCraftGuiHandler implements IGuiHandler {

	/**
	 * Generates a container, which forms the server side of the GUI.
	 * 
	 * @param id
	 *            number to indicate which GUI we want to open
	 * @param player
	 *            the player who is opening the GUI
	 * @param world
	 *            the world the player is in
	 * @param x
	 *            the x-position of the player
	 * @param y
	 *            the y-position of the player
	 * @param z
	 *            the z-position of the player
	 */
	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world,
			int x, int y, int z) {
		
		switch (id) {
		case BlockCalculator.GUI_ID:
			TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
			return (tileEntity instanceof CraftingTileEntity) ? new CalculatorContainer(
					player.inventory, (CraftingTileEntity) tileEntity, world)
					: null;
		case BlockOperatorBench.GUI_ID:
			return world.getBlockId(x, y, z) == EduCraft.OPERATOR_BENCH.blockID ? new OperatorContainer(
					player.inventory, world, x, y, z) : null;
		case BlockOrderingBench.GUI_ID:
			tileEntity = world.getBlockTileEntity(x, y, z);
			return (tileEntity instanceof CraftingTileEntity) ? new OrderingContainer(
					player.inventory, (CraftingTileEntity) tileEntity, world)
					: null;
		}
		
		return null;
	}

	/**
	 * Generates the GUI itself, which is displayed to the client.
	 * 
	 * @param id
	 *            number to indicate which GUI we want to open
	 * @param player
	 *            the player who is opening the GUI
	 * @param world
	 *            the world the player is in
	 * @param x
	 *            the x-position of the player
	 * @param y
	 *            the y-position of the player
	 * @param z
	 *            the z-position of the player
	 */
	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world,
			int x, int y, int z) {

		switch (id) {
		case BlockCalculator.GUI_ID:
			TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
			return (tileEntity instanceof CraftingTileEntity) ? new CalculatorGui(
					player.inventory, world, (CraftingTileEntity) tileEntity)
					: null;
		case BlockOperatorBench.GUI_ID:
			return world.getBlockId(x, y, z) == EduCraft.OPERATOR_BENCH.blockID ? new OperatorGui(
					player.inventory, world, x, y, z) : null;
		case BlockOrderingBench.GUI_ID:
			tileEntity = world.getBlockTileEntity(x, y, z);
			return (tileEntity instanceof CraftingTileEntity) ? new OrderingGui(
					player.inventory, world, (CraftingTileEntity) tileEntity)
					: null;
		}
		
		return null;
	}
}
