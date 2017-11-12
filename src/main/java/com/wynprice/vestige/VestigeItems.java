package com.wynprice.vestige;

import com.wynprice.vestige.item.ItemAtom;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class VestigeItems 
{
	public static final Item ATOM = new ItemAtom().setRegistryName("atom").setUnlocalizedName("atom").setHasSubtypes(true);
	
	public static void preInit()
	{
		ForgeRegistries.ITEMS.register(ATOM);
	}
	
	public static void regRenders()
	{
		regRender(ATOM);
	}
	
	private static void regRender(Item item)
	{
		for(int i = 0; i < 65535; i++)
			ModelLoader.setCustomModelResourceLocation(item, i, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
}
