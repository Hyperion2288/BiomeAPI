package net.minecraft.src;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MapGenStronghold extends MapGenStructure
{
    //private BiomeGenBase[] allowedBiomeGenBases;
    
    /** A list of all the biomes villages can spawn in. */
	/**
	 * Stronghold uses a different method to set the spawn biomes then villages did that was not easy for adding in biomes on the fly
	 * Therefore, I changed things around to make it easier
	 */
    public static List strongholdSpawnBiomes = new ArrayList();
    
    /**
     * This adds the biome to list of avaialbe spawn biomes
     * @param biome
     */
    public static void addStrongholdBiomes(BiomeGenBase biome)
    {
    	for(int i = 0; i < strongholdSpawnBiomes.size(); i++)
    		if(biome == strongholdSpawnBiomes.get(i)) return;
    	strongholdSpawnBiomes.add(biome);
    }

    /**
     * is spawned false and set true once the defined BiomeGenBases were compared with the present ones
     */
    private boolean ranBiomeCheck;
    private ChunkCoordIntPair[] structureCoords;

    public MapGenStronghold()
    {
        //this.allowedBiomeGenBases = new BiomeGenBase[] {BiomeGenBase.desert, BiomeGenBase.forest, BiomeGenBase.extremeHills, BiomeGenBase.swampland, BiomeGenBase.taiga, BiomeGenBase.icePlains, BiomeGenBase.iceMountains, BiomeGenBase.desertHills, BiomeGenBase.forestHills, BiomeGenBase.extremeHillsEdge, BiomeGenBase.jungle, BiomeGenBase.jungleHills};
       	this.addStrongholdBiomes(BiomeGenBase.desert);
       	this.addStrongholdBiomes(BiomeGenBase.forest);
       	this.addStrongholdBiomes(BiomeGenBase.extremeHills);
       	this.addStrongholdBiomes(BiomeGenBase.swampland);
       	this.addStrongholdBiomes(BiomeGenBase.taiga);
       	this.addStrongholdBiomes(BiomeGenBase.icePlains);
       	this.addStrongholdBiomes(BiomeGenBase.iceMountains);
       	this.addStrongholdBiomes(BiomeGenBase.desertHills);
       	this.addStrongholdBiomes(BiomeGenBase.forestHills);
       	this.addStrongholdBiomes(BiomeGenBase.extremeHillsEdge);
       	this.addStrongholdBiomes(BiomeGenBase.jungle);
       	this.addStrongholdBiomes(BiomeGenBase.jungleHills);
        this.structureCoords = new ChunkCoordIntPair[3];
    }

    protected boolean canSpawnStructureAtCoords(int par1, int par2)
    {
        if (!this.ranBiomeCheck)
        {
            Random var3 = new Random();
            var3.setSeed(this.worldObj.getSeed());
            double var4 = var3.nextDouble() * Math.PI * 2.0D;

            for (int var6 = 0; var6 < this.structureCoords.length; ++var6)
            {
                double var7 = (1.25D + var3.nextDouble()) * 32.0D;
                int var9 = (int)Math.round(Math.cos(var4) * var7);
                int var10 = (int)Math.round(Math.sin(var4) * var7);
                ArrayList var11 = new ArrayList();
                //BiomeGenBase[] var12 = this.allowedBiomeGenBases;
                //int var13 = var12.length;

                for (int var14 = 0; var14 < this.strongholdSpawnBiomes.size(); ++var14)
                {
                    BiomeGenBase var15 = (BiomeGenBase)this.strongholdSpawnBiomes.get(var14);
                    var11.add(var15);
                }

                ChunkPosition var19 = this.worldObj.getWorldChunkManager().findBiomePosition((var9 << 4) + 8, (var10 << 4) + 8, 112, var11, var3);

                if (var19 != null)
                {
                    var9 = var19.x >> 4;
                    var10 = var19.z >> 4;
                }
                else
                {
                    System.out.println("Placed stronghold in INVALID biome at (" + var9 + ", " + var10 + ")");
                }

                this.structureCoords[var6] = new ChunkCoordIntPair(var9, var10);
                var4 += (Math.PI * 2D) / (double)this.structureCoords.length;
            }

            this.ranBiomeCheck = true;
        }

        ChunkCoordIntPair[] var16 = this.structureCoords;
        int var17 = var16.length;

        for (int var5 = 0; var5 < var17; ++var5)
        {
            ChunkCoordIntPair var18 = var16[var5];

            if (par1 == var18.chunkXPos && par2 == var18.chunkZPosition)
            {
                System.out.println(par1 + ", " + par2);
                return true;
            }
        }

        return false;
    }

    protected List func_40482_a()
    {
        ArrayList var1 = new ArrayList();
        ChunkCoordIntPair[] var2 = this.structureCoords;
        int var3 = var2.length;

        for (int var4 = 0; var4 < var3; ++var4)
        {
            ChunkCoordIntPair var5 = var2[var4];

            if (var5 != null)
            {
                var1.add(var5.getChunkPosition(64));
            }
        }

        return var1;
    }

    protected StructureStart getStructureStart(int par1, int par2)
    {
        StructureStrongholdStart var3;

        for (var3 = new StructureStrongholdStart(this.worldObj, this.rand, par1, par2); var3.getComponents().isEmpty() || ((ComponentStrongholdStairs2)var3.getComponents().get(0)).portalRoom == null; var3 = new StructureStrongholdStart(this.worldObj, this.rand, par1, par2))
        {
            ;
        }

        return var3;
    }
}
