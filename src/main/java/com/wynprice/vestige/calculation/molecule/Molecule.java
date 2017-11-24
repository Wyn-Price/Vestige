package com.wynprice.vestige.calculation.molecule;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import com.wynprice.vestige.calculation.Atom;
import com.wynprice.vestige.calculation.ChemistryHelper;
import com.wynprice.vestige.calculation.compound.BaseAtomPart;

public class Molecule extends BaseAtomPart
{
	private final Atom atom;
	private final int amount;
	
	public Molecule(Atom atom, int amount) 
	{
		this.amount = amount;
		this.atom = atom;
	}
	
	public Atom getAtom() {
		return atom;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public Pair<Atom, Integer> createPair()
	{
		return Pair.of(atom, amount);
	}

	@Override
	public String getName() {
		return atom.getElementSymbol() + (amount > 1 ? String.valueOf(amount) : "");
	}

	@Override
	public List<Pair<Atom, Integer>> atomList() {
		return Arrays.asList(createPair());
	}
}
