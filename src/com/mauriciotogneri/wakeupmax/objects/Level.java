package com.mauriciotogneri.wakeupmax.objects;

import com.mauriciotogneri.wakeupmax.controls.Input;
import com.mauriciotogneri.wakeupmax.utils.Resources;

public class Level
{
	private final Max max;
	private final Background background;
	
	public Level()
	{
		World world = new World();
		world.create("maps/map_1.txt");
		
		this.background = new Background(1, Resources.Images.Levels.LEVEL_1);
		this.background.start();
		
		this.max = new Max(world);
		this.max.start();
	}
	
	public void update(float delta, Input input)
	{
		this.max.update(delta, input);
	}
}