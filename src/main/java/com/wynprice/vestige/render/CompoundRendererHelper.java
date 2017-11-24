package com.wynprice.vestige.render;

import com.wynprice.vestige.calculation.ChemistryHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class CompoundRendererHelper
{
	public static final class TileEntityHelper extends TileEntity{}
	
	public static final class ItemRendererHelper extends TileEntitySpecialRenderer<TileEntityHelper>
	{	
		@Override
		public void render(TileEntityHelper te, double x, double y, double z, float partialTicks, int destroyStage,
				float alpha) 
		{
			if(!CompoundBakedModel.stack.hasTagCompound())
				CompoundBakedModel.stack.setTagCompound(new NBTTagCompound());
			Minecraft.getMinecraft().renderEngine.bindTexture(ChemistryHelper.getCompound(CompoundBakedModel.stack.getTagCompound()).getLocation());
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
			atomParts.setTextureSize(256, 128);
			atomParts.addBox(0, 0, 0, 128, 128, 0);
		}
		
		public void render() 
		{
			atomParts.render(1f / 128f);
		}
	}
}