package com.wynprice.vestige.calculation.molecule;

import java.util.ArrayList;

import com.wynprice.vestige.calculation.Atom;

public class Molecule 
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
}
