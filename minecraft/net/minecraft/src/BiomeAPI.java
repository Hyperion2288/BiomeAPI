package net.minecraft.src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;


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
	 * This assigns a biome an id based on the avaiable slots
	 * It also searches for the id in config file if this is not the first time running the mod
	 * Key should be a unique identifier for the biome. Appends 'BiomeID' to the key.
	 * @param key
	 * @return
	 */
	public static int getNextBiomeID(String key)
	{
		//Start by searching for the biome in the config file
		if(ModLoader.props.containsKey("BiomeID" + key))
			return Integer.parseInt(ModLoader.props.getProperty("BiomeID" + key));
		
		//Didn't find it so search for a new id
		for(int i = 23; i < BiomeGenBase.biomeList.length; i++)
		{
			if(BiomeGenBase.biomeList[i] == null) 
			{
				Iterator iter = ModLoader.props.entrySet().iterator();
				boolean found = false;
				while(iter.hasNext())
				{
					Map.Entry<String, String> e = (Map.Entry<String, String>)iter.next();
					if(e == null) throw new MinecraftException("WTF?");
					if(e.getKey().startsWith("BiomeID") && e.getKey() != ("BiomeID" + key))
						if(Integer.parseInt(e.getValue()) == i)
							found = true;	
				}
				if(!found)
				{
					// Add the value to the config file
					ModLoader.props.setProperty("BiomeID" + key, Integer.toString(i));
					return i;
				}
			}	
		}
		// return -1 if the array is filled up and there are no slots left
		return -1; 
	}
	
	/**
	 * This replaces one biome with another in the BiomeGenBase.biomeList
	 * This is useful for inserting your own version of the vanilla biomes
	 * Because it does not edit the biomeIDs, any time someone references the replaced biomeID
	 * the new biome will be returned instead
	 * @param biome to be replaced
	 * @param replacee
	 */
	public static void replaceBiome(BiomeGenBase biome1, BiomeGenBase biome2)
	{
		BiomeGenBase.biomeList[biome1.biomeID] = biome2;
	}
	
	/**
	 * This removes the specified biome
	 * It only works for the vanilla biomes and only for the default generator
	 * If you want to it to work for other biomes or generators just code it in yourself :)
	 * For those biomes not in the GenLayerBiome this does a simple replace with the plains biome
	 * If you would like a different replace biome simply call replaceBiome yourself
	 * @param biome
	 */
	public static void removeBiome(BiomeGenBase biome)
	{
		// Only remove the original biomes
		if(biome.biomeID > 22)
			throw new MinecraftException("You can only remove the original biomes through this method.");
		
		// start with the generator since it is the hardest
		if(biome == BiomeGenBase.ocean || biome == BiomeGenBase.mushroomIsland)
		{
			replaceBiome(biome, BiomeGenBase.plains);
			if(biome == BiomeGenBase.ocean) 
				replaceBiome(BiomeGenBase.beach, BiomeGenBase.plains);
			else
				replaceBiome(BiomeGenBase.mushroomIslandShore, BiomeGenBase.plains);
		}
		else if(biome == BiomeGenBase.river || biome == BiomeGenBase.river)
		{
			replaceBiome(BiomeGenBase.river, BiomeGenBase.plains);
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
}