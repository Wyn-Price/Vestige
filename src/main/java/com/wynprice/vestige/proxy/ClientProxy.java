package com.wynprice.vestige.proxy;

import com.wynprice.vestige.VestigeItems;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy 
{
	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
		VestigeItems.regRenders();
	}
	
	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
	}
}
