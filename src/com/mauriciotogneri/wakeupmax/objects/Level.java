package com.mauriciotogneri.wakeupmax.objects;

import com.mauriciotogneri.wakeupmax.controls.Input;

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

	public void update(float delta, Input input)
	{
		this.max.update(delta, input);
	}
}