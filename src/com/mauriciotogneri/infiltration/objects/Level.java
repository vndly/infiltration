package com.mauriciotogneri.infiltration.objects;

import com.mauriciotogneri.infiltration.controls.Input;
import com.mauriciotogneri.infiltration.utils.Resources;

public class Level
{
	private final Protagonist max;
	
	public static final int BLOCK_SIZE = 32;
	
	public Level()
	{
		Building building = new Building(Resources.Levels.LEVEL_1, 1, Resources.Images.Levels.LEVEL_1);
		building.start();
		
		this.max = new Protagonist(building);
		this.max.start();
	}
	
	public void update(float delta, Input input)
	{
		this.max.update(delta, input);
	}
}