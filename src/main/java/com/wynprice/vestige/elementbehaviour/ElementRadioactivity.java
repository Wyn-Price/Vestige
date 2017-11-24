package com.wynprice.vestige.elementbehaviour;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ElementRadioactivity extends BaseItemEntityReaction {

	@Override
	public void onEntityItemTick(World world, Vec3d pos, ItemStack stack, EntityItem item) 
	{
//		for(EntityPlayer player : world.playerEntities)
//			if(world.rayTraceBlocks(item.getPositionVector(), player.getPositionVector()) != null);
	}

	@Override
	protected boolean isAtomAccepted(int electron) {
		return electron > 83;
	}

}
