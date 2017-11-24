package com.wynprice.vestige.events;

import com.wynprice.vestige.item.encyclopedia.ContainerEncyclopedia;
import com.wynprice.vestige.item.encyclopedia.GuiScreenEncyclopedia;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{
	
	public static final int ENCYCLOPEDIAOFATOMS = 0;
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		if (ID == ENCYCLOPEDIAOFATOMS)
			return new ContainerEncyclopedia(player);
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		if (ID == ENCYCLOPEDIAOFATOMS)
			return new GuiScreenEncyclopedia(player);
		return null;
	}

}