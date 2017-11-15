package com.wynprice.vestige;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.wynprice.vestige.calculation.Atom;
import com.wynprice.vestige.calculation.ChemistryHelper;
import com.wynprice.vestige.calculation.Shell;
import com.wynprice.vestige.calculation.VestigeChemistry;
import com.wynprice.vestige.calculation.compound.SimpleCompound;
import com.wynprice.vestige.item.ItemAtom;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.NumberInvalidException;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;

public class CommandElement extends CommandBase {

	@Override
	public String getName() {
		return "element";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return new TextComponentTranslation("command.element.usage").getUnformattedText();
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException 
	{
		if(args.length < 1)
			throw new CommandException("command.element.usage");
		ItemStack stack = ItemStack.EMPTY;
		if(!Arrays.asList(VestigeChemistry.ELEMENT_NAMES).contains(args[0]) || !Arrays.asList(VestigeChemistry.ELEMENT_SYMBOLS).contains(args[0]))
			if(Arrays.asList(VestigeChemistry.ELEMENT_NAMES).indexOf(args[0]) != -1)
				stack = new ItemStack(VestigeItems.ATOM, 1, Arrays.asList(VestigeChemistry.ELEMENT_NAMES).indexOf(args[0]) + 1);
			else if(Arrays.asList(VestigeChemistry.ELEMENT_SYMBOLS).indexOf(args[0]) != -1)
				stack = new ItemStack(VestigeItems.ATOM, 1, Arrays.asList(VestigeChemistry.ELEMENT_SYMBOLS).indexOf(args[0]) + 1);
		if(stack.isEmpty())
			try {
				stack = new ItemStack(VestigeItems.ATOM, 1, parseInt(args[0], 1, ItemAtom.ATOM_LIMIT));
			}
			catch (NumberInvalidException e) {
				throw new CommandException("command.element.notfound", args[0]);
			}
		if(args.length > 1)
			stack.setCount(this.parseInt(args[1], 0, stack.getItem().getItemStackLimit(stack)));
		if(sender.getCommandSenderEntity() instanceof EntityPlayer)
			if(!((EntityPlayer)sender.getCommandSenderEntity()).inventory.addItemStackToInventory(stack.copy()))
			{
				EntityItem entityitem =  ((EntityPlayer)sender.getCommandSenderEntity()).dropItem(stack.copy(), false);
                if (entityitem != null)
                {
                    entityitem.setNoPickupDelay();
                    entityitem.setOwner(((EntityPlayer)sender.getCommandSenderEntity()).getName());
                }
			}
		notifyCommandListener(sender, this, "command.element.success", stack.getDisplayName() , stack.getCount(), ((EntityPlayer)sender.getCommandSenderEntity()).getName());
	}
	
	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args,
			BlockPos targetPos) {
		return args.length == 1 ? getListOfStringsMatchingLastWord(args, VestigeChemistry.ELEMENT_NAMES) : Collections.<String>emptyList();
	}

}
