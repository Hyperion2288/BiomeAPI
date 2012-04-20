package net.minecraft.src;

public class BiomeGenSuperHill extends BiomeGenBase 
{

	protected BiomeGenSuperHill(String name) 
	{
		super(name);
		this.biomeDecorator.setDecoratorRate("diamonds", 0);
	}
}
