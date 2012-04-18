package net.minecraft.src;

final class BiomeDecoratorGen
{
	public int rate;
	public WorldGenerator gen;
	
	public BiomeDecoratorGen(int rate, WorldGenerator gen)
	{
		this.rate = rate;
		this.gen = gen;
	}
	
	public BiomeDecoratorGen copy()
	{
		return new BiomeDecoratorGen(this.rate, this.gen);
	}
}