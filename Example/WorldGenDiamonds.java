package net.minecraft.src;

import java.util.Random;

public class WorldGenDiamonds extends WorldGenerator {

	@Override
	public boolean generate(World var1, Random var2, int var3, int var4,int var5) 
	{
		for(int i = 0; i < 1; i++)
		{
			int x = var3 + var2.nextInt(8);
			int z = var5 + var2.nextInt(8);
			int y = var1.getHeightValue(x, z);
			if(!(var1.getBlockMaterial(x, y-1, z) == Material.water || var1.getBlockMaterial(x, y-1, z) == Material.lava))
				var1.setBlockAndMetadata(x, y, z, Block.blockDiamond.blockID, 0);
		}
		
		return true;
	}

}
