package com.wynprice.vestige.proxy;

import com.wynprice.vestige.Vestige;
import com.wynprice.vestige.VestigeItems;
import com.wynprice.vestige.elementbehaviour.ElementRadioactivity;
import com.wynprice.vestige.elementbehaviour.GroupOneWater;
import com.wynprice.vestige.events.BakeSpecialRenderEvent;
import com.wynprice.vestige.events.GuiHandler;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class CommonProxy 
{
	public void preInit(FMLPreInitializationEvent event)
	{
		
		Object[] objects = {
				new BakeSpecialRenderEvent(),
				new GroupOneWater(),
				new ElementRadioactivity()
		};
		for(Object o : objects)
		MinecraftForge.EVENT_BUS.register(o);

		
		VestigeItems.preInit();
	}
	
	public void init(FMLInitializationEvent event)
	{
		NetworkRegistry.INSTANCE.registerGuiHandler(Vestige.instance, new GuiHandler());
	}
	
	public void postInit(FMLPostInitializationEvent event)
	{
		
	}
}
