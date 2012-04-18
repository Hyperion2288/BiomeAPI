package net.minecraft.src;

public class BiomeGenBetterDesert extends BiomeGenDesert 
{

	public BiomeGenBetterDesert(int par1) {
		super(par1);
		this.biomeDecorator.setDecoratorRate("diamonds", 4);
	}
}
