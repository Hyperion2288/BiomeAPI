package net.minecraft.src;

public interface IWorldGenerator
{
	public GenLayer[] generateWorld(long seed, WorldType worldType);
}