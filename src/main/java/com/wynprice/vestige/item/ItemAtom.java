package com.wynprice.vestige.item;

import java.util.List;

import com.wynprice.vestige.calculation.VestigeChemistry;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemAtom extends Item
{
	@Override
	public String getItemStackDisplayName(ItemStack stack) 
	{
		return stack.getMetadata() <= 118 ? VestigeChemistry.getNameOfElement(stack.getMetadata()) : super.getItemStackDisplayName(stack);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(TextFormatting.GRAY + new TextComponentTranslation("element.mass").getUnformattedText() + ": " + VestigeChemistry.getElementMass(stack.getMetadata()));
	}
}
