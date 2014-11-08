package com.mauriciotogneri.infiltration.objects.enemies.turret;

import com.mauriciotogneri.infiltration.objects.Building;
import com.mauriciotogneri.infiltration.utils.Resources;
import com.misty.kernel.Process;

public class TurretBeam extends Process
{
	private final float speed;
	private final int directionX;
	private final int directionY;
	private final Building building;
	
	public TurretBeam(float x, float y, float angle, float speed, int directionX, int directionY, Building building)
	{
		super(true, true);
		
		this.x = x;
		this.y = y;
		this.angle = angle;
		this.z = 2;
		
		this.speed = speed;
		this.directionX = directionX;
		this.directionY = directionY;
		
		this.building = building;
		
		setImage(Resources.Images.Enemies.Turret.BEAM);
	}
	
	@Override
	public void update(float delta)
	{
		this.x += (this.speed * delta) * this.directionX;
		this.y += (this.speed * delta) * this.directionY;
		
		// if (this.x > Level.BLOCK_SIZE * 12)
		if (this.building.collide(this))
		{
			finish();
		}
	}
}