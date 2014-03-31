package org.educraft.block.calculator;

import org.educraft.EduCraft;
import org.educraft.block.CraftingTileEntity;
import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

/**
 * This class handles the creation and display of the {@link BlockCalculator}
 * GUI.
 */
@SideOnly(Side.CLIENT)
public class CalculatorGui extends GuiContainer {

	/**
	 * Location to where the texture is stored
	 */
	private ResourceLocation calculator = new ResourceLocation(EduCraft.GuiTexturePrefix
			+ "crafting_table.png");
	
	/**
	 * Class constructor.
	 * 
	 * @param inventory
	 *            the inventory to display in this GUI
	 * @param world
	 *            the world the GUI is in
	 * @param tileEntity
	 *            the tile entity containing the crafting inventories
	 */
	public CalculatorGui(InventoryPlayer inventory, World world,
			CraftingTileEntity tileEntity) {
		super(new CalculatorContainer(inventory, tileEntity, world));
	}

	/**
	 * Draw the foreground layer for the GuiContainer (everything in front of
	 * the items)
	 */
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		this.fontRenderer.drawString("Calculator", 28, 6, 4210752);
		this.fontRenderer.drawString(I18n.getString("container.inventory"), 8,
				this.ySize - 96 + 2, 4210752);
	}

	/**
	 * Draw the background layer for the GuiContainer (everything behind the
	 * items)
	 */
	protected void drawGuiContainerBackgroundLayer(float par1, int par2,
			int par3) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(calculator);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
	}
}
