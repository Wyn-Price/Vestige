package com.wynprice.vestige.proxy;

import com.wynprice.vestige.VestigeItems;
import com.wynprice.vestige.elementbehaviour.GroupOneWater;
import com.wynprice.vestige.events.BakeSpecialRenderEvent;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy 
{
	public void preInit(FMLPreInitializationEvent event)
	{
		
		Object[] objects = {
				new BakeSpecialRenderEvent(),
				new GroupOneWater()
		};
		for(Object o : objects)
		MinecraftForge.EVENT_BUS.register(o);

		
		VestigeItems.preInit();
	}
	
	public void init(FMLInitializationEvent event)
	{
		
	}
	
	public void postInit(FMLPostInitializationEvent event)
	{
		
	}
}
