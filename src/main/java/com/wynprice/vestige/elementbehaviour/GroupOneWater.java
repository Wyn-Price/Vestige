package com.wynprice.vestige.elementbehaviour;

import com.wynprice.vestige.calculation.Atom;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SPacketParticles;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
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
			if(atom.getShells().size() > 4)
				ElementExplosion.explode(world, pos, 1.5f * (atom.getShells().size() - 4));
			else 
			{
				world.playSound((EntityPlayer)null, new BlockPos(pos), SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, atom.getShells().size() / 5f, 1f);
				for(EntityPlayer entityplayer : world.playerEntities)
					((EntityPlayerMP)entityplayer).connection.sendPacket(new SPacketParticles(EnumParticleTypes.CLOUD, true, (float)pos.x, (float)pos.y, (float)pos.z, 0f, 0f, 0f, 0f, atom.getShells().size() * atom.getShells().size()));
			}
//			InventoryHelper.spawnItemStack(world, pos.x, pos.y, pos.z, stack);
		}
	}
	
}
