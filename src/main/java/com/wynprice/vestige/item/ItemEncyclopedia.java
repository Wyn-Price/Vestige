package com.wynprice.vestige.item;

import com.wynprice.vestige.Vestige;
import com.wynprice.vestige.events.GuiHandler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemEncyclopedia extends Item
{
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) 
	{
		playerIn.openGui(Vestige.instance, GuiHandler.ENCYCLOPEDIAOFATOMS, worldIn, playerIn.getPosition().getX(), playerIn.getPosition().getY(), playerIn.getPosition().getZ());
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
}
