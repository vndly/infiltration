package com.mauriciotogneri.wakeupmax.objects;

import com.misty.kernel.Process;

public class Game extends Process
{
	private final Max max;

	public Game()
	{
		super(true, false);
		
		World world = new World();
		world.create("maps/map_1.txt");
		
		this.max = new Max(world);
		this.max.start();
	}
	
	@Override
	public void update(float delta)
	{
		this.max.update(delta);
	}
}