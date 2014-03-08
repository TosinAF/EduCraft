package org.educraft.number.block;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import org.educraft.EduCraft;
import org.educraft.number.block.calculator.CalculatorContainer;
import org.educraft.number.block.calculator.CalculatorGui;
import org.educraft.number.block.operators.OperatorContainer;
import org.educraft.number.block.operators.OperatorGui;
import org.educraft.number.block.ordering.OrderingGui;
import org.educraft.number.block.ordering.OrderingContainer;

import cpw.mods.fml.common.network.IGuiHandler;

public class EduCraftGuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world,
			int x, int y, int z) {
		TileEntity tile_entity = world.getBlockTileEntity(x, y, z);
		
		int blockId = world.getBlockId(x, y, z);
		
		switch(blockId) {
		case 500:
			return new CalculatorContainer(player.inventory, world, x, y, z);
		case 501:
			return new OperatorContainer(player.inventory, world, x, y, z);
		case 502:
			return new OrderingContainer(player.inventory, world, x, y, z);
		}
		
//		switch (id) {
//		case 0:
//			return world.getBlockId(x, y, z) == EduCraft.CALCULATOR.blockID ? new CalculatorContainer(
//					player.inventory, world, x, y, z) : null;
//		case 1:
//			return world.getBlockId(x, y, z) == EduCraft.OPERATOR_BENCH.blockID ? new OperatorContainer(
//					player.inventory, world, x, y, z) : null;
//		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world,
			int x, int y, int z) {
		TileEntity tile_entity = world.getBlockTileEntity(x, y, z);
		
		int blockId = world.getBlockId(x, y, z);
		
		switch(blockId) {
		case 500:
			return new CalculatorGui(player.inventory, world, x, y, z);
		case 501:
			return new OperatorGui(player.inventory, world, x, y, z);
		case 502:
			return new OrderingGui(player.inventory, world, x, y, z);
		}
		
//		switch (id) {
//		case 0:
//			return world.getBlockId(x, y, z) == EduCraft.CALCULATOR.blockID ? new CalculatorGui(
//					player.inventory, world, x, y, z) : null;
//		case 1:
//			return world.getBlockId(x, y, z) == EduCraft.OPERATOR_BENCH.blockID ? new OperatorGui(
//					player.inventory, world, x, y, z) : null;
//		}
		return null;
	}
}
