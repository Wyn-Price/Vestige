package com.wynprice.vestige.calculation;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import com.wynprice.vestige.VestigeItems;
import com.wynprice.vestige.render.AtomBakedModel;
import com.wynprice.vestige.render.AtomRendererHelper;
import com.wynprice.vestige.render.AtomRendererHelper.TileEntityHelper;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.client.ForgeHooksClient;

@SuppressWarnings("serial")
public class VestigeChemistry
{
	
	
	private static ArrayList<Integer> imageSizes = new ArrayList<Integer>();
	private static ArrayList<Integer> electrons = new ArrayList<Integer>();
	public static final String[] ELEMENT_NAMES = {"hydrogen","helium","lithium","beryllium","boron","carbon","nitrogen",
		"oxygen","fluorine","neon","sodium","magnesium","aluminum","silicon","phosphorus","sulfur","chlorine",
		"argon","potassium","calcium","scandium","titanium","vanadium","chromium","manganese","iron","cobalt",
		"nickel","copper","zinc","gallium","germanium","arsenic","selenium","bromine","krypton","rubidium",
		"strontium","yttrium","zirconium","niobium","molybdenum","technetium","ruthenium","rhodium","palladium",
		"silver","cadmium","indium","tin","antimony","tellurium","iodine","xenon","cesium","barium","lanthanum",
		"cerium","praseodymium","neodymium","promethium","samarium","europium","gadolinium","terbium","dysprosium",
		"holmium","erbium","thulium","ytterbium","lutetium","hafnium","tantalum","tungsten","rhenium","osmium",
		"iridium","platinum","gold","mercury","thallium","lead","bismuth","polonium","astatine","radon","francium",
		"radium","actinium","thorium","protactinium","uranium","neptunium","plutonium","americium","curium",
		"berkelium","californium","einsteinium","fermium","mendelevium","nobelium","lawrencium","rutherfordium",
		"dubnium","seaborgium","bohrium","hassium","meitnerium","darmstadtium","roentgenium","copernicium",
		"nihonium","flerovium","moscovium","livermorium","tennessine","oganesson"};
	
	public static final String[] ELEMENT_SYMBOLS = {"h","he","li","be","b","c","n","o","f","ne","na","mg","al","si","p",
		"s","cl","ar","k","ca","sc","ti","v","cr","mn","fe","co","ni","cu","zn","ga","ge","as","se","br","kr","rb",
		"sr","y","zr","nb","mo","tc","ru","rh","pd","ag","cd","in","sn","sb","te","i","xe","cs","ba","la","ce","pr",
		"nd","pm","sm","eu","gd","tb","dy","ho","er","tm","yb","lu","hf","ta","w","re","os","ir","pt","au","hg","tl",
		"pb","bi","po","at","rn","fr","ra","ac","th","pa","u","np","pu","am","cm","bk","cf","es","fm","md","no","lr",
		"rf","db","sg","bh","hs","mt","ds","rg","cn","nh","fl","mc","lv","ts","og"};

	public static final double[] ELEMENT_MASS = {1.00794D, 4.002602D, 6.941D, 9.012182D, 10.811D, 12.0107D, 14.00674D, 15.9994D,
			18.9984032D, 20.1797D, 22.989770D, 24.3050D, 26.981538D, 28.0855D, 30.973761D, 32.066D, 35.4527D, 39.948D, 39.0983D,
			40.078D, 44.955910D, 47.867D, 50.9415D, 51.9961D, 54.938049D, 55.845D, 58.933200D, 58.6934D, 63.546D, 65.39D, 69.723D,
			72.61D, 74.92160D, 78.96D, 79.904D, 83.80D, 85.4678D, 87.62D, 88.90585D, 91.224D, 92.90638D, 95.94D, 98D, 101.07D,
			102.90550D, 106.42D, 107.8682D, 112.411D, 114.818D, 118.710D, 121.760D, 127.60D, 126.90447D, 131.29D, 132.90545D,
			137.327D, 138.9055D, 140.116D, 140.90765D, 144.24D, 145D, 150.36D, 151.964D, 157.25D, 158.92534D, 162.50D, 164.93032D,
			167.26D, 168.93421D, 173.04D, 174.967D, 178.49D, 180.9479D, 183.84D, 186.207D, 190.23D, 192.217D, 195.078D, 196.96655D,
			200.59D, 204.3833D, 207.2D, 208.98038D, 209D, 210D, 222D, 223D, 226D, 227D, 232.0381D, 231.03588D, 238.0289D, 237D, 244D,
			243D, 247D, 247D, 251D, 252D, 257D, 258D, 259D, 262D, 261D, 262D, 263D, 262D, 265D, 266D, 269D, 272D, 277D, 286D, 289D,
			288D, 292D, 294D, 294D};
	
	public static void addData(int position, int ele, int totalShells)
	{
		if(imageSizes.isEmpty())
			 imageSizes.add((int) (60 * (40D / (20D + (12 * (totalShells - 1))))));
		imageSizes.add((int) ((position + 1) * (60 * (40D / (20D + (12 * (totalShells - 1)))))));
		if(electrons.isEmpty())
			electrons.add(0);
		electrons.add(ele);
	}
	 
	public static void drawImage()
	 {
		int size = 256;
		AtomImageWriter writer = new AtomImageWriter(size, size);
		Graphics2D graphics = writer.getGraphics();
		for(int i = 1; i < imageSizes.size(); i++)
		{
			graphics.setColor(Color.BLACK);
			graphics.setStroke(new BasicStroke(3f));
			graphics.drawOval((size / 2) - (imageSizes.get(i) / 2), (size / 2) - (imageSizes.get(i) / 2), imageSizes.get(i) , imageSizes.get(i));
			graphics.setStroke(new BasicStroke(1f));
			graphics.setColor(Color.YELLOW);
			for(int k = 0; k < electrons.get(i); k++)
				graphics.fillOval((int) Math.floor(imageSizes.get(i) / 2 * Math.cos(((Math.PI*2) / electrons.get(i)) * k) + (size / 2) - 3.5d), 
						(int) Math.floor(imageSizes.get(i) / 2 * Math.sin(((Math.PI*2) / electrons.get(i)) * k) + (size / 2) - 3.5d), 7, 7);
		}
		int totalElectrons = 0;
		for(int i : electrons)
			totalElectrons+= i;
		imageSizes.clear();
		electrons.clear();
		writer.build(totalElectrons);
	 }
	
	public static String getNameOfElement(int electrons)
	{
		return ELEMENT_MASS.length < electrons ? "" : ( electrons == 0 ? new TextComponentTranslation("atom.nonexist").getUnformattedText() : capatilizeFirstLetter(ELEMENT_NAMES[electrons - 1]) + " (%s)".replace("%s", capatilizeFirstLetter(ELEMENT_SYMBOLS[electrons - 1])));
	}
	
	public static String getElementMass(int electrons)
	{
		if(electrons <= 0)
			return "0";
		return String.valueOf(ELEMENT_MASS.length < electrons ? makeElementMassGuess(electrons) : ELEMENT_MASS[electrons - 1]);
	}
	
	public static String makeElementMassGuess(int electrons)
	{
		double mass = Math.round((-3.63 + (2.48 * electrons) + (9.48E-04 * (electrons * electrons))) * 10000d) / 10000d;
		if(mass < 0)
			 return String.valueOf((long)Integer.MAX_VALUE + ((long)MathHelper.floor(mass)- (long)Integer.MIN_VALUE + ( Math.round(mass * 10000d) / 10000d) - (long)MathHelper.floor(mass)));
		return String.valueOf(mass);
	}
		
	public static ResourceLocation registerAtom(int electrons)
	{
		if(!AtomBakedModel.INITILIZED_ATOMS.contains(electrons))
		{
			Atom.getAtom(electrons).addBufferData();
			drawImage();
			ForgeHooksClient.registerTESRItemStack(VestigeItems.ATOM, electrons, AtomRendererHelper.TileEntityHelper.class);
			AtomBakedModel.INITILIZED_ATOMS.add(electrons);
		}
		return AtomImageWriter.getLocation(electrons);
		
	}
		
	public static String capatilizeFirstLetter(String name)
	{
		return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
	}

}
