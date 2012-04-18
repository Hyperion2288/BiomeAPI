package net.minecraft.src;

public class BiomeGenSuperHill extends BiomeGenBase 
{

	protected BiomeGenSuperHill(int id) 
	{
		super(id);
		this.biomeDecorator.setDecoratorRate("diamonds", 0);
	}
}
