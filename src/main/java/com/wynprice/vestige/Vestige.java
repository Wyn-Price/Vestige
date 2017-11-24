package com.wynprice.vestige;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.wynprice.vestige.proxy.CommonProxy;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = Vestige.MODID, name = Vestige.MODNAME, version = Vestige.VERSION)
public class Vestige
{
    public static final String MODID = "vestige";
    public static final String MODNAME = "Vestige";
    public static final String VERSION = "0.1.0";
    
    public static final Logger LOGGER = LogManager.getLogger(MODID);
    
    @SidedProxy(clientSide = "com.wynprice.vestige.proxy.ClientProxy", serverSide = "com.wynprice.vestige.proxy.ServerProxy")
    public static CommonProxy proxy;
    
    @Instance(MODID)
    public static Vestige instance;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	proxy.preInit(event);
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	proxy.init(event);
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    	proxy.postInit(event);
    }
    
    @EventHandler
	public void serverLoad(FMLServerStartingEvent event)
	{
    	event.registerServerCommand(new CommandElement());
    	event.registerServerCommand(new CommandCompound());
	}
}
