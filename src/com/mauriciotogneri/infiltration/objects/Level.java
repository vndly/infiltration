package com.mauriciotogneri.infiltration.objects;

import com.mauriciotogneri.infiltration.controls.Input;
import com.mauriciotogneri.infiltration.objects.enemies.laser.Laser;
import com.mauriciotogneri.infiltration.utils.Resources;

public class Level
{
	private final Protagonist max;
	private final ViewPoint viewPoint;
	private final Laser laser;
	
	public static final int BLOCK_SIZE = 32;
	
	public Level()
	{
		Building building = new Building(Resources.Levels.LEVEL_1, Resources.Images.Levels.LEVEL_1);
		building.start();
		
		this.viewPoint = new ViewPoint((building.getWidth() + 1) * Level.BLOCK_SIZE);
		this.viewPoint.start();
		
		this.max = new Protagonist(building);
		this.max.start();
		
		this.laser = new Laser(Level.BLOCK_SIZE * 19, Level.BLOCK_SIZE * 13, 2, 1000, 2000, true);
	}
	
	public void update(float delta, Input input)
	{
		this.max.update(delta, input);
		this.viewPoint.update(this.max);
	}
	
	public void reset()
	{
		// TODO
	}
}