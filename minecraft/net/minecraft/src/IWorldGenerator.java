package net.minecraft.src;

/**
 * A class that wants to replace the default world generator must implement this interface
 * @author Michael
 *
 */
public interface IWorldGenerator
{
	public GenLayer[] generateWorld(long seed, WorldType worldType);
}