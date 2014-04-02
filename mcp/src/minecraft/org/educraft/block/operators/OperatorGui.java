package org.educraft.block.operators;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.educraft.EduCraft;
import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class OperatorGui extends GuiContainer {
	
	private static final ResourceLocation craftingTableGuiTextures = new ResourceLocation(
			"textures/gui/container/crafting_table.png");
	
	/**
	 * Operator GUI Constructor for creating the interface 
	 * where players perform operators on numbers in order to obtain a value
	 * 
	 */
	public OperatorGui(InventoryPlayer par1InventoryPlayer, World par2World,
			int par3, int par4, int par5) {
		super(new OperatorContainer(par1InventoryPlayer, par2World, par3, par4,
				par5));
	}
	
	/**
	 * Draw the forground layer of the Operator Gui
	 * 
	 */

	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		this.fontRenderer.drawString("Operators", 28, 6, 4210752);
		this.fontRenderer.drawString(I18n.getString("container.inventory"), 8,
				this.ySize - 96 + 2, 4210752);
	}
	
	/**
	 * Draw the background layer of the Operator Gui
	 * 
	 */

	protected void drawGuiContainerBackgroundLayer(float par1, int par2,
			int par3) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(craftingTableGuiTextures);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
	}

}
