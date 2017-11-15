package com.wynprice.vestige.calculation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.wynprice.vestige.calculation.molecule.Molecule;

public class Atom
{
	private static final HashMap<Integer, Atom> ATOMS = new HashMap<>();
	private static final HashMap<Integer, ArrayList<Atom>> OUTERLAYER_LIST = new HashMap<>();
	
	public static Atom getAtom(int electrons)
	{
		if(!ATOMS.containsKey(electrons))
			initAtom(electrons);
		return ATOMS.get(electrons);
	}
	
	public static void initAtom(int electrons)
	{
		Atom atom = new Atom(electrons);
		ATOMS.put(electrons, atom);
		if(atom.shells.size() == 0)
			return;
		addAtomToMap(OUTERLAYER_LIST, atom.getOuterShellElecrons(), atom);
	}
	
	public static ArrayList<Atom> getAtomsWithLastShell(int shellElectrons){return getAtomListFromMap(OUTERLAYER_LIST, shellElectrons);}
	
	private static void addAtomToMap(HashMap<Integer, ArrayList<Atom>> map, int electrons, Atom atom)
	{
		if(!map.containsKey(electrons))
			map.put(electrons, new ArrayList<>());
		map.get(electrons).add(atom);
	}
	
	private static ArrayList<Atom> getAtomListFromMap(HashMap<Integer, ArrayList<Atom>> map, int electrons)
	{
		return map.get(electrons) == null ? new ArrayList<>() : new ArrayList<>(map.get(electrons));
	}
	
	
	private ArrayList<Shell> shells = new ArrayList<Shell>();
	private int e;
	private final int totalElectrons;
	
	private Atom(int totalElectrons)
	{
		this.totalElectrons = totalElectrons;
		shellNoticableChange.clear();
		shellNoticableChange.add(18);
		k = 0;
		ArrayList<Integer> j = new ArrayList<Integer>();
		int[] p = {1,3,11,19,37,55,87,119};
		for (int l : p)
			j.add(l);
		boolean extendListDone = false;
		int s = 14;
		int o = 0;
		while(!extendListDone)
			if(totalElectrons >= j.get(j.size()-1))
			{
				if(o % 2 == 1)
					s += 4;
				j.add(s + j.get(j.size()-1));
				o++;
			}
			else
				extendListDone = true;
		
		for(int i = 0; i < j.size(); i ++)
		{
			if(totalElectrons >= j.get(i))
				shells.add(new Shell(i));
		}	
		e = totalElectrons;
		for(int i = 0; i < shells.size(); i ++)
		{
			simpleAdd(i);
			if(i+1==shells.size() || i < 2 || totalElectrons < 21)
				continue;
			addS(i+1,2);
			Shell prev = shells.get(i - 1);
			Shell next = shells.get(i + 1);
			if(next.s == 2)
			{
				if(prev.hasF())
					addF(i-1, 14);
				addD(i, 10);
				if(prev.hasF())
				{
					addF(i-1, 14);
					if(shells.get(i - 2).getInternalMax() > 14)
						createCustomShells(shells.get(i - 2));
				}
			}
		}
		
		ArrayList<Shell> finishShell = new ArrayList<Shell>();
		for(Shell shell : shells)
			if(shell.getAdded() != 0)
				finishShell.add(shell);
		shells = finishShell;
		
	}
	
	public Shell getOuterShell()
	{
		return shells.get(shells.size() - 1);
	}
	
	public int getOuterShellElecrons()
	{
		if(shells.size() == 0)
			return 0;
		return getOuterShell().getAdded();
	}
	
	private void createCustomShells(Shell shell) 
	{
		int k = ((shell.getInternalMax() - 2) / 4) - 4;
		for(int j = 0; shell.customSubs.size() < k; j++)
			shell.createNewSub((j * 4) + 18);
		
		addToCustomShell(shell, shell.getInternalMax());
	}
	
	private ArrayList<Integer> shellNoticableChange = new ArrayList<Integer>();
	int k = 0;
	
	private void addToCustomShell(Shell shell, int amount)
	{
		k++;
		if(shellNoticableChange.get(shellNoticableChange.size()-1) < amount)
			shellNoticableChange.add(amount);
		for(int i = 0; i < k; i++)
		{
			if(shells.get(shell.getPosition() - i).getInternalMax() > shellNoticableChange.get(i))
			{
				int n;
				if(e <= shellNoticableChange.get(i))
					n = e;
				else
					n = shellNoticableChange.get(i);
				e -=shells.get(shell.getPosition() - i).addCustomSub(i, n);
			}
				
		}	
	}
	
	private void simpleAdd(int shellPosition)
	{
		addS(shellPosition,2);
		addP(shellPosition,6);
	}
	
	private void addS(int shellPosition, int addative)
	{
		int num = e >= addative? addative : e;
		e += shells.get(shellPosition).addS(num);
		e -= num;		
	}
	
	private void addP(int shellPosition, int addative)
	{
		if(shellPosition == 0)
			return;
		int num = e >= addative? addative : e;
		e += shells.get(shellPosition).addP(num);
		e -= num;		
	}
	
	private void addD(int shellPosition, int addative)
	{
		int num = e >= addative? addative : e;
		e += shells.get(shellPosition).addD(num);
		e -= num;	
	}
	
	private void addF(int shellPosition, int addative)
	{
		int num = e >= addative? addative : e;
		e += shells.get(shellPosition).addF(num);
		e -= num;	
	}

	public void addBufferData() {
		for(Shell s : shells)
			VestigeChemistry.addData(s.getPosition(), s.getAdded(), shells.size());
	}
	
	public ArrayList<Shell> getShells() {
		return shells;
	}
	
	public String getElementSymbol()
	{
		return VestigeChemistry.capatilizeFirstLetter(VestigeChemistry.ELEMENT_SYMBOLS[totalElectrons - 1]);
	}
	
	public int getSize() {
		return shells.size();
	}
	
	public int getTotalElectrons() {
		return totalElectrons;
	}
	
	public boolean isTransitionMetal() //Does the atom exist in any of the normal groups
	{
		if(getOuterShellElecrons() != 2 || shells.size() < 3 || Atom.getAtom(totalElectrons - 1).getOuterShellElecrons() == 1)
			return false;
		return true;
	}
	
	public boolean isDiatomic() //Seemingly no pattern to this, assume it stops at Iodine
	{
		return Arrays.asList(1, 7, 8, 9, 17, 35, 53).contains(totalElectrons);
	}
	
	public Molecule asMolecule()
	{
		return new Molecule(this, 1);
	}

	public List<Integer> getSimpleIon()
	{
		if(isTransitionMetal()) return new ArrayList<>();
		switch (getOuterShellElecrons()) {
		case 1:return Arrays.asList(1);
		case 2:return Arrays.asList(2);
		case 3:return Arrays.asList(3);
		case 4:return Arrays.asList(4, -4);
		case 5:return Arrays.asList(-3);
		case 6:return Arrays.asList(-2);
		case 7:return Arrays.asList(-1);
		}
		return new ArrayList<>();
	}
	
}
