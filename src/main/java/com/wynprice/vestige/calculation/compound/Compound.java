package com.wynprice.vestige.calculation.compound;

import java.lang.Character.UnicodeBlock;

import com.wynprice.vestige.calculation.ChemistryHelper;
import com.wynprice.vestige.calculation.molecule.Molecule;

public class Compound 
{
	protected final Molecule[] molecules;
	
	public Compound(Molecule... molecules) 
	{
		this.molecules = molecules;
	}
	
	public final String getMoleculeName()
	{
		String name = "";
		for(Molecule molecule : molecules)
			name += molecule.getAtom().getElementSymbol() + (molecule.getAmount() > 1 ? ChemistryHelper.getSubscript(molecule.getAmount()) : "");
		return name;
	}
}

