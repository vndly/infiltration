package com.mauriciotogneri.infiltration.objects;

import com.mauriciotogneri.infiltration.controls.Input;
import com.mauriciotogneri.infiltration.objects.enemies.laser.Laser;
import com.mauriciotogneri.infiltration.objects.enemies.turret.Turret;
import com.mauriciotogneri.infiltration.utils.Resources;

public class Level
{
	private final Building building;
	private final Protagonist max;
	private final ViewPoint viewPoint;
	
	private final Laser laser1;
	private final Laser laser2;
	private final Turret turret;
	
	public static final int BLOCK_SIZE = 32;
	
	public Level()
	{
		this.building = new Building(Resources.Levels.LEVEL_1, Resources.Images.Levels.Buildings.BUILDING_1);
		this.building.start();
		
		this.viewPoint = new ViewPoint((this.building.getWidth() + 1) * Level.BLOCK_SIZE);
		this.viewPoint.start();
		
		this.max = new Protagonist(this, this.building, 28, 15, 1);
		this.max.start();
		
		this.laser1 = new Laser(18, 13, 2, 1000, 1000, true);
		this.laser2 = new Laser(20, 13, 2, 1000, 1000, true);
		
		this.turret = new Turret(5, 4, 1500, Level.BLOCK_SIZE * 8, 1, this.building);
		this.turret.start();
		
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
		this.laser1.reset();
		this.laser2.reset();
		this.turret.reset();
	}
	
	public void stop()
	{
		this.laser1.stop();
		this.laser2.stop();
	}
}