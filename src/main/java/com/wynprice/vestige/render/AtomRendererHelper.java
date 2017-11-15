package com.wynprice.vestige.render;

import com.wynprice.vestige.Vestige;
import com.wynprice.vestige.VestigeItems;
import com.wynprice.vestige.calculation.Atom;
import com.wynprice.vestige.calculation.AtomImageWriter;
import com.wynprice.vestige.calculation.VestigeChemistry;
import com.wynprice.vestige.item.ItemAtom;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class AtomRendererHelper
{
	public static final class TileEntityHelper extends TileEntity{}
	
	public static final class ItemRendererHelper extends TileEntitySpecialRenderer<TileEntityHelper>
	{	
		@Override
		public void render(TileEntityHelper te, double x, double y, double z, float partialTicks, int destroyStage,
				float alpha) 
		{
			Minecraft.getMinecraft().renderEngine.bindTexture(AtomImageWriter.getLocation(AtomBakedModel.damage));
			GlStateManager.translate(0, 0, 0.5d);
			new ModelAtom().render();
			GlStateManager.translate(0, 0, -0.5d);
		}
	}
	
	public static final class ModelAtom extends ModelBase
	{
		private final ModelRenderer atomParts;
		
		public ModelAtom() 
		{
			atomParts = new ModelRenderer(this, 0, 0);
			atomParts.setTextureSize(128, 128);
			atomParts.addBox(0, 0, 0, 128, 128, 0);
		}
		
		public void render() 
		{
			atomParts.render(1f / 128f);
		}
	}
	
	public static void preInit()
	{
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHelper.class, new ItemRendererHelper());
		Vestige.LOGGER.info("Initializing the big bang");
		long time = System.currentTimeMillis();
		for(int i = 0; i <= 118; i++)
			VestigeChemistry.registerAtom(i);
		for(int i = 0; i < ItemAtom.ATOM_LIMIT; i++)
			Atom.getAtom(i);
		Vestige.LOGGER.info("Big bang achieved in {} ms. Hello Universe", System.currentTimeMillis() - time);
		
	}
}