package org.educraft.block.ordering;

import org.educraft.block.CraftingTileEntity;

public class OrderingTileEntity extends CraftingTileEntity {
	private BenchType type;

	public OrderingTileEntity(BenchType type) {
		this.type = type;
	}
	
	public BenchType getBenchType() {
		return this.type;
	}
}
