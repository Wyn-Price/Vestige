package com.wynprice.vestige;

import com.wynprice.vestige.item.ItemAtom;
import com.wynprice.vestige.item.ItemCompound;
import com.wynprice.vestige.item.ItemEncyclopedia;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class VestigeItems 
{
	public static final Item ATOM = new ItemAtom().setRegistryName("atom").setUnlocalizedName("atom").setHasSubtypes(true);
	public static final Item COMPOUND = new ItemCompound().setRegistryName("compound").setUnlocalizedName("compound");
	public static final Item ENCYCLOPEDIA = new ItemEncyclopedia().setRegistryName("atom_encyclopedia").setUnlocalizedName("atom_encyclopedia");
	
	public static void preInit()
	{
		register(ATOM);
		register(ENCYCLOPEDIA);
		register(COMPOUND);
	}
	
	public static void regRenders()
	{
		regRenderAtom(ATOM);
		regRender(ENCYCLOPEDIA);
		regRender(COMPOUND);
	}
	
	private static void register(Item item)
	{
		ForgeRegistries.ITEMS.register(item);
	}
	
	private static void regRenderAtom(Item item)
	{
		for(int i = 0; i < 65535 && i < ItemAtom.ATOM_LIMIT; i++)
			ModelLoader.setCustomModelResourceLocation(item, i, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
	
	private static void regRender(Item item)
	{
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));

	}
}
