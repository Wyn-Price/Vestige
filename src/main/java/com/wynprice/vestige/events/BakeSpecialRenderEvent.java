package com.wynprice.vestige.events;

import com.wynprice.vestige.Vestige;
import com.wynprice.vestige.calculation.VestigeChemistry;
import com.wynprice.vestige.render.AtomBakedModel;
import com.wynprice.vestige.render.AtomRendererHelper;
import com.wynprice.vestige.render.CompoundBakedModel;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BakeSpecialRenderEvent 
{
	@SubscribeEvent
	 public void onModelBake(ModelBakeEvent e) 
	 {
		AtomRendererHelper.preInit();

		for(ModelResourceLocation model : e.getModelRegistry().getKeys())
			if(model.getResourceDomain().equals(Vestige.MODID))
				if(model.getResourcePath().equals("atom"))
					e.getModelRegistry().putObject(model, new AtomBakedModel(e.getModelRegistry().getObject(model)));
				else if(model.getResourcePath().equals("compound"))
					e.getModelRegistry().putObject(model, new CompoundBakedModel(e.getModelRegistry().getObject(model)));
	 }
}
