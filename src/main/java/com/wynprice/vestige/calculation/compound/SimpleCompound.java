package com.wynprice.vestige.calculation.compound;

import com.wynprice.vestige.calculation.molecule.Molecule;

public class SimpleCompound extends Compound
{
	public SimpleCompound(Molecule molecule1, Molecule molecule2) 
	{
		super(molecule1, molecule2);
		boolean containsTransitionMetal = false;
		if(molecule1.getAtom().isTransitionMetal()) containsTransitionMetal = true;
		if(molecule2.getAtom().isTransitionMetal()) containsTransitionMetal = true;
		if(containsTransitionMetal) throw new IllegalArgumentException("Transition Metals are currently not supported while I figure out how on earth they work");
		boolean works = false;
		for(int atom1Ion : molecule1.getAtom().getSimpleIon())
			for(int atom2Ion : molecule2.getAtom().getSimpleIon())
				if(atom1Ion < 0 != atom2Ion < 0)
					works = true;
	}
}
