package com.mauriciotogneri.wakeupmax.objects;

public class Level
{
	private final Max max;
	
	public Level()
	{
		World world = new World();
		world.create("maps/map_1.txt");

		this.max = new Max(world);
		this.max.start();
	}

	public void update(float delta)
	{
		this.max.update(delta);
	}
}