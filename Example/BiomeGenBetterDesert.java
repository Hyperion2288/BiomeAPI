package net.minecraft.src;

public class BiomeGenBetterDesert extends BiomeGenDesert 
{

	public BiomeGenBetterDesert(int par1) 
	{
		super(par1);
		this.biomeDecorator.setDecoratorRate("diamonds", 4);
	}
	
	public BiomeGenBetterDesert(String name)
	{
		super(BiomeAPI.getNextBiomeID(name));
		setEnabled(name);
	}
}
