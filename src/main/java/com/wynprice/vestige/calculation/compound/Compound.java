package com.wynprice.vestige.calculation.compound;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import com.wynprice.vestige.calculation.Atom;
import com.wynprice.vestige.calculation.ChemistryHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

public class Compound
{
	protected final BaseAtomPart[] atomParts;
	
	private ResourceLocation location;
	
	public Compound(BaseAtomPart... molecules) 
	{
		this.atomParts = molecules;
	}
	
	public String getMoleculeName()
	{
		String name = "";
		for(BaseAtomPart part : atomParts)
			name += part.getName();
		return name;
	}
	
	public List<Pair<Atom, Integer>> getTotalList()
	{
		ArrayList<Pair<Atom, Integer>> list = new ArrayList<>();
		for(BaseAtomPart part : atomParts)
			list.addAll(part.atomList());
		return list;
	}
	
	public int getTotalMolecules()
	{
		int total = 0;
		for(BaseAtomPart parts : atomParts)
			total += parts.atomList().size();
		return total;
	}
	
	private static final HashMap<NBTTagCompound, ResourceLocation> HELD_MAP = new HashMap<>();
	
	private ResourceLocation generateResourceLocation()
	{
		if(HELD_MAP.containsKey(ChemistryHelper.getNbt(this)))
			return HELD_MAP.get(ChemistryHelper.getNbt(this));
		BufferedImage image = new BufferedImage(256, 256, BufferedImage.TYPE_INT_ARGB);
		Graphics2D graphics = image.createGraphics();
		graphics.setColor(Color.BLACK);
		graphics.setFont(new Font(graphics.getFont().getFontName(), graphics.getFont().getStyle(), 50));
		for(int i = 0; i < atomParts.length; i++)
			graphics.drawString(atomParts[i].getName(), 100, ((180 / atomParts.length) * i) + 38);
		BufferedImage finalImage = new BufferedImage(512, 256, BufferedImage.TYPE_INT_ARGB);
		finalImage.createGraphics().drawImage(image, 512, 256, -256, -256, null);
		finalImage.createGraphics().drawImage(image, 0, 256, 256, -256, null);
		ResourceLocation loc =  Minecraft.getMinecraft().getTextureManager().getDynamicTextureLocation(getMoleculeName(), new DynamicTexture(finalImage));
		HELD_MAP.put(ChemistryHelper.getNbt(this), loc);
		return loc;
	}
	
	public ResourceLocation getLocation() {
		if(location == null) location = generateResourceLocation();
		return location ;
	}
	
}

