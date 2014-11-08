package com.mauriciotogneri.infiltration.objects;

import com.mauriciotogneri.infiltration.controls.Input;
import com.mauriciotogneri.infiltration.objects.enemies.laser.Laser;
import com.mauriciotogneri.infiltration.utils.Resources;

public class Level
{
	private final Building building;
	private final Protagonist max;
	private final ViewPoint viewPoint;
	private final Laser laser;
	
	public static final int BLOCK_SIZE = 32;
	
	public Level()
	{
		this.building = new Building(Resources.Levels.LEVEL_1, Resources.Images.Levels.LEVEL_1);
		this.building.start();
		
		this.viewPoint = new ViewPoint((this.building.getWidth() + 1) * Level.BLOCK_SIZE);
		this.viewPoint.start();
		
		this.max = new Protagonist(this, this.building, Level.BLOCK_SIZE * 28, Level.BLOCK_SIZE * 15, -1);
		this.max.start();
		
		this.laser = new Laser(Level.BLOCK_SIZE * 19, Level.BLOCK_SIZE * 13, 2, 1000, 2000, true);
		
		reset();
	}
	
	public void update(float delta, Input input)
	{
		this.max.update(delta, input);
		this.viewPoint.update(this.max);
	}
	
	public void reset()
	{
		this.max.reset();
		this.laser.reset();
	}
	
	public void stop()
	{
		// TODO
		
		this.laser.stop();
	}
}