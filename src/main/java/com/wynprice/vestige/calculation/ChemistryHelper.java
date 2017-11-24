package com.wynprice.vestige.calculation;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.lang3.tuple.Pair;

import com.wynprice.vestige.calculation.compound.BaseAtomPart;
import com.wynprice.vestige.calculation.compound.Compound;
import com.wynprice.vestige.calculation.molecule.CompoundedMonocule;
import com.wynprice.vestige.calculation.molecule.Molecule;

import net.minecraft.nbt.NBTTagCompound;

public class ChemistryHelper 
{
	public static int HCF(int a, int b)
	{
	    while (a != b) 
	    	if (a > b) a -= b;
	        else b -= a;
	    return a;
	}

	public static int LCM(int a, int b)
	{
	    return (a * b) / HCF(a, b);
	}
	
//	public static String getSubscript(int subscript)
//	{
//		String finalString = "";
//		for(char c : String.valueOf(subscript).toCharArray())
//			switch (c) {
//			case '0': finalString += "\u2080";
//			case '1': finalString += "\u2081";
//			case '2': finalString += "\u2082";
//			case '3': finalString += "\u2083";
//			case '4': finalString += "\u2084";
//			case '5': finalString += "\u2085";
//			case '6': finalString += "\u2086";
//			case '7': finalString += "\u2087";
//			case '8': finalString += "\u2088";
//			case '9': finalString += "\u2089";
//			}
//		return finalString;
//	}
	
	public static Compound getCompound(NBTTagCompound compound)
	{
		NBTTagCompound nbt = compound.getCompoundTag("vestige");
		ArrayList<BaseAtomPart> molecules = new ArrayList<>();
		int total = nbt.getInteger("total_molecules");
		if(total > 0)
			for(int i = 0; i < total; i++)
				molecules.add(new Molecule(Atom.getAtom(nbt.getInteger("atom_" + String.valueOf(i))), nbt.getInteger("amount_" + String.valueOf(i))));
		BaseAtomPart[] parts = new BaseAtomPart[molecules.size()];
		for(int i = 0; i < molecules.size(); i++)
			parts[i] = molecules.get(i);
		return new Compound(parts);
	}
	
	public static NBTTagCompound getNbt(Compound compound)
	{
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setInteger("total_molecules", compound.getTotalMolecules());
		for(int i = 0; i < compound.getTotalMolecules(); i++)
		{
			Pair<Atom, Integer> pair = compound.getTotalList().get(i);
			nbt.setInteger("atom_" + String.valueOf(i), pair.getLeft().getTotalElectrons());
			nbt.setInteger("amount_" + String.valueOf(i), pair.getRight());
		}
		return nbt;
	}
}
