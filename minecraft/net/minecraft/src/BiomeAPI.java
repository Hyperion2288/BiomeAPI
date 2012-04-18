package net.minecraft.src;

import java.util.ArrayList;
import java.util.Arrays;


public abstract class BiomeAPI
{
	/**
	 * This is the current world generator that replaces the default mojang one
	 * If this is null then the default is used
	 */
	private static IWorldGenerator worldGen = null;
	
	/**
	 * Sets the world generator to the default
	 */
	public static void setDefualtWorldGenerator()
	{
		if(worldGen != null) worldGen = null;
	}
	
	/**
	 * Sets the world generator to the one supplied by this function
	 * @param newWorldGen - the generator to use
	 */
	public static void setWorldGenerator(IWorldGenerator newWorldGen)
	{
		worldGen = newWorldGen;
	}
	
	/**
	 * This called by WorldChunkManager to get GenLayer[] to generate the world
	 * @param seed
	 * @param worldType
	 * @return
	 */
	public static GenLayer[] genWorld(long seed, WorldType worldType)
	{
		if(worldGen != null)
			return worldGen.generateWorld(seed, worldType);
		return  GenLayer.func_48425_a(seed, worldType);
	}
	
	/**
	 * This removes the specified biome
	 * It only works for the vanilla biomes and only for the default generator
	 * If you want to it to work for other biomes or generators just code it in yourself :)
	 * @param biome
	 */
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
	
	/**
	 * For the following biomes, their generation is not controlled by the GenLayerBiome and as such a hack is used to disable them
	 * The biomes are simply switch wholesale for another biome and this function allows you to set them. The default is plains
	 * ocean - will also disable beach
	 * river
	 * mushroomIsland - will also disable mushroom island shore
	 * icePlains
	 * @param To be replaced
	 * @param Replacee
	 */
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
			disabledSpecialBiomes[2][1] = biome2.biomeID;
		}
		else if(biome1 == BiomeGenBase.mushroomIsland)
		{
			disabledSpecialBiomes[3][1] = biome2.biomeID;
		}
		else throw new MinecraftException("You can only set a replacement for the ocean, river, icePlains, swampland and mushroomIsland");
	}
	
	/**
	 * This holds the replacement biomes
	 */
	public static int[][] disabledSpecialBiomes = new int[4][2];
}