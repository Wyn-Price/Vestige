package com.wynprice.vestige.elementbehaviour;

import com.wynprice.vestige.calculation.Atom;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class GroupOneWater extends BaseItemEntityReaction
{
	@Override
	protected boolean isAtomAccepted(int damage) 
	{
		return Atom.getAtom(damage).getShells().get(Atom.getAtom(damage).getShells().size() - 1).getAdded() == 1;
	}
	
	@Override
	public void onEntityItemUsed(World world, Vec3d pos, ItemStack stack, EntityItem item) 
	{
		if(!world.isRemote && item.isWet() && !item.isDead)
		{
			item.setDead();
			world.createExplosion(null, pos.x, pos.y, pos.z, Atom.getAtom(stack.getMetadata()).getShells().size() * Atom.getAtom(stack.getMetadata()).getShells().size() / 2f, true);
		}
	}
	
}
