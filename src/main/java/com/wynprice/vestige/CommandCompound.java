package com.wynprice.vestige;

import java.util.ArrayList;
import java.util.Arrays;

import com.wynprice.vestige.calculation.Atom;
import com.wynprice.vestige.calculation.ChemistryHelper;
import com.wynprice.vestige.calculation.VestigeChemistry;
import com.wynprice.vestige.calculation.compound.BaseAtomPart;
import com.wynprice.vestige.calculation.compound.Compound;
import com.wynprice.vestige.calculation.molecule.Molecule;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentTranslation;

public class CommandCompound extends CommandBase {

	@Override
	public String getName() {
		return "compound";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return new TextComponentTranslation("command.compound.usage").getUnformattedText();
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException 
	{
		if(args.length < 1)
			throw new CommandException("command.compound.usage");
		ArrayList<Molecule> list = new ArrayList<>();
		for(String string : args)
		{
			String s = "";
			String nums = "";
			boolean hasSwapped = false;
			for(char c : string.toCharArray())
				if(Character.isDigit(c))
				{
					hasSwapped = true;
					nums += c;
				}
				else if(!hasSwapped)
					s += c;
				else
					throw new CommandException("command.compound.nonmole", string);
			if(nums == "") nums = "0";
			s = s.toLowerCase();
			int num = parseInt(nums, 0);
			if(!Arrays.asList(VestigeChemistry.ELEMENT_SYMBOLS).contains(s))
				throw new CommandException("command.compound.nonelement", s);
			list.add(new Molecule(Atom.getAtom(Arrays.asList(VestigeChemistry.ELEMENT_SYMBOLS).indexOf(s) + 1), num));
		}
			
		ItemStack stack = new ItemStack(VestigeItems.COMPOUND);
		stack.setTagCompound(new NBTTagCompound());
		stack.getTagCompound().setTag("vestige", ChemistryHelper.getNbt(new Compound(list.toArray(new BaseAtomPart[list.size()]))));
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
		notifyCommandListener(sender, this, "command.compound.success", stack.getDisplayName() , stack.getCount(), ((EntityPlayer)sender.getCommandSenderEntity()).getName());
	}

}
