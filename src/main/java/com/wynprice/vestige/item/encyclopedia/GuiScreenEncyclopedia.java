package com.wynprice.vestige.item.encyclopedia;

import com.wynprice.vestige.Vestige;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiScreenEncyclopedia extends GuiContainer
{

	public GuiScreenEncyclopedia(EntityPlayer player) 
	{
		super(new ContainerEncyclopedia(player));
		this.xSize = 176;
		this.ySize = 216;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) 
	{
		this.drawDefaultBackground();
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(Vestige.MODID, "textures/gui/container/encyclopedia.png"));
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
	}

}
