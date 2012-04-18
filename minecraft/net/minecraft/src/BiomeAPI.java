package net.minecraft.src;

import java.util.ArrayList;
import java.util.Arrays;


public abstract class BiomeAPI
{
	private static IWorldGenerator worldGen = null;
	
	public static void setDefualtWorldGenerator()
	{
		if(worldGen != null) worldGen = null;
	}
	
	public static void setWorldGenerator(IWorldGenerator newWorldGen)
	{
		worldGen = newWorldGen;
	}
	
	public static GenLayer[] genWorld(long seed, WorldType worldType)
	{
		if(worldGen != null)
			return worldGen.generateWorld(seed, worldType);
		return  GenLayer.func_48425_a(seed, worldType);
	}
	
	public static void removeBiome(BiomeGenBase biome)
	{
		// Only remove the original biomes
		if(biome.biomeID > 22)
			throw new MinecraftException("You can only remove the original biomes through this method.");
		
		// start with the generator since it is the hardest
		if(biome == BiomeGenBase.ocean)
		{
			disabledSpecialBiomes[0][0] = 1;
			disabledSpecialBiomes[0][1] = BiomeGenBase.plains.biomeID;
		}
		else if(biome == BiomeGenBase.river)
		{
			disabledSpecialBiomes[1][0] = 1;
			disabledSpecialBiomes[1][1] = BiomeGenBase.plains.biomeID;
		}
		else if(biome == BiomeGenBase.icePlains)
		{
			disabledSpecialBiomes[1][0] = 1;
			disabledSpecialBiomes[1][1] = BiomeGenBase.plains.biomeID;
		}
		else if(biome == BiomeGenBase.swampland)
		{
			disabledSpecialBiomes[1][0] = 1;
			disabledSpecialBiomes[1][1] = BiomeGenBase.plains.biomeID;
		}
		else if(biome == BiomeGenBase.mushroomIsland)
		{
			disabledSpecialBiomes[1][0] = 1;
			disabledSpecialBiomes[1][1] = BiomeGenBase.plains.biomeID;
		}
		else if(biome == BiomeGenBase.beach || biome == BiomeGenBase.mushroomIslandShore)
		{
			// do nothing for these guys
		}
		else
		{
			BiomeGenBase[] biomeArray = new BiomeGenBase[GenLayerBiome.biomeArray.length - 1];
			for(int i = 0, j = 0; i < GenLayerBiome.biomeArray.length; i++)
			{
				if(GenLayerBiome.biomeArray[i] != biome)
					biomeArray[i-j] = GenLayerBiome.biomeArray[i];
				else j++;
			}

			GenLayerBiome.biomeArray = biomeArray;
		}
	}
	
	public static void setBiomeReplacement(BiomeGenBase biome1, BiomeGenBase biome2)
	{
		if(biome1 == BiomeGenBase.ocean)
		{
			disabledSpecialBiomes[0][1] = biome2.biomeID;
		}
		else if(biome1 == BiomeGenBase.river)
		{
			disabledSpecialBiomes[1][1] = biome2.biomeID;
		}
		else if(biome1 == BiomeGenBase.icePlains)
		{
			disabledSpecialBiomes[1][1] = biome2.biomeID;
		}
		else if(biome1 == BiomeGenBase.swampland)
		{
			disabledSpecialBiomes[1][1] = biome2.biomeID;
		}
		else if(biome1 == BiomeGenBase.mushroomIsland)
		{
			disabledSpecialBiomes[1][1] = biome2.biomeID;
		}
		else throw new MinecraftException("You can only set a replacement for the ocean, river, icePlains, swampland and mushroomIsland");
	}
	
	public static int[][] disabledSpecialBiomes = new int[5][2];
}