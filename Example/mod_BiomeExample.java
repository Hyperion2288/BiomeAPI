package net.minecraft.src;

public class mod_BiomeExample extends BaseMod 
{
	@MLProp(info = "Test", name = "Test") private int testvar = 16;
	
	@Override
	public void load() 
	{
		// Adds our test decorator to the whole world
		BiomeDecorator.addDecorator("diamonds", 1, new WorldGenDiamonds());
		
		// Create a basic hill biome
		(new BiomeGenSuperHill(BiomeGenBase.getNextBiomeID("superHills")))
			.addBiomeEverything().setBiomeName("Super Hills").setMinMaxHeight(2.9f, 3.0f);
		
		// Create a desert replacement that edits the rate for the diamond generation
		BiomeGenBase betterDesert = (new BiomeGenBetterDesert(BiomeGenBase.getNextBiomeID("betterDesert")))
			.addBiomeSpawn().addBiomeVillage().addBiomeStronghold()
			.setColor(16421912).setBiomeName("Desert").setDisableRain()
			.setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.1F, 0.2F);
		BiomeAPI.replaceBiome(BiomeGenBase.desert, betterDesert);
	}

	@Override
	public String getVersion() 
	{
		return "BiomeExample";
	}

}
