package com.wynprice.vestige.base;

import java.awt.Point;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public abstract class BaseContainer extends Container
{

	private final int totalSize;
	private final boolean playerInventory;
	protected final ItemStackHandler handler;
	
	public BaseContainer(ItemStackHandler handler, EntityPlayer player) 
	{
		this.handler = handler;
		this.playerInventory = player != null;
		int totalSize = handler.getSlots();
		if(shouldRenderHotbar())
			totalSize += 9;
		if(shouldRenderInventory())
			totalSize += 27;
		this.totalSize = totalSize;
		if(shouldRenderInventory())
		for(int i = 0; i < handler.getSlots(); i++)
			try 
			{
				getPoint(i);
			} 
			catch (IndexOutOfBoundsException e) 
			{
				throw new IllegalArgumentException("There arnt enough registered points to quantify all the slots");
			}
		
		for(int i = 0; i < handler.getSlots(); i++)
			this.addSlotToContainer(getSlot(handler, i, getPoint(i).x, getPoint(i).y));
		
		if(playerInventory)
		{
			Point p = getInventoryStart();
			if(shouldRenderHotbar() && !shouldRenderInventory())
				p.y -= 58;
			if(shouldRenderInventory())
				for(int y = 0; y < 3; ++y)
					for(int x = 0; x < 9; ++x)
						this.addSlotToContainer(new Slot(player.inventory, x + y * 9 + 9, p.x + x * 18, p.y + y * 18));
			if(shouldRenderHotbar())
				for(int x = 0; x < 9; ++x) 
					this.addSlotToContainer(new Slot(player.inventory, x, p.x + x * 18, p.y + 58));
		}
	}
	
	public BaseContainer(ItemStackHandler handler) {
		this(handler, null);
	}
	
	protected abstract Point getPoint(int index);
	
	protected abstract Point getInventoryStart();
	
	protected Slot getSlot(ItemStackHandler handler, int index, int xPosition, int yPosition) 
	{
		return new SlotItemHandler(handler, index, xPosition, yPosition);
	}
	
	protected boolean shouldRenderInventory() {
		return true;
	}
	
	protected boolean shouldRenderHotbar() {
		return true;
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int fromSlot) {
	    ItemStack previous = ItemStack.EMPTY;
	    Slot slot = (Slot) this.inventorySlots.get(fromSlot);

	    if (slot != null && slot.getHasStack()) {
	        ItemStack current = slot.getStack();
	        previous = current.copy();
	        if(playerInventory)
		        if (fromSlot < handler.getSlots()) {
		            if (!this.mergeItemStack(current, handler.getSlots(), totalSize, true))
		                return ItemStack.EMPTY;
		        } else {
		            if (!this.mergeItemStack(current, 0, handler.getSlots(), false))
		                return ItemStack.EMPTY;
		        }

	        if (current.getCount() == 0)
	            slot.putStack(ItemStack.EMPTY);
	        else
	            slot.onSlotChanged();

	        if (current.getCount() == previous.getCount())
	            return null;
	        slot.onTake(playerIn, current);
	    }
	    return previous;
	}

}