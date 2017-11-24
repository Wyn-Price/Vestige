package com.wynprice.vestige.calculation.molecule;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import com.wynprice.vestige.calculation.Atom;
import com.wynprice.vestige.calculation.ChemistryHelper;
import com.wynprice.vestige.calculation.compound.BaseAtomPart;

public class CompoundedMonocule extends BaseAtomPart
{
	
	private final Molecule[] molecules;
	
	public CompoundedMonocule(Molecule... molecules) {
		this.molecules = molecules;
	}

	@Override
	public String getName() 
	{
		String finalName = "";
		for(Molecule molecule : molecules)
			finalName += molecule.getAtom().getElementSymbol() + (molecule.getAmount() > 1 ? String.valueOf(molecule.getAmount()) : "");
		return finalName;
	}

	@Override
	public List<Pair<Atom, Integer>> atomList() 
	{
		ArrayList<Pair<Atom, Integer>> list = new ArrayList<>();
		for(Molecule molecule : molecules)
			list.add(molecule.createPair());
		return list;
	}

}
