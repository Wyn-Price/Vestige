package com.wynprice.vestige.elementbehaviour;

import java.util.ArrayList;

import com.wynprice.vestige.item.ItemAtom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;

public abstract class BaseItemEntityReaction 
{
	public abstract void onEntityItemTick(World world, Vec3d pos, ItemStack stack, EntityItem item);
	
	protected abstract boolean isAtomAccepted(int electron);
	
	@SubscribeEvent
	public void onEntityUpdate(WorldTickEvent event)
	{
		ArrayList<Entity> entityList = new ArrayList<>(event.world.loadedEntityList);
		for(Entity entity : entityList)
			if(entity instanceof EntityItem && ((EntityItem)entity).getItem().getItem() instanceof ItemAtom && isAtomAccepted(((EntityItem)entity).getItem().getMetadata()))
				onEntityItemTick(event.world, entity.getPositionVector(), ((EntityItem)entity).getItem(), (EntityItem) entity);
	}
}
