package org.educraft.number.block;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import org.educraft.EduCraft;
import org.educraft.number.block.calculator.BlockCalculator;
import org.educraft.number.block.calculator.CalculatorContainer;
import org.educraft.number.block.calculator.CalculatorGui;
import org.educraft.number.block.calculator.CalculatorTileEntity;
import org.educraft.number.block.operators.BlockOperatorBench;
import org.educraft.number.block.operators.OperatorContainer;
import org.educraft.number.block.operators.OperatorGui;

import cpw.mods.fml.common.network.IGuiHandler;

public class EduCraftGuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world,
			int x, int y, int z) {
		switch (id) {
		case BlockCalculator.GUI_ID:
			TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
			return (tileEntity instanceof CalculatorTileEntity) ? new CalculatorContainer(
					player.inventory, (CalculatorTileEntity) tileEntity, world)
					: null;
		case BlockOperatorBench.GUI_ID:
			return world.getBlockId(x, y, z) == EduCraft.OPERATOR_BENCH.blockID ? new OperatorContainer(
					player.inventory, world, x, y, z) : null;
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world,
			int x, int y, int z) {
		switch (id) {
		case BlockCalculator.GUI_ID:
			TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
			return (tileEntity instanceof CalculatorTileEntity) ? new CalculatorGui(
					player.inventory, world, (CalculatorTileEntity) tileEntity)
					: null;
		case BlockOperatorBench.GUI_ID:
			return world.getBlockId(x, y, z) == EduCraft.OPERATOR_BENCH.blockID ? new OperatorGui(
					player.inventory, world, x, y, z) : null;
		}
		return null;
	}
}
