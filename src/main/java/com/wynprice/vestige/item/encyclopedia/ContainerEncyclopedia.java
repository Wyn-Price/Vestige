package com.wynprice.vestige.item.encyclopedia;

import java.awt.Point;

import com.wynprice.vestige.base.BaseContainer;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.items.ItemStackHandler;

public class ContainerEncyclopedia extends BaseContainer
{

	public ContainerEncyclopedia(EntityPlayer player) {
		super(new ItemStackHandler(NonNullList.withSize(1, ItemStack.EMPTY)), player);
	}

	@Override
	protected Point getPoint(int index) 
	{
		return new Point(79, 39);
	}

	@Override
	protected Point getInventoryStart() {
		return new Point(8, 192);
	}
	
	@Override
	protected boolean shouldRenderInventory() {
		return false;
	}
	
}
