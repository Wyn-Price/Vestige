package com.wynprice.vestige.calculation.compound;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import com.wynprice.vestige.calculation.Atom;

public abstract class BaseAtomPart 
{
	public abstract String getName();
	
	public abstract List<Pair<Atom, Integer>> atomList();
}
