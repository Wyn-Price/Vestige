package com.wynprice.vestige.calculation;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;

public class AtomImageWriter 
{
	private final BufferedImage bufferedImage;
	private final Graphics2D graphics;
	
	public AtomImageWriter(int xSize, int ySize) 
	{
		bufferedImage = new BufferedImage(xSize, ySize, BufferedImage.TYPE_INT_ARGB);
		graphics = bufferedImage.createGraphics();
	}
	
	public Graphics2D getGraphics() {
		return graphics;
	}
	
	public static final HashMap<Integer, ResourceLocation> ATOM_RESOURCELOCATION_MAP = new HashMap<>();
	
	public void build(int electrons)
	{
		if(electrons <= 0)
			return;
		ATOM_RESOURCELOCATION_MAP.put(electrons, Minecraft.getMinecraft().getTextureManager().getDynamicTextureLocation(String.valueOf(electrons), new DynamicTexture(bufferedImage)));
	}
	
	
	
	public static ResourceLocation getLocation(int electrons)
	{
		if(!ATOM_RESOURCELOCATION_MAP.containsKey(electrons))
		{
			new Atom(electrons).addBufferData();
			VestigeChemistry.drawImage();
		}
		if(electrons <= 0)
			return new ResourceLocation("missingno");
		return ATOM_RESOURCELOCATION_MAP.get(electrons);
	}
}
