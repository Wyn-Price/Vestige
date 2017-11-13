package com.wynprice.vestige.elementbehaviour;

import com.wynprice.vestige.calculation.Atom;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class GroupOneWater extends BaseItemEntityReaction
{
	@Override
	protected boolean isAtomAccepted(int damage) 
	{
		return Atom.getAtom(damage).getOuterShellElecrons() == 1;
	}
	
	@Override
	public void onEntityItemUsed(World world, Vec3d pos, ItemStack stack, EntityItem item) 
	{
		if(!world.isRemote && item.isWet() && !item.isDead)
		{
			Atom atom = Atom.getAtom(stack.getMetadata());
			item.setDead();
			world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, pos.x, pos.y, pos.z, 0, 0, 0);
			if(atom.getShells().size() > 4)
				ElementExplosion.explode(world, pos, (atom.getShells().size()-4)*(atom.getShells().size()-4));
		}
	}
	
}
